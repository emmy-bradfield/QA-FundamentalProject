package com.qa.coolstore.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.coolstore.persistence.dao.ItemDAO;
import com.qa.coolstore.persistence.domain.Item;
import com.qa.coolstore.utils.Utils;

public class ItemController implements CrudController<Item>{
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please the product name");
		String name = utils.getString();
		LOGGER.info("Please enter the product cost");
		Double cost = utils.getDouble();
		Item item = itemDAO.create(new Item(name, cost));
		LOGGER.info("Product created");
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the name");
		String name = utils.getString();
		LOGGER.info("Please enter the value");
		Double cost = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, cost));
		LOGGER.info("Product Updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}