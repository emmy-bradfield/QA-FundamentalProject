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

import com.qa.objects.Item;
import com.qa.tools.SQLConnector;

public class DBItem implements DB<Item>{
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	/**
	 * Reads all items from the database
	 * 
	 * @return A list of items
	 */

	@Override
	public List<Item> viewAll() {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Items");) {
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	/**
	 * Reads the last item added to the database
	 * 
	 * @return an item
	 */

	@Override
	public Item viewLatest() {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Items ORDER BY itemID DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Reads a specific item based on the id provided by the user
	 * 
	 * @param ID - the item ID
	 * @return an item
	 */

	@Override
	public Item view(Long id) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Items WHERE itemID = ?");) {
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
	 * Creates an item in the database
	 * 
	 * @param item - takes in an item object. id will be ignored
	 */

	@Override
	public Item add(Item item) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO Items (itemName, itemCost) VALUES (?, ?)");) {
			statement.setString(1, item.getName());
			statement.setDouble(2, item.getCost());
			statement.executeUpdate();
			return viewLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Updates an item in the database
	 * 
	 * @param item - takes in an item object, the id field will be used to
	 *                 update that item in the database
	 */


	@Override
	public Item update(Item item) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE Items SET itemName = ?, itemCost = ? WHERE itemID = ?");) {
			statement.setString(1, item.getName());
			statement.setDouble(2, item.getCost());
			statement.setLong(3, item.getId());
			statement.executeUpdate();
			return view(item.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Deletes an item in the database
	 * 
	 * @param id - id of the item
	 */

	@Override
	public int delete(long id) {
		try (Connection connection = SQLConnector.getCurrent().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM Items WHERE itemID = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * Creates a new object Item from the ResultSet data in the SQL Database
	 * 
	 * @param ResultSet
	 * @throws SQLException
	 * @return Item
	 */

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("itemID");
		String name = resultSet.getString("itemName");
		Double cost = resultSet.getDouble("itemCost");
		return new Item(id, name, cost);
	}

}