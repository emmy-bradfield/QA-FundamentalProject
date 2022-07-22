package com.qa.coolstore.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.coolstore.utils.DBUtils;

public class CalculatorTest {
	private final Calculator calc = new Calculator();

	/**
	 * Connects to the database and triggers a scheme to inject a new customer, new
	 * items, and new orders entries
	 */
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/CoolStoreDB.sql",
				"src/test/resources/CoolStoreData-calcOnly.sql");
	}

	/**
	 * Confirms that given the order ref 1 the calculator returns the expected value
	 */
	@Test
	public void testCalculator() {
		final Long ref = 1L;
		final Double expected = 3.50;

		assertEquals(expected, calc.calculate(ref));
	}
}
