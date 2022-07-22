package com.qa.coolstore.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.coolstore.persistence.domain.Item;
import com.qa.coolstore.utils.DBUtils;

/**
 * Confirms that the data given in the test matches the data pulled from the
 * database, ensuring the ItemDAO accurately updates the database, and reads
 * from it, rather than simply creating new objects
 * 
 *
 */
public class ItemDAOTest {
	private final ItemDAO DAO = new ItemDAO();

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
		final Item created = new Item(2L, "crackers", 0.50);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "cheese", 2.50));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(1L, "cheese", 2.50), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(ID, "cheese", 2.50), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "cheese", 2.75);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

}
