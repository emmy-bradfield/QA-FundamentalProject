package com.qa.controls;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.database.DBItem;
import com.qa.objects.Item;
import com.qa.tools.Input;

/**
 * Takes in item details to then execute CRUD commands with
 */

public class CRUDItems implements CRUD<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private DBItem dbitem;
	private Input input;

	public CRUDItems(DBItem itemDB, Input userIn) {
		super();
		this.dbitem = itemDB;
		this.input = userIn;
	}

	public CRUDItems() {
	}

	/**
	 * Adds a new item to the database
	 * 
	 * @return item - an item
	 */

	@Override
	public Item add() {
		LOGGER.info("Please enter the product name");
		String name = input.getString();
		LOGGER.info("Please enter the product cost");
		Double cost = input.getDouble();
		Item item = dbitem.add(new Item(name, cost));
		LOGGER.info("Product created");
		return item;
	}

	/**
	 * Allows a user to view all items in the database, printed through the logger
	 * 
	 * @return items - a list of items
	 */

	@Override
	public List<Item> view() {
		List<Item> items = dbitem.viewAll();
		return items;
	}

	/**
	 * Allows for an item to be deleted from the database based on the ID given
	 * 
	 * @ return dbitem.delete(id) - an executed SQL statement
	 */

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long id = input.getLong();
		return dbitem.delete(id);
	}

	/**
	 * Allows for an item to be updated in the database based on ID provided, and
	 * returns the new item details
	 * 
	 * @return item - an item
	 */

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the product you would like to update");
		Long id = input.getLong();
		LOGGER.info("Please enter it's name");
		String name = input.getString();
		LOGGER.info("Please enter a cost in GBP");
		Double cost = input.getDouble();
		Item item = dbitem.update(new Item(id, name, cost));
		LOGGER.info("Product Updated");
		return item;
	}

}
