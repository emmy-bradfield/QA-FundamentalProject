package com.qa.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.objects.Customer;
import com.qa.tools.SQLConnector;

public class DBCustomer implements DB<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */

	@Override
	public List<Customer> viewAll() {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers");) {
			List<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(modelFromResultSet(resultSet));
			}
			return customers;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	/**
	 * Reads the last customer added to the database
	 * 
	 * @return a customer
	 */

	public Customer viewLatest() {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM Customers ORDER BY customerID DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Reads a specific customer based on the id provided by the user
	 * 
	 * @param ID - the customer ID
	 * @return a customer
	 */

	@Override
	public Customer view(Long id) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM Customers WHERE customerID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */

	@Override
	public Customer add(Customer customer) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO Customers (customerForename, customerSurname) VALUES (?, ?)");) {
			statement.setString(1, customer.getForename());
			statement.setString(2, customer.getSurname());
			statement.executeUpdate();
			return viewLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 */

	@Override
	public Customer update(Customer customer) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE Customers SET customerForename = ?, customerSurname = ? WHERE customerID = ?");) {
			statement.setString(1, customer.getForename());
			statement.setString(2, customer.getSurname());
			statement.setLong(3, customer.getID());
			statement.executeUpdate();
			return view(customer.getID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */

	@Override
	public int delete(long id) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM Customers WHERE customerID = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * Creates a new object Customer from the ResultSet data in the SQL Database
	 * 
	 * @param ResultSet
	 * @throws SQLException
	 * @return Customer
	 */

	@Override
	public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("customerID");
		String firstName = resultSet.getString("customerForename");
		String surname = resultSet.getString("customerSurname");
		return new Customer(id, firstName, surname);
	}

}
