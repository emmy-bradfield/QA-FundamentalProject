package com.qa.coolstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	
	public static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {
		CoolStore system = new CoolStore();
		system.startSystem();
		LOGGER.info("Thank you, goodbye!");
	}

}
