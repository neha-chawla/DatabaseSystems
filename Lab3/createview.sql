CREATE VIEW earningsView(movieID, computedEarnings) AS
   SELECT movieID, SUM(computedEarnings)
   FROM(SELECT s.movieID, SUM(t.ticketPrice) computedEarnings
	FROM Showings s, Tickets t
	WHERE s.theaterID = t.theaterID
	AND s.showingDate = t.showingDate
	AND s.startTime = t.startTime
	AND s.movieID IS NOT NULL
	GROUP BY s.movieID
	UNION (SELECT s2.movieID, 0 AS computedEarnings
               FROM Showings s2
               WHERE NOT EXISTS (SELECT *
                                 FROM Tickets t
                                 WHERE s2.theaterID = t.theaterID
                                 AND s2.showingDate = t.showingDate
                                 AND s2.startTime = t.startTime)
              )
	UNION (SELECT m.movieID, 0 AS computedEarnings
	       FROM Movies m, Showings s
	       WHERE m.movieID <> ANY (SELECT movieID FROM Showings)
	      )
       ) AS movieSummary
   GROUP BY movieID;
