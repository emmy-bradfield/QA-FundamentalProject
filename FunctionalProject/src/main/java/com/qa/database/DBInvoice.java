package com.qa.database;

import java.sql.Connection;
import java.sql.Date;
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

	public static final Logger LOGGER = LogManager.getLogger();

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

	@Override
	public Invoice viewLatest() {

		return null;
	}

	@Override
	public Invoice view(Long id) {

		return null;
	}

	@Override
	public Invoice add(Invoice t) {

		return null;
	}

	@Override
	public Invoice update(Invoice t) {

		return null;
	}

	@Override
	public int delete(long id) {

		return 0;
	}

	@Override
	public Invoice modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("OrderID");
		String firstName = resultSet.getString("Forename");
		String surname = resultSet.getString("Surname");
		Date date = resultSet.getDate("OrderDate");
		Double total = resultSet.getDouble("OrderTotal");
		return new Invoice(id, firstName, surname, date, total);
	}

	@Override
	public void string(Invoice invoice) {
		String str = "Invoice ID #0072-" + invoice.getOrderID() + " | References a purchase by " + invoice.getForename() + " " + invoice.getSurname() +  " on " + invoice.getOrderDate()
				+ " totalling " + invoice.getOrderTotal() + " GBP";
		System.out.println(str);
	}

}