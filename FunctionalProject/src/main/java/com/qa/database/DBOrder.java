package com.qa.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.qa.objects.Order;

public class DBOrder implements DB<Order>{

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order view(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order add(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
