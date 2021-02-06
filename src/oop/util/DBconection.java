package oop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBconection extends Common{
	
	public static Connection connection ;
	
	/** create database connection */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		
		if (connection == null || connection.isClosed()) {
			
			Class.forName(PROPERTIES.getProperty(Constants.DRIVER_NAME));
			
			connection = DriverManager.getConnection(PROPERTIES.getProperty(Constants.URL),
					PROPERTIES.getProperty(Constants.USER_NAME),PROPERTIES.getProperty(Constants.PASSWORD));
		}
		return connection;
		
	}

}
