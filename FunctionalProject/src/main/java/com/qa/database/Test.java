package com.qa.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
	Integer i = 0;
	String customerForename;
	String customerSurname;

	Logger LOGGER = LogManager.getLogger();

	Scanner scan = new Scanner(System.in);
	
	public Integer addCustomer(SQLConnector connector) {
		i = i+1;
		System.out.print("Please enter the forename of the customer you wish to add\n>> ");
		customerForename = scan.nextLine();
		System.out.print("Please enter the surname of the customer you wish to add\n>> ");
		customerSurname = scan.nextLine();
		newCustomer(connector, i);
		return i;
	}
	
	public String getForename() {
		return customerForename;
	}
	
	public String getSurname() {
		return customerSurname;
	}
	
	public void newCustomer(SQLConnector connector, Integer i) {
		String customerID = Integer.toString(i);
		try (Connection con = connector.connectDB();
				PreparedStatement state = con.prepareStatement("INSERT INTO Customers VALUES (?, ?, ?)");) {
				state.setString(1, customerID);
				state.setString(2, getForename());
				state.setString(3, getSurname());
				state.executeUpdate();
			System.out.println(customerForename + " " + customerSurname + " successfully added!");
		} catch (SQLException e) {
			LOGGER.debug("Unable to add");
			LOGGER.debug(e.getStackTrace());
			e.printStackTrace();
		}
		
	}

}
