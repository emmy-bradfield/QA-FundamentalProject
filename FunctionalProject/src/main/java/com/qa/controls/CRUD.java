package com.qa.controls;

import java.util.List;

public interface CRUD <T> {
	
	T add();
	
	List<T> view();
	
	int delete();
	
	T update();

}
