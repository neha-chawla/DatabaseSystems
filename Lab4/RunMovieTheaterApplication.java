import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * A class that connects to PostgreSQL and disconnects.
 * You will need to change your credentials below, to match the usename and password of your account
 * in the PostgreSQL server.
 * The name of your database in the server is the same as your username.
 * You are asked to include code that tests the methods of the MovieTheaterApplication class
 * in a similar manner to the sample RunFilmsApplication.java program.
*/


public class RunMovieTheaterApplication
{
    public static void main(String[] args) {
    	
    	Connection connection = null;
    	try {
    	    //Register the driver
    		Class.forName("org.postgresql.Driver"); 
    	    // Make the connection.
            // You will need to fill in your real username (twice) and password for your
            // Postgres account in the arguments of the getConnection method below.
            connection = DriverManager.getConnection(
                                                     "jdbc:postgresql://cse180-db.lt.ucsc.edu/nechawla",
                                                     "nechawla",
                                                     "lemon-lime77");
            
            if (connection != null)
                System.out.println("Connected to the database!");

            /* Include your code below to test the methods of the MovieTheaterApplication class.
             * The sample code in RunFilmsApplication.java should be useful.
             * That code tests other methods for a different database schema.
             * Your code below: */

            MovieTheaterApplication app = new MovieTheaterApplication(connection);
            String thePriceCode = "B"; // setting price code to B
            Integer showings = app.getShowingsCount(thePriceCode); //called getShowingsCount with parameter B
            System.out.print("\n/*\n* Output of getShowingsCount\n* when the parameter thePriceCode is 'B'.\n");
            System.out.print(showings + "\n*/\n"); //output

            int theMovieID = 101; // setting movieID to 101
            String newMovieName = "Avatar 1"; // setting newMovieName to Avatar 1
            int numUpdated = app.updateMovieName(theMovieID, newMovieName); // called updateMovieName with theMovieID and newMovieName parameters
            System.out.print("\n/*\n* Output of updateMovieName\n* when theMovieID is 101\n* and newMovieName is 'Avatar 1'\n");
            System.out.print(numUpdated + "\n*/\n"); //output

            theMovieID = 888; // setting movieID to 888
            newMovieName = "Star Wars: A New Hope"; // setting movieName to star wars: a new hope
            numUpdated = app.updateMovieName(theMovieID, newMovieName); // called updateMovieName with theMovieID and newMovieName parameters
            System.out.print("\n/*\n* Output of updateMovieName\n* when theMovieID is 888\n* and newMovieName is 'Star Wars: A New Hope'\n");
            System.out.print(numUpdated + "\n*/\n"); // output
	    
            int maxTicketCount = 15; // setting maxTicketCount to 15
            int totalReductions = app.reduceSomeTicketPrices(maxTicketCount); // calling reduceSomeTicketPrices with parameter 15
            System.out.print("\n/*\n* Output of reduceSomeTicketPrices\n* when the maxTicketCount is 15\n");
            System.out.print(totalReductions + "\n*/\n"); // output

            maxTicketCount = 99; // setting maxTicketCount to 99
            totalReductions = app.reduceSomeTicketPrices(maxTicketCount); // calling reduceSomeTicketPrices with parameter 99
            System.out.print("\n/*\n* Output of reduceSomeTicketPrices\n* when the maxTicketCount is 99\n");
            System.out.print(totalReductions + "\n*/\n");  // output
            System.out.println(); // new line for cleanliness

            /*******************
            * Your code ends here */
            
    	}
    	catch (SQLException | ClassNotFoundException e) {
    		System.out.println("Error while connecting to database: " + e);
    		e.printStackTrace();
    	}
    	finally {
    		if (connection != null) {
    			// Closing Connection
    			try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Failed to close connection: " + e);
					e.printStackTrace();
				}
    		}
    	}
    }
}
