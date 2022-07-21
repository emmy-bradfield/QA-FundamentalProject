package com.qa.controls;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.database.DBInvoice;
import com.qa.database.DBOrder;
import com.qa.objects.Invoice;
import com.qa.objects.Order;
import com.qa.tools.Input;

public class CRUDInvoice implements CRUD<Invoice>{
	
	/**
	 * Takes in invoice details to then execute CRUD commands with
	 */

	public static final Logger LOGGER = LogManager.getLogger();

	private DBInvoice dbin;
	private Input userIn;
	private DBOrder dborder;

	public CRUDInvoice(DBInvoice dbin, Input userIn, DBOrder dborder) {
		super();
		this.dbin = dbin;
		this.setUserIn(userIn);
		this.dborder = dborder;
	}

	public CRUDInvoice() {
	};
	
	/**
	 * Adds a new item to the invoice
	 */

	public Invoice add() {
		LOGGER.info("Please enter the puchase ID of the complete transaction you wish to add to");
		Long pid = userIn.getLong();
		LOGGER.info("Please enter the order date");
		Date date = userIn.getDate();
		LOGGER.info("Please enter the ID for the customer who placed the order");
		Long custId = userIn.getLong();
		LOGGER.info("Please enter the ID for the item you wish to add");
		Long itemId = userIn.getLong();
		LOGGER.info("Please enter the quantity of the item purchased");
		Long amount = userIn.getLong();
		dborder.add(new Order(pid, date, custId, itemId, amount));
		LOGGER.info("Item added");
		return null;
	}
	
	/**
	 * Allows a user to view all invoices in the database, printed through the
	 * logger
	 * 
	 * @return invoices - a list of invoices
	 */

	public List<Invoice> view() {
		List<Invoice> invoices = dbin.viewAll();
		return invoices;
	}
	
	/**
	 * Allows for an item to be deleted from the invoice based on the ID given
	 * 
	 * @return dbin.delete(id) - an executed SQL statement
	 */

	@Override
	public int delete() {
		LOGGER.info("Please enter the order id of the transaction you wish to remove from an order");
		Long id = userIn.getLong();
		return dbin.delete(id);
	}
	
	/**
	 * Prints the total cost of an invoice given it's ID
	 */

	@Override
	public Invoice update() {
		LOGGER.info("Please enter the id of the order you would like to cost");
		Long id = userIn.getLong();
		dbin.findCost(id);
		return null;
	}
	
	/**
	 * Getters and Setters
	 */

	public Input getUserIn() {
		return userIn;
	}

	public void setUserIn(Input userIn) {
		this.userIn = userIn;
	}


}
