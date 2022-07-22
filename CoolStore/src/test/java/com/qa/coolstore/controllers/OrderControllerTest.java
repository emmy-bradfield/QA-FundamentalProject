package com.qa.coolstore.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.coolstore.controller.OrderController;
import com.qa.coolstore.persistence.dao.OrderDAO;
import com.qa.coolstore.persistence.domain.Order;
import com.qa.coolstore.utils.Utils;
/**
 * Uses Mockito to ensure that the OrderController executes their CRUD commands
 * through the use of the DAO classes, ensuring communication with the SQL
 * database
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final Long customerID = 1L;
		final Long itemID = 1L;
		final Long itemAmount = 2L;
		final Long ref = 1L;
		final Order created = new Order(customerID, itemID, itemAmount, ref);

		Mockito.when(utils.getLong()).thenReturn(customerID, itemID, itemAmount, ref);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(4)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 2L, 1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Order updated = new Order(1L, 1L, 1L, 3L, 1L);

		Mockito.when(this.utils.getLong()).thenReturn(1L, updated.getCustomerID(), updated.getItemID(), updated.getItemAmount(), updated.getRef());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(5)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
