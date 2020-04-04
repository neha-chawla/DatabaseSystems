SELECT m.rating, COUNT(*) AS misreportCount
FROM Movies m, earningsView e
WHERE m.movieID = e.movieID
AND m.totalEarned <> e.computedEarnings
GROUP BY m.rating
HAVING EVERY (m.year < 2019);

/*
rating | misreportcount 
--------+----------------
 G      |              2
 P      |              1
(2 rows)
*/

DELETE FROM Tickets
WHERE theaterID = 111
AND seatNum = 1
AND showingDate = DATE '2009-06-24'
AND startTime = TIME '15:00:00';

DELETE FROM Tickets
WHERE theaterID = 444
AND seatNum = 5
AND showingDate = DATE '2020-06-24'
AND startTime = TIME '15:00:00';

/*
rating | misreportcount 
--------+----------------
 G      |              2
 P      |              1
(2 rows)
*/
