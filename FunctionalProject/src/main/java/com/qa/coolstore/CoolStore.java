package com.qa.coolstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.controls.CRUD;
import com.qa.controls.CRUDCustomer;
import com.qa.controls.CRUDItems;
import com.qa.controls.CRUDOrders;
import com.qa.controls.Table;
import com.qa.database.SQLConnector;
import com.qa.tools.Input;

public class CoolStore {

	public static final Logger LOGGER = LogManager.getLogger();
	private final Input userIn;
	private final CRUDCustomer customers;
	private final CRUDItems items;
	private final CRUDOrders orders;

	public CoolStore() {
		this.userIn = new Input();
		this.customers = new CRUDCustomer();
		this.items = new CRUDItems();
		this.orders = new CRUDOrders();
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
				break;
			case STOP:
				return;
			default:
				break;
			}

}}}
