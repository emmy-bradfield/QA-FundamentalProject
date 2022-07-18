package com.qa.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.qa.objects.Item;

public class DBItem implements DB<Item>{

	@Override
	public List<Item> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item view(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item add(Item t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item update(Item t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
