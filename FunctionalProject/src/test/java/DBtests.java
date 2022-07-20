import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.database.DBCustomer;
import com.qa.database.DBItem;
import com.qa.objects.Customer;
import com.qa.objects.Item;
import com.qa.tools.SQLConnector;

public class DBtests {

	private final DBCustomer cust = new DBCustomer();

	@Before
	public void setup() {
		SQLConnector.connect();
		SQLConnector.getCurrent().init("src/test/resources/StoreDatabase.sql","src/test/resources/schema.sql");
	}

	@Test
	public void addCust() {
		final Customer created = new Customer("Chris", "Perrins");
		assertEquals(created, cust.add(created));
	}

	@Test
	public void viewAllCust() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "Emily", "Bradfield"));
		assertEquals(expected, cust.viewAll());
	}

	@Test
	public void viewLateCust() {
		assertEquals(new Customer(1L, "Emily", "Bradfield"), cust.viewLatest());
	}
	
	@Test
	public void viewCust() {
		final long ID = 1L;
		assertEquals(new Customer(ID, "Emily", "Bradfield"), cust.view(ID));
	}
	
	@Test
	public void custUp() {
		final Customer updated = new Customer(1L, "Emmy", "Bradfield");
		assertEquals(updated, cust.update(updated));
	}
	
	@Test
	public void custDelete() {
		assertEquals(1, cust.delete(1));
	}
	
	private final DBItem item = new DBItem();

	@Test
	public void addItem() {
		final Item created = new Item("Demo", 1.49);
		assertEquals(created, item.add(created));
	}

	@Test
	public void viewAllItem() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "sample", 1.20));
		assertEquals(expected, item.viewAll());
	}

	@Test
	public void viewLateITem() {
		assertEquals(new Item(1L, "sample", 1.20), item.viewLatest());
	}
	
	@Test
	public void viewItem() {
		final long ID = 1L;
		assertEquals(new Item(ID, "sample", 1.20), item.view(ID));
	}
	
	@Test
	public void iteUp() {
		final Item updated = new Item(1L, "sample", 1.19);
		assertEquals(updated, item.update(updated));
	}
	
	@Test
	public void itemDelete() {
		assertEquals(1, item.delete(1));
	}


}
