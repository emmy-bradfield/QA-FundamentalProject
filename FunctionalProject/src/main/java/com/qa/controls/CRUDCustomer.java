package com.qa.controls;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.database.DBCustomer;
import com.qa.objects.Customer;
import com.qa.tools.Input;

public class CRUDCustomer implements CRUD<Customer>{
	
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

	@Override
	public List<Customer> view() {
		List<Customer> customers = dbcustomer.viewAll();
		for (Customer customer : customers) {
			LOGGER.info(customer);
		}
		return customers;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = input.getLong();
		return dbcustomer.delete(id);
	}

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
