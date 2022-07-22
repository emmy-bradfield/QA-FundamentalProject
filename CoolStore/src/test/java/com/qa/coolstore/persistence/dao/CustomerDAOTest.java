package com.qa.coolstore.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.coolstore.persistence.domain.Customer;
import com.qa.coolstore.utils.DBUtils;

/**
 * Confirms that the data given in the test matches the data pulled from the
 * database, ensuring the CustomerDAO accurately updates the database, and reads
 * from it, rather than simply creating new objects
 * 
 *
 */
public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	/**
	 * Connects to the database and triggers a scheme to inject a new customer and
	 * new item entry
	 */
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/CoolStoreDB.sql", "src/test/resources/CoolStoreData.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(2L, "chris", "perrins");
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Customer(ID, "jordan", "harrison"), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}