INSERT INTO Tickets (theaterID, seatNum, showingDate, startTime, customerID, ticketPrice)
VALUES (111, 1, DATE '2020-06-23', TIME '11:00:00', 1, 8.50);

INSERT INTO Tickets (theaterID, seatNum, showingDate, startTime, customerID, ticketPrice)
VALUES (111, 2, DATE '2009-06-24', TIME '11:00:00', 5, 5.00);

INSERT INTO Tickets (theaterID, seatNum, showingDate, startTime, customerID, ticketPrice)
VALUES (111, 4, DATE '2009-06-23', TIME '11:00:00', 1, 8.50);

UPDATE Tickets
	SET ticketPrice = 3
	WHERE (ticketPrice = 8.50);

UPDATE Tickets
	SET ticketPrice = -3
	WHERE (ticketPrice = 3);

UPDATE Customers
	SET joinDate = DATE '2016-12-01'
	WHERE (joinDate > DATE '2018-01-01');

UPDATE Customers
	SET joinDate = DATE '2014-12-01'
	WHERE (joinDate = DATE '2016-12-01');

UPDATE Showings
	SET priceCode = 'B'
	WHERE (movieID > 102);

UPDATE Showings
	SET priceCode = NULL
	WHERE (movieID > 102);

