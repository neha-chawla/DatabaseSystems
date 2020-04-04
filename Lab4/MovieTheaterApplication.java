import java.sql.*;
import java.util.*;

/**
 * The class implements methods of the MovieTheater database interface.
 *
 * All methods of the class receive a Connection object through which all
 * communication to the database should be performed. Note: the
 * Connection object should not be closed by any method.
 *
 * Also, no method should throw any exceptions. In particular, in case
 * an error occurs in the database, then the method should print an
 * error message and call System.exit(-1);
 */

public class MovieTheaterApplication {

    private Connection connection;

    /*
     * Constructor
     */
    public MovieTheaterApplication(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection()
    {
        return connection;
    }

    /**
     * getShowingsCount has a string argument called thePriceCode, and returns the number of
     * Showings whose priceCode equals thePriceCode.
     * A value of thePriceCode that’s not ‘A’, B’ or ‘C’ is an error.
     */

    public Integer getShowingsCount(String thePriceCode)
    {
        Integer result = 0;
        // your code here
        try{
		if(thePriceCode == "A" || thePriceCode == "B" || thePriceCode == "C"){ // Making sure input is A, B, or C
			char priceCode = thePriceCode.charAt(0); // Converting priceCode to character
			Statement st = connection.createStatement(); // creating st statement
			ResultSet rst = st.executeQuery("SELECT COUNT(*) FROM Showings WHERE priceCode = '" + priceCode + "'"); // executing query
			while (rst.next()){ // inserting query result value into result integer
				result = rst.getInt(1);
			}
		}
		else{
			System.out.println("Error: Make sure price code is A, B, or C");
			System.exit(-1); // System.exit for invalid input
		}
	}
	catch (SQLException e){
		e.printStackTrace();
		System.out.println("Query Execution Error");
	} // catches errors

        // end of your code
        return result; // returns number of tuples with the given price code
    }


    /**
     * updateMovieName method has two arguments, an integer argument theMovieID, and a string
     * argument, newMovieName.  For the tuple in the Movies table (if any) whose movieID equals
     * theMovieID, updateMovieName should update its name to be newMovieName.  (Note that there
     * might not be any tuples whose movieID matches theMovieID.)  updateMovieName should return
     * the number of tuples that were updated, which will always be 0 or 1.
     */

    public int updateMovieName(int theMovieID, String newMovieName)
    {
        // your code here; return 0 appears for now to allow this skeleton to compile.
        int numTuples = 0;
	try{
		Statement st = connection.createStatement(); // creating st statement
		numTuples = st.executeUpdate("UPDATE Movies SET name  = '" + newMovieName + "' WHERE movieID = " + theMovieID); // updates movie name if movie id matches theMovieID 
	}
	catch (SQLException e){ // catches errors
                e.printStackTrace();
		System.out.println("Query Execution Error");
	} 

	return numTuples; // returns the number of updated tuples (0 or 1)
        // end of your code
    }


    /**
     * reduceSomeTicketPrices has an integer parameter, maxTicketCount.  It invokes a stored
     * function reduceSomeTicketPricesFunction that you will need to implement and store in the
     * database according to the description in Section 5.  reduceSomeTicketPricesFunction should
     * have the same parameter, maxTicketCount.  A value of maxTicketCount that’s not positive is
     * an error.
     *
     * The Tickets table has a ticketPrice attribute, which gives the price (in dollars and cents)
     * of each ticket.  reduceSomeTicketPricesFunction will reduce the ticketPrice for some (but
     * not necessarily all) tickets; Section 5 explains which tickets should have their
     * ticketPrice reduced, and also tells you how much they should be reduced.  The
     * reduceSomeTicketPrices method should return the same integer result that the
     * reduceSomeTicketPricesFunction stored function returns.
     *
     * The reduceSomeTicketPrices method must only invoke the stored function
     * reduceSomeTicketPricesFunction, which does all of the assignment work; do not implement
     * the reduceSomeTicketPrices method using a bunch of SQL statements through JDBC.
     */

    public int reduceSomeTicketPrices (int maxTicketCount)
    {
        // There's nothing special about the name storedFunctionResult
        int storedFunctionResult = 0;
	// your code here
	try{
		if (maxTicketCount >= 0){ // checks that maxTicketCount is a positive number
			PreparedStatement pst = connection.prepareStatement("SELECT * FROM reduceSomeTicketPricesFunction(?)"); //creates prepared statement
			pst.setInt(1, maxTicketCount); // assigns maxTicketCount value to prepared statement parameter
			ResultSet rst = pst.executeQuery(); // executes query
			while(rst.next()){ // puts resulting integer in storedFunctionResult
				storedFunctionResult = rst.getInt(1);
			}
			rst.close();
			pst.close();
		}
		else{ // output message if maxTicketCount is not positive
			System.out.println("Error: Make sure max ticket count is positive");
                        System.exit(-1);
		}
        }
        catch (SQLException e){ // catches errors
                e.printStackTrace();
                System.out.println("Query Execution Error");
        }
    
        // end of your code
        return storedFunctionResult; // returns number returned by reduceSomeTicketPricesFunction

    }

};
