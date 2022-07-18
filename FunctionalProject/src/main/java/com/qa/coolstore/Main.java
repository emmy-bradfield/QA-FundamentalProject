package com.qa.coolstore;

import com.qa.database.SQLConnector;
import com.qa.database.Test;

public class Main {

	public static void main(String[] args) {
		SQLConnector connector = new SQLConnector("root","Apple.Bob1");
		connector.connectDB();
		Test test2 = new Test();
		test2.addCustomer(connector);
		
	}

}
