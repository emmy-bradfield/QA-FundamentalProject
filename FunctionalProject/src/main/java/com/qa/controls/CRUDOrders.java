package com.qa.controls;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.database.DBOrder;
import com.qa.objects.Order;
import com.qa.tools.Input;

public class CRUDOrders implements CRUD<Order>{

	/**
	 * Takes in order details to then execute CRUD commands with
	 */
	
	public static final Logger LOGGER = LogManager.getLogger();

	private DBOrder dborder;
	private Input userIn;
	
	public CRUDOrders(DBOrder orderDB, Input userIn) {
		super();
		this.dborder = orderDB;
		this.userIn = userIn;
	}
	
	public CRUDOrders() {};
	
	/**
	 * Adds a new order to the database
	 */

	@Override
	public Order add() {
		LOGGER.info("Please enter the order date");
		Date date = userIn.getDate();
		LOGGER.info("Please enter the ID for the customer who placed the order");
		Long custId = userIn.getLong();
		LOGGER.info("Please enter the ID for the item purchased");
		Long itemId = userIn.getLong();
		Order order = dborder.add(new Order(date, custId, itemId));
		LOGGER.info("Order created");
		return order;
	}
	
	/**
	 * Allows a user to view all orders in the database, printed through the logger
	 * 
	 * @return orders
	 */

	@Override
	public List<Order> view() {
		List<Order> orders = dborder.viewAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	
	/**
	 * Allows for an order to be deleted from the database based on the ID given
	 */

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = userIn.getLong();
		return dborder.delete(id);
	}
	
	/**
	 * Allows for an order to be updated in the database based on ID provided, and
	 * returns the new order details
	 * 
	 * @return order
	 */

	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = userIn.getLong();
		LOGGER.info("Please enter the date it was placed");
		Date date = userIn.getDate();
		LOGGER.info("Please enter the ID of the customer who placed it");
		Long custId = userIn.getLong();
		LOGGER.info("Please enter the ID of the item which was ordered");
		Long itemId = userIn.getLong();
		Order order = dborder.update(new Order(id, date, custId, itemId));
		LOGGER.info("Order Updated");
		return order;
	}

}
