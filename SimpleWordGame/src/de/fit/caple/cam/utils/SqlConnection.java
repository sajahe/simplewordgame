package de.fit.caple.cam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Wrapper for SQL Connections
 * used for Querying the CAM database
 *  
 * @author Martin Friedrich
 * 04.01.2011
 * 
 */
public class SqlConnection {
	private Connection connection = null;

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * creates a new Sql Connection with the following parameters
	 * example call: new SqlConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/MyDb", "username", "password");
	 * 
	 * @param dbDriverClassname 
	 * @param dbUrl 
	 * @param dbUsername 
	 * @param dbPassword 
	 */
	public SqlConnection(String dbDriverClassname, String dbUrl, String dbUsername, String dbPassword) {
		try {
			Class.forName(dbDriverClassname);
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Database driver not found: " + dbDriverClassname + e);
		} catch (SQLException e) {
			System.out.println("Error creating new DB connection: Driver: " +
					dbDriverClassname + " Url: " + dbUrl + " User: " + dbUsername + 
                    " Password: " + dbPassword + e);
		}
	}
	    
	// Destructor to close connection
	protected void finalize ()  {
        close();
    }
	
	/**
	 * to close the database connection
	 */
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Unable to close database connection!" + e);
		}
	}
}
