import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import com.qa.controls.*;
import com.qa.database.*;
import com.qa.objects.*;
import com.qa.tools.*;
import com.qa.coolstore.*;

public class UnitTests {
	private static final Input userIn = new Input();

	/**
	 * Creates sever customer, item, and order objects to add to the database for
	 * use with testing
	 */
	
	final DBCustomer custDB = new DBCustomer();
	final CRUDCustomer cust = new CRUDCustomer(custDB, userIn);
	String forename = "Test";
	String surname = "Person";
	Input input;
	
	@BeforeClass
	public static void instanceObj() {
		SQLConnector.connect();
		try {	
			Connection connection = SQLConnector.getCurrent().getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("CALL `storedb`.`JUNIT_REFRESH`();");
		} catch (Exception e) {
		}
	}
		
	@Test
	public void Customer_Test() {
		Customer customer = custDB.add(new Customer(forename, surname));
		custDB.add(customer);
		assertEquals("", (long) 1, customer.getID());
		assertEquals("","Test", customer.getForename());
		assertEquals("", "Person", customer.getSurname());
		}

}
