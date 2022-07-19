package com.qa.tools;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Date;
import java.time.LocalDate;

public class Input {

	/**
	 * User input/scanner tool to be used in other classes
	 * 
	 * Reads input from the user with the java.util.Scanner class, as well as having
	 * methods to convert the user input to a Long, String, or Double
	 */

	private final Scanner scanner;

	private final Logger LOGGER = LogManager.getLogger();

	public Input() {
		scanner = new Scanner(System.in);
	}

	public Input(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public Long getLong() {
		String input = null;
		Long longInput = null;
		do {
			try {
				input = getString();
				longInput = Long.parseLong(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (longInput == null);
		return longInput;
	}

	public String getString() {
		return scanner.nextLine();
	}

	public Double getDouble() {
		String input = null;
		Double doubleInput = null;
		do {
			try {
				input = getString();
				doubleInput = Double.parseDouble(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (doubleInput == null);
		return doubleInput;
	}
	
	public Date getDate() {
		String input = null;
		Date dateInput = null;
		LocalDate date = null;
		do {
			try {
				input = getString();
				date = LocalDate.parse(input);
				dateInput = Date.valueOf(date);
			} catch (NumberFormatException e) {
				LOGGER.info("Error - Please enter a date");
			}
		} while (dateInput == null);
		return dateInput;
	}

}
