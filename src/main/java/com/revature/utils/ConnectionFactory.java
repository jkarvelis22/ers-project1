package com.revature.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;

public class ConnectionFactory {
	
	private static ConnectionFactory cf = new ConnectionFactory();
	
	private ConnectionFactory() {
		super();
	}
	
	public static ConnectionFactory getInstance() {
		return cf;
	}
	
	public Connection getConnection() {
		
		Connection conn = null;
		
		// We use a .properties file so we do not hard-code our DB credentials into the source code
		Properties prop = new Properties();
		
		try {
			
			DriverManager.registerDriver(new OracleDriver());
			
			// Load the properties file (application.properties) keys/values into the Properties object
			prop.load(new FileReader("C:/Users/jkarv/repos/ers-project1/src/main/resources/application.properties"));
			
			// Get a connection from the DriverManager class
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pw"));
			
		} catch (SQLException sqle) {
//			log.error(sqle.getMessage());
			sqle.printStackTrace();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			
//			log.error(ioe.getMessage());
			ioe.printStackTrace();
		}
		
		//if (conn == null) System.out.println("Connection object is null");
		return conn;
	}

}
