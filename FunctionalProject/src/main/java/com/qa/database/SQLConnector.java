package com.qa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SQLConnector {

	static Logger LOGGER = LogManager.getLogger();

	// Attributes
	String localhost;
	String user;
	String password;
	private static SQLConnector connection;

	// Constructors
	public SQLConnector() {
		this.localhost = "jdbc:mysql://localhost:3306/storeDB";
		this.user = "root";
		this.password = "Apple.Bob1";
	}
	
	public SQLConnector(String user, String password) {
		this.localhost = "jdbc:mysql://localhost:3306/storeDB";
		this.user = user;
		this.password = password;
	}
	
	public static SQLConnector connect() {
		connection = new SQLConnector();
		return connection;
		
	}

	/* public static Connection connect() {
		Connection useDB = null;
		try {
			useDB = DriverManager.getConnection(localhost, user, password);
			System.out.println("Connected successfully");
		} catch (SQLException e) {
			LOGGER.debug("Unable to connect");
			LOGGER.debug(e.getStackTrace());
			e.printStackTrace();
		}
		return useDB;
	} */

}
