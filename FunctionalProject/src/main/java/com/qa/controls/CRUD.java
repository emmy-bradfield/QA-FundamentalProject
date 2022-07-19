package com.qa.controls;

import java.util.List;

/**
 * CREATE, READ, UPDATE, DELETE
 * Takes inputs for actions which is then sent to the appropriate class to execute
 */

public interface CRUD <T> {
	
	T add();
	
	List<T> view();
	
	int delete();
	
	T update();

}
