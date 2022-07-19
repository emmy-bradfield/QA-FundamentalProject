package com.qa.controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.tools.Input;

/**
 * Table is a collection of commands which are used to determine table in which
 * an entity is stored or an action should be executed to.
 *
 */

public enum Table {
	CUSTOMERS("Add, remove, update, and view all customers in the database"),
	ITEMS("Add, remove, update, and view all items sold"),
	ORDERS("Add, remove, update, cost, and delete all placed orders"), STOP("To exit the system");

	private String description;

	private static final Logger LOGGER = LogManager.getLogger();

	private Table(String description) {
		this.description = description;
	}

	/**
	 * Describes the table
	 */

	public String getDescription() {
		return this.name() + " - " + this.description;
	}

	/**
	 * Prints out all of the tables available to the user
	 */

	public static void printTables() {
		for (Table table : Table.values()) {
			LOGGER.info(table.getDescription());
		}
	}
	
	/**
	 * Returns a table based on user input. If the input does not match a valid
	 * table, method will display an error and ask for another input
	 * 
	 * @return Table
	 */

	public static Table getTable(Input userIn) {
		Table table;
		while (true) {
			try {
				table = Table.valueOf(userIn.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection, please try again");
			}
		}
		return table;
	}
}
