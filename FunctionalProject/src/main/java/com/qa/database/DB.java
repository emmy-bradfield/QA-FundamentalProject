package com.qa.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Interface for persisting CRUD controls to the database for the table
 * (generic) as given by the user
 *
 * @param <T>
 */

public interface DB<T> {

	List<T> viewAll();

	T viewLatest();

	T view(Long id);

	T add(T t);

	T update(T t);

	int delete(long id);

	T modelFromResultSet(ResultSet resultSet) throws SQLException;

}
