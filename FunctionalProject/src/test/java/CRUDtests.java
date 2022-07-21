import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.controls.*;
import com.qa.database.*;
import com.qa.objects.*;
import com.qa.tools.*;
import com.qa.coolstore.*;

@RunWith(MockitoJUnitRunner.class)
public class CRUDtests {

	/**
	 * Mockito tests for adding, viewing, updating, and deleting customers.
	 */

	@Mock
	private Input input;

	@Mock
	private DBCustomer custDB;

	@InjectMocks
	private CRUDCustomer custCRUD;

	@Test
	public void addCustomer() {
		final String forename = "John", surname = "Doe";
		final Customer created = new Customer(forename, surname);

		Mockito.when(input.getString()).thenReturn(forename, surname);
		Mockito.when(custDB.add(created)).thenReturn(created);

		assertEquals(created, custCRUD.add());

		Mockito.verify(input, Mockito.times(2)).getString();
		Mockito.verify(custDB, Mockito.times(1)).add(created);
	}

	@Test
	public void viewCustomer() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Jane", "Doe"));

		Mockito.when(custDB.viewAll()).thenReturn(customers);

		assertEquals(customers, custCRUD.view());

		Mockito.verify(custDB, Mockito.times(1)).viewAll();
	}

	@Test
	public void updateCustomer() {
		Customer updated = new Customer(1L, "Chris", "Perrins");

		Mockito.when(this.input.getLong()).thenReturn(1L);
		Mockito.when(this.input.getString()).thenReturn(updated.getForename(), updated.getSurname());
		Mockito.when(this.custDB.update(updated)).thenReturn(updated);

		assertEquals(updated, this.custCRUD.update());

		Mockito.verify(this.input, Mockito.times(1)).getLong();
		Mockito.verify(this.input, Mockito.times(2)).getString();
		Mockito.verify(this.custDB, Mockito.times(1)).update(updated);
	}

	@Test
	public void deleteCustomer() {
		final long ID = 1L;

		Mockito.when(input.getLong()).thenReturn(ID);
		Mockito.when(custDB.delete(ID)).thenReturn(1);

		assertEquals(1L, this.custCRUD.delete());

		Mockito.verify(input, Mockito.times(1)).getLong();
		Mockito.verify(custDB, Mockito.times(1)).delete(ID);
	}
	
	/**
	 * Mockito tests for adding, viewing, updating, and deleting items
	 */

	@Mock
	private DBItem itemDB;

	@InjectMocks
	private CRUDItems itemCRUD;

	@Test
	public void addItem() {
		final String name = "mockedItem";
		final Double cost = 1.50;
		final Item created = new Item(name, cost);

		Mockito.when(input.getString()).thenReturn(name);
		Mockito.when(input.getDouble()).thenReturn(cost);
		Mockito.when(itemDB.add(created)).thenReturn(created);

		assertEquals(created, itemCRUD.add());

		Mockito.verify(input, Mockito.times(1)).getString();
		Mockito.verify(input, Mockito.times(1)).getDouble();
		Mockito.verify(itemDB, Mockito.times(1)).add(created);
	}

	@Test
	public void viewItems() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("testItem", 2.19));

		Mockito.when(itemDB.viewAll()).thenReturn(items);

		assertEquals(items, itemCRUD.view());

		Mockito.verify(itemDB, Mockito.times(1)).viewAll();
	}

	@Test
	public void updateItems() {
		Item updated = new Item(1L,"demoItem", 0.99);

		Mockito.when(this.input.getLong()).thenReturn(1L);
		Mockito.when(this.input.getString()).thenReturn(updated.getName());
		Mockito.when(this.input.getDouble()).thenReturn(updated.getCost());
		Mockito.when(this.itemDB.update(updated)).thenReturn(updated);

		assertEquals(updated, this.itemCRUD.update());

		Mockito.verify(this.input, Mockito.times(1)).getLong();
		Mockito.verify(this.input, Mockito.times(1)).getString();
		Mockito.verify(this.input, Mockito.times(1)).getDouble();
		Mockito.verify(this.itemDB, Mockito.times(1)).update(updated);
	}

	@Test
	public void deleteItem() {
		final long ID = 1L;

		Mockito.when(input.getLong()).thenReturn(ID);
		Mockito.when(itemDB.delete(ID)).thenReturn(1);

		assertEquals(1L, this.itemCRUD.delete());

		Mockito.verify(input, Mockito.times(1)).getLong();
		Mockito.verify(itemDB, Mockito.times(1)).delete(ID);
	}

	/**
	 * Mockito tests for adding, viewing, updating, and deleting an order
	 */
	
	@Mock
	private DBOrder orderDB;

	@InjectMocks
	private CRUDOrders orderCRUD;

	@Test
	public void addOrder() {
		final Long pid = 1L;
		Long millis = System.currentTimeMillis();
		final Date date = new Date(millis);
		final Long custID = 1L;
		final Long itemID = 1L;
		final Long amount = 2L;
		final Order created = new Order(pid, date, custID, itemID, amount);

		Mockito.when(input.getLong()).thenReturn(pid, custID, itemID, amount);
		Mockito.when(input.getDate()).thenReturn(date);
		Mockito.when(orderDB.add(created)).thenReturn(created);

		assertEquals(created, orderCRUD.add());

		Mockito.verify(input, Mockito.times(4)).getLong();
		Mockito.verify(input, Mockito.times(1)).getDate();
		Mockito.verify(orderDB, Mockito.times(1)).add(created);
	}

	@Test
	public void viewOrders() {
		final Long pid = 1L;
		Long millis = System.currentTimeMillis();
		final Date date = new Date(millis);
		final String name = "mockedItem";
		final Double cost = 1.50;
		final Long amount = 1L;
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, pid, date, name, cost, amount));

		Mockito.when(orderDB.viewAll()).thenReturn(orders);

		assertEquals(orders, orderCRUD.view());

		Mockito.verify(orderDB, Mockito.times(1)).viewAll();
	}

	@Test
	public void updateOrders() {
		Long millis = System.currentTimeMillis();
		final Date date = new Date(millis);
		Order updated = new Order(1L,1L, date, 1L, 1L, 3L);

		Mockito.when(this.input.getLong()).thenReturn(1L, 1L, 1L, 1L, 3L);
		Mockito.when(this.input.getDate()).thenReturn(updated.getDate());
		Mockito.when(this.orderDB.update(updated)).thenReturn(updated);

		assertEquals(updated, this.orderCRUD.update());

		Mockito.verify(this.input, Mockito.times(5)).getLong();
		Mockito.verify(this.input, Mockito.times(1)).getDate();
		Mockito.verify(this.orderDB, Mockito.times(1)).update(updated);
	}

	@Test
	public void deleteOrder() {
		final long ID = 1L;

		Mockito.when(input.getLong()).thenReturn(ID);
		Mockito.when(orderDB.delete(ID)).thenReturn(1);

		assertEquals(1L, this.orderCRUD.delete());

		Mockito.verify(input, Mockito.times(1)).getLong();
		Mockito.verify(orderDB, Mockito.times(1)).delete(ID);
	}
}
