package com.qa.controls;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.database.DBCustomer;
import com.qa.objects.Customer;
import com.qa.tools.Input;

/**
 * Takes in customer details to then execute CRUD commands with
 */

public class CRUDCustomer implements CRUD<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	private DBCustomer dbcustomer;
	private Input input;

	public CRUDCustomer(DBCustomer dbcustomer, Input input) {
		super();
		this.dbcustomer = dbcustomer;
		this.input = input;
	}

	public CRUDCustomer() {
	}

	/**
	 * Adds a new customer to the database
	 */

	@Override
	public Customer add() {
		LOGGER.info("Please enter a first name");
		String forename = input.getString();
		LOGGER.info("Please enter a surname");
		String surname = input.getString();
		Customer customer = dbcustomer.add(new Customer(forename, surname));
		LOGGER.info("Customer created");
		return customer;
	}

	/**
	 * Allows a user to view all customers in the database, printed through the
	 * logger
	 * 
	 * @return customers
	 */

	@Override
	public List<Customer> view() {
		List<Customer> customers = dbcustomer.viewAll();
		return customers;
	}

	/**
	 * Allows for a customer to be deleted from the database based on the ID given
	 */

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = input.getLong();
		return dbcustomer.delete(id);
	}

	/**
	 * Allows for a customer to be updated in the database based on ID provided, and
	 * returns the new customer details
	 * 
	 * @return customer
	 */

	@Override
	public Customer update() {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = input.getLong();
		LOGGER.info("Please enter a first name");
		String firstName = input.getString();
		LOGGER.info("Please enter a surname");
		String surname = input.getString();
		Customer customer = dbcustomer.update(new Customer(id, firstName, surname));
		LOGGER.info("Customer Updated");
		return customer;
	}

}
