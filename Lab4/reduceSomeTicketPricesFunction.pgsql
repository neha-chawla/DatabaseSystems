CREATE FUNCTION reduceSomeTicketPricesFunction(IN maxTicketCount INT) 
		RETURNS INTEGER AS $$
		DECLARE numLeft INTEGER := maxTicketCount;
			price NUMERIC (4,2);
			code CHAR(1);
			id INTEGER;
			sNum INTEGER;
			sDate DATE;
			sTime TIME;
			decrease INTEGER;
			totalReduced INTEGER := 0;
		DECLARE priceCursor CURSOR FOR
			SELECT t.ticketPrice, s.priceCode, t.theaterID, t.seatNum, t.showingDate, t.startTime
                        FROM Showings s, Tickets t
                        WHERE s.theaterID = t.theaterID
                        AND s.showingDate = t.showingDate
                        AND s.startTime = t.startTime
			AND t.ticketPrice IS NOT NULL
			AND s.priceCode IS NOT NULL
                        ORDER BY priceCode, t.customerID;
	BEGIN
		OPEN priceCursor;
		FETCH NEXT FROM priceCursor INTO price, code, id, sNum, sDate, sTime;
		WHILE(numLeft > 0 AND FOUND) LOOP
			IF code = 'A' THEN
				UPDATE Tickets SET ticketPrice = ticketPrice - 3.00
				WHERE theaterID = id
				AND showingDate = sDate
				AND seatNum = sNum
				AND startTime = sTime;
				numLeft = numLeft - 1;
				totalReduced = totalReduced + 3;
			ELSEIF code = 'B' THEN
				UPDATE Tickets SET ticketPrice = price - 2.00
                                WHERE theaterID = id
                                AND seatNum = sNum
                                AND showingDate = sDate
                                AND startTime = sTime;
				numLeft = numLeft - 1;
				totalReduced = totalReduced + 2;
			ELSE
				UPDATE Tickets SET ticketPrice = price - 1.00
				WHERE theaterID = id
                                AND seatNum = sNum
                                AND showingDate = sDate
                                AND startTime = sTime;
				numLeft = numLeft - 1;
				totalReduced = totalReduced + 1;
			END IF;
			FETCH NEXT FROM priceCursor INTO price, code, id, sNum, sDate, sTime;
			EXIT WHEN NOT FOUND;
		END LOOP;
		CLOSE priceCursor;
		return totalReduced;
	END;
	$$ LANGUAGE plpgsql;
