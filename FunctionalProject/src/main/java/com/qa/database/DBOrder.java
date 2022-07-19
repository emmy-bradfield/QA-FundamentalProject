package com.qa.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.objects.Order;

public class DBOrder implements DB<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */

	@Override
	public List<Order> viewAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Reads the last order added to the database
	 * 
	 * @return an order
	 */

	@Override
	public Order viewLatest() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Reads a specific order based on the id provided by the user
	 * 
	 * @param ID - the order ID
	 * @return an order
	 */

	@Override
	public Order view(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates an order in the database
	 * 
	 * @param order - takes in an order object, id will be ignored
	 */

	@Override
	public Order add(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Updates an order in the database
	 * 
	 * @param order - takes in an order object, the id field will be used to update
	 *              that order in the database
	 */

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Deletes an item in the database
	 * 
	 * @param id - id of the order
	 */
	
	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Creates a new object Order from the ResultSet data in the SQL Database
	 * 
	 * @param ResultsSet
	 * @throws SQLException
	 * @return Order
	 */

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
