BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;

INSERT INTO Showings(theaterID, showingDate, startTime, movieID, priceCode)
	SELECT s.theaterID, s.showingDate, s.startTime, s.movieID, NULL
	FROM ModifyShowings s
	WHERE NOT EXISTS (SELECT * FROM Showings
					  WHERE s.theaterID = theaterID
					  AND s.showingDate = showingDate
					  AND s.startTime = startTime);

UPDATE Showings sh
	SET movieID = sh.movieID
	FROM ModifyShowings s
	WHERE sh.theaterID = s.theaterID
	AND sh.showingDate = s.showingDate
	AND sh.startTime = s.startTime;

COMMIT TRANSACTION;
	


