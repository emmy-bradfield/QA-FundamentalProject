package com.qa.database;

import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;

public class SQLConnector {

	static Logger LOGGER = LogManager.getLogger();

	// Attributes
	String localhost;
	String user;
	String password;

	// Constructors
	public SQLConnector(String properties) {
		Properties dbProps = new Properties();
		try (InputStream fis = ClassLoader.getSystemResourceAsStream(properties)) {
			dbProps.load(fis);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		this.localhost = dbProps.getProperty("db.url", "");
		this.user = dbProps.getProperty("db.user", "");
		this.password = dbProps.getProperty("db.password", "");
	}
	
	public SQLConnector() {
		this("db.properties");
	}
	
	public int init(String... paths) {
		int modified = 0;
		
		for (String path : paths) {
			modified += executeSQLFile(path);
		}
		return modified;
	}
	
	public int executeSQLFile(String file) {
		int modified = 0;
		try (Connection connection = this.getConnection();
				BufferedReader br = new BufferedReader(new FileReader(file));) {
			String fileAsString = br.lines().reduce((acc,  next) -> acc + next).orElse("");
			String[] queries = fileAsString.split(";");
			modified += Stream.of(queries).map(string -> {
				try (Statement statement = connection.createStatement();) {
					return statement.executeUpdate(string);
				} catch (Exception e) {
					LOGGER.debug(e);
					return 0;
				}
			}).reduce((acc, next) -> acc + next).orElse(0);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return modified;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(localhost, user, password);
	}
	
	private static SQLConnector connection;
	
	public static SQLConnector connect() {
		connection = new SQLConnector();
		return connection;
	}
	
	public static SQLConnector connect(String properties) {
		connection = new SQLConnector(properties);
		return connection;
	}
	
	public static SQLConnector getCurrent() {
		if (connection == null) {
			connection = new SQLConnector();
		}
		return connection;
	}

}
