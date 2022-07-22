package com.qa.coolstore.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.coolstore.persistence.dao.OrderDAO;
import com.qa.coolstore.persistence.domain.Order;
import com.qa.coolstore.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}
	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please the ID of the customer who placed the order");
		Long customerID = utils.getLong();
		LOGGER.info("Please enter the ID for the first product");
		Long itemID = utils.getLong();
		LOGGER.info("Please enter the quantity purchased");
		Long itemAmount = utils.getLong();
		LOGGER.info("Please enter the reference for the transaction this purchase relates to");
		Long ref = utils.getLong();
		Order order = orderDAO.create(new Order(customerID, itemID, itemAmount, ref));
		LOGGER.info("Order created");
		return order;
	}
	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please the ID of the order you wish to update");
		Long id = utils.getLong();
		LOGGER.info("Please the ID of the customer who placed the order");
		Long customerID = utils.getLong();
		LOGGER.info("Please enter the ID for the first product");
		Long itemID = utils.getLong();
		LOGGER.info("Please enter the quantity purchased");
		Long itemAmount = utils.getLong();
		LOGGER.info("Please enter the reference for the transaction this purchase relates to");
		Long ref = utils.getLong();
		Order order = orderDAO.update(new Order(id, customerID, itemID, itemAmount, ref));
		LOGGER.info("Order updated");
		return order;
	}
	
	/**
	 * Deletes an existing order by the id of the customer
	 * 
	 * @return 1 - indicates success
	 */

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
