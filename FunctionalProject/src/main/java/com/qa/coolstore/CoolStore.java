package com.qa.coolstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.controls.Action;
import com.qa.controls.CRUD;
import com.qa.controls.CRUDCustomer;
import com.qa.controls.CRUDItems;
import com.qa.controls.CRUDOrders;
import com.qa.controls.Table;
import com.qa.database.DBCustomer;
import com.qa.database.DBItem;
import com.qa.database.DBOrder;
import com.qa.database.SQLConnector;
import com.qa.tools.Input;

public class CoolStore {

	public static final Logger LOGGER = LogManager.getLogger();
	private final Input userIn;
	private final CRUDCustomer customers;
	private final CRUDItems items;
	//private final CRUDOrders orders;

	public CoolStore() {
		this.userIn = new Input();
		final DBCustomer custDB = new DBCustomer();
		final DBItem itemDB = new DBItem();
		//final DBOrder orderDB = new DBOrder();
		this.customers = new CRUDCustomer(custDB, userIn);
		this.items = new CRUDItems(itemDB, userIn);
		//this.orders = new CRUDOrders(orderDB, userIn);
	}

	public void startSystem() {
		LOGGER.info("Welcome to CoolStore Inventory and Order Management System");
		SQLConnector.connect();

		Table table = null;
		do {
			LOGGER.info("Which entity would you like to use?");
			Table.printTables();

			table = Table.getTable(userIn);

			tableAction(table);

		} while (table != Table.STOP);
	}
	
	private void tableAction(Table table) {
		boolean changeTable = false;
		do {

			CRUD<?> active = null;
			switch (table) {
			case CUSTOMERS:
				active = this.customers;
				break;
			case ITEMS:
				active = this.items;
				break;
			case ORDERS:
				//active = this.orders;
				break;
			case STOP:
				return;
			default:
				break;
			}
			
			LOGGER.info(() ->"What would you like to do with " + table.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction(userIn);

			if (action == Action.RETURN) {
				changeTable = true;
			} else {
				doAction(active, action);
			}
		} while (!changeTable);
	}
	
	public void doAction(CRUD<?> crudController, Action action) {
		switch (action) {
		case ADD:
			crudController.add();
			break;
		case VIEW:
			crudController.view();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}
}
