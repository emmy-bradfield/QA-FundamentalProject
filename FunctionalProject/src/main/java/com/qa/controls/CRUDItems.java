package com.qa.controls;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.database.DBCustomer;
import com.qa.database.DBItem;
import com.qa.objects.Customer;
import com.qa.objects.Item;
import com.qa.tools.Input;

public class CRUDItems implements CRUD<Item>{
	
	public static final Logger LOGGER = LogManager.getLogger();

	private DBItem dbitem;
	private Input input;

	public CRUDItems(DBItem itemDB, Input userIn) {
		super();
		this.dbitem = itemDB;
		this.input = userIn;
	}
	
	public CRUDItems() {}

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

	@Override
	public List<Item> view() {
		List<Item> items = dbitem.viewAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long id = input.getLong();
		return dbitem.delete(id);
	}

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
