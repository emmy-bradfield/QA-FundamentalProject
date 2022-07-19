package com.qa.controls;

import java.util.List;

import com.qa.database.DBOrder;
import com.qa.objects.Order;
import com.qa.tools.Input;

public class CRUDOrders implements CRUD<Order>{

	/**
	 * Takes in order details to then execute CRUD commands with
	 */
	
	public CRUDOrders(DBOrder orderDB, Input userIn) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Adds a new order to the database
	 */

	@Override
	public Order add() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Allows a user to view all orders in the database, printed through the logger
	 * 
	 * @return orders
	 */

	@Override
	public List<Order> view() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Allows for an order to be deleted from the database based on the ID given
	 */

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Allows for an order to be updated in the database based on ID provided, and
	 * returns the new order details
	 * 
	 * @return order
	 */

	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

}
