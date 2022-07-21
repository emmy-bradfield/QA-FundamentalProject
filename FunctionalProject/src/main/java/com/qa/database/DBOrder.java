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

import com.qa.objects.Order;
import com.qa.tools.SQLConnector;

public class DBOrder implements DB<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Reads all orders from the database
	 * 
	 * @throws SQLException
	 * @return orders - a list of orders
	 */

	@Override
	public List<Order> viewAll() {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM OrderView");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				Order order = modelFromResultSet(resultSet);
				string(order);
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	/**
	 * Reads the last order added to the database
	 * 
	 * @throws Exception
	 * @return order - an order
	 */

	@Override
	public Order viewLatest() {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM OrderView ORDER BY orderID_itemized DESC LIMIT 1");) {
			resultSet.next();
			Order order = modelFromResultSet(resultSet);
			string(order);
			return order;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Reads a specific order based on the id provided by the user
	 * 
	 * @throws Exception
	 * @param ID - the order ID
	 * @return order - an order
	 */

	@Override
	public Order view(Long id) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM OrderView WHERE orderID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				Order order = modelFromResultSet(resultSet);
				string(order);
				return order;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates an order in the database
	 * 
	 * @throws Exception
	 * @param order - takes in an order object, id will be ignored
	 */

	@Override
	public Order add(Order order) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO Orders (purchaseID, orderDate, fk_customerID, fk_itemID, itemQuantity) VALUES (?, ?, ?, ?, ?)");) {
			statement.setLong(1,  order.getPid());
			statement.setDate(2, order.getDate());
			statement.setLong(3, order.getCustID());
			statement.setLong(4, order.getItemID());
			statement.setLong(5, order.getAmount());
			statement.executeUpdate();
			return viewLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an order in the database
	 * 
	 * @throws Exception
	 * @param order - takes in an order object, the id field will be used to update
	 *              that order in the database
	 * @return view(order) - the details of that order
	 */

	@Override
	public Order update(Order order) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE Orders SET puchaseID = ?, orderDate = ?, fk_customerID = ?, fk_itemID = ? itemQuantity = ? WHERE orderID = ?");) {
			statement.setDate(1, order.getDate());
			statement.setLong(2, order.getCustID());
			statement.setLong(3, order.getItemID());
			statement.setLong(4,  order.getId());
			statement.executeUpdate();
			return view(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an item in the database
	 * 
	 * @throws Exception
	 * @param id - id of the order
	 */
	
	@Override
	public int delete(long id) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM Orders WHERE orderID = ?");) {
			statement.setLong(1, id);
			statement.executeUpdate();
			return 0;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * Creates a new object Order from the ResultSet data in the SQL Database
	 * 
	 * @param ResultsSet - the result set from the SQL statement
	 * @throws SQLException
	 * @return order - a new order
	 */

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("orderID_itemized");
		Long pid = resultSet.getLong("invoiceID");
		Date date = resultSet.getDate("orderDate");
		String item = resultSet.getString("itemName");
		Double cost = resultSet.getDouble("itemCost");
		Long amount = resultSet.getLong("itemQuantity");
		Order order = new Order(id, pid, date, item, cost, amount);
		return order;
	}
	
	/**
	 * Creates a string of the information stored in object order to print
	 * 
	 * @param order - an order object
	 */

	@Override
	public void string(Order order) {
		String str = "ORDER ID #0072-" + order.getId() 
		+ " | Purchase made on " + order.getDate() + " x " + order.getAmount() + " of " + order.getItem() 
		+ " at " + order.getCost() + " each : "
		+ "Total cost: " + (order.getCost()*order.getAmount()) + "GBP";
		System.out.println(str);
	}
}
