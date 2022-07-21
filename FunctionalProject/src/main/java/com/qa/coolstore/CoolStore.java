package com.qa.coolstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.controls.Action;
import com.qa.controls.CRUD;
import com.qa.controls.CRUDCustomer;
import com.qa.controls.CRUDInvoice;
import com.qa.controls.CRUDItems;
import com.qa.controls.CRUDOrders;
import com.qa.controls.Table;
import com.qa.database.DBCustomer;
import com.qa.database.DBInvoice;
import com.qa.database.DBItem;
import com.qa.database.DBOrder;
import com.qa.tools.Input;
import com.qa.tools.SQLConnector;

public class CoolStore {
	
	/**
	 * Attributes and constructors
	 */

	public static final Logger LOGGER = LogManager.getLogger();
	private final Input userIn;
	private final CRUDCustomer customers;
	private final CRUDItems items;
	private final CRUDOrders orders;
	public final CRUDInvoice invoices;


	public CoolStore() {
		this.userIn = new Input();
		final DBCustomer custDB = new DBCustomer();
		final DBItem itemDB = new DBItem();
		final DBOrder orderDB = new DBOrder();
		final DBInvoice invoiceDB = new DBInvoice();
		this.customers = new CRUDCustomer(custDB, userIn);
		this.items = new CRUDItems(itemDB, userIn);
		this.orders = new CRUDOrders(orderDB, userIn);
		this.invoices = new CRUDInvoice(invoiceDB, userIn, orderDB);
	}

	/**
	 * Starts the software running, greets the user, and asks which Table they wish
	 * to interact with
	 */

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

	/**
	 * Informs the system which table to interact with, and asks the user to input
	 * their chosen action to then execute to the table
	 * 
	 * @param table - the table they chose from startSystem()
	 */

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
				active = this.orders;
				break;
			case INVOICES:
				active = this.invoices;
				break;
			case STOP:
				return;
			default:
				break;
			}

			LOGGER.info(() -> "What would you like to do with " + table.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction(userIn);

			if (action == Action.RETURN) {
				changeTable = true;
			} else {
				doAction(active, action);
			}
		} while (!changeTable);
	}

	/**
	 * Communicates with the CRUD class to execute the action as specified by the
	 * user to the previously determined table
	 * 
	 * @param CRUD - the table to which they will execute an action
	 * @param action - the action they wish to execute
	 */

	public void doAction(CRUD<?> crud, Action action) {
		switch (action) {
		case ADD:
			crud.add();
			break;
		case VIEW:
			crud.view();
			break;
		case UPDATE:
			crud.update();
			break;
		case DELETE:
			crud.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}
	
}
