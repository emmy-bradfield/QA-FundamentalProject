package com.qa.coolstore.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.coolstore.persistence.domain.Order;
import com.qa.coolstore.utils.DBUtils;

public class OrderDAOTest {
	private final OrderDAO DAO = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/CoolStoreDB.sql", "src/test/resources/CoolStoreData-orderOnly.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(2L, 1L, 2L, 2L, 1L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 1L, 1L, 1L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Order(1L, 1L, 1L, 1L, 1L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(ID, 1L, 1L, 1L, 1L), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Order updated = new Order(1L, 1L, 2L, 2L, 1L);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

}

// java.lang.AssertionError: expected:<id: null customer: 1 item: 2 quantity: 2 transaction: 1> but was:<id: 2 customer: 1 item: 2 quantity: 2 transaction: 1>
