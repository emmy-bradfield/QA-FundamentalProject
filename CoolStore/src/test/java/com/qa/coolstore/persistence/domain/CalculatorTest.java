package com.qa.coolstore.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.coolstore.utils.DBUtils;

public class CalculatorTest {
	private final Calculator calc = new Calculator();
	

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/CoolStoreDB.sql", "src/test/resources/CoolStoreData-calcOnly.sql");
	}
	
	@Test
	public void testCalculator() {
		final Long ref = 1L;
		final Double expected = 3.50;
		
		assertEquals(expected, calc.calculate(ref));
	}
}
