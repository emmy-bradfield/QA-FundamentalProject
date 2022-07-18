package com.qa.controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.tools.Input;

public enum Table {
	CUSTOMERS("Add, remove, update, and view all customers in the database"),
	ITEMS("Add, remove, update, and view all items sold"),
	ORDERS("Add, remove, update, cost, and delete all placed orders"),
	STOP("To exit the system");

	private String description;

	private static final Logger LOGGER = LogManager.getLogger();

	Table(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + " - " + this.description;
	}

	public static void printTables() {
		for (Table table : Table.values()) {
			LOGGER.info(table.getDescription());
		}
	}

	public static Table getTable(Input userIn) {
		Table table;
		while (true) {
			try {
				table = Table.valueOf(userIn.toString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection, please try that again");
			}
		}
		return table;
	}
}
