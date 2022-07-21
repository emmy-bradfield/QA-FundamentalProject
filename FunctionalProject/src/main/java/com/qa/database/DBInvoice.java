package com.qa.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.objects.Invoice;
import com.qa.tools.SQLConnector;

public class DBInvoice implements DB<Invoice> {

	public DBItem itemdb;

	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Reads all invoices from the database
	 * 
	 * @throws SQLException
	 * @return invoices - a list of invoices
	 */

	@Override
	public List<Invoice> viewAll() {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Invoices");) {
			List<Invoice> invoices = new ArrayList<>();
			while (resultSet.next()) {
				Invoice invoice = modelFromResultSet(resultSet);
				string(invoice);
				invoices.add(invoice);
			}
			return invoices;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	/**
	 * Reads the last invoice added to the database
	 * 
	 * @throws Exception
	 * @return invoice - an invoice
	 */

	@Override
	public Invoice viewLatest() {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Invoices ORDER BY orderID DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Reads a specific invoice based on the id provided by the user
	 * 
	 * @throws Exception
	 * @param ID - the invoice ID
	 * @return invoice - an invoice
	 */

	@Override
	public Invoice view(Long id) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM Invoices WHERE OrderID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				Invoice invoice = modelFromResultSet(resultSet);
				string(invoice);
				return invoice;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Adds an invoice to the database - function is empty
	 */

	@Override
	public Invoice add(Invoice invoice) {

		return null;
	}

	/**
	 * Calculates and prints the cost of an invoice
	 * 
	 * @param invoice - an invoice
	 */

	@Override
	public Invoice update(Invoice invoice) {
		Long id = invoice.getOrderID();
		Double cost = findCost(id);
		System.out.print("The total cost for the order is " + cost + "\n Full Order Details: ");

		return null;
	}

	/**
	 * Deletes an item/transaction from the invoice
	 * 
	 * @throws Exception
	 * @param id - id of the transaction
	 */

	@Override
	public int delete(long id) {
		System.out.println("Removing transaction from invoice:");
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM Orders WHERE orderID = ?");) {
			statement.setLong(1, id);
			statement.executeUpdate();
			LOGGER.info("Order deleted, invoice updated to reflect change");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * Creates a new object Invoice from the ResultSet data in the SQL Database
	 * 
	 * @param ResultSet - the result set from the SQL Query
	 * @throws SQLException
	 * @return Invoice - an invoice object
	 */

	@Override
	public Invoice modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("OrderID");
		String firstName = resultSet.getString("Forename");
		String surname = resultSet.getString("Surname");
		Date date = resultSet.getDate("OrderDate");
		Double total = resultSet.getDouble("OrderTotal");
		return new Invoice(id, firstName, surname, date, total);
	}

	/**
	 * Creates a string of the information stored in object invoice to print
	 * 
	 * @param invoice - an invoice object
	 */

	@Override
	public void string(Invoice invoice) {
		String str = "Invoice ID #0072-" + invoice.getOrderID() + " | References a purchase by " + invoice.getForename()
				+ " " + invoice.getSurname() + " on " + invoice.getOrderDate() + " totalling " + invoice.getOrderTotal()
				+ " GBP";
		System.out.println(str);
	}

	/**
	 * Calculates the cost of an invoice (collection of transactions made by the
	 * same customer on the same day with the same purchase ID) and prints it
	 * 
	 * @throws Exception
	 * @param id - the id of the invoice
	 * @return cost - a double containing the cost of the invoice
	 */

	public Double findCost(Long id) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM Invoices WHERE OrderID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				Invoice invoice = modelFromResultSet(resultSet);
				Double cost = invoice.getOrderTotal();
				LOGGER.info("The total cost for the order is " + cost);
				return cost;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

}