package com.qa.coolstore.persistence.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.coolstore.utils.DBUtils;
import com.qa.coolstore.utils.Utils;

public class Calculator {
	public static final Logger LOGGER = LogManager.getLogger();
	public static Scanner scan = new Scanner(System.in);
	static Long id;

	public Calculator(Utils util) {
		calculator(util);
	}

	public static void calculator(Utils util) {
		LOGGER.info("Please enter the reference number for the order you wish to cost");
		Long id = util.getLong();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Calculator WHERE ref = ?");) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			modelFromResultSet(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long ref = resultSet.getLong("ref");
		Double cost = resultSet.getDouble("total");
		System.out.println("The total cost for order #" + ref + " is £" + cost);
	}

}