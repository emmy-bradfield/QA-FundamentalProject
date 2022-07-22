package com.qa.coolstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.coolstore.persistence.domain.Calculator;
import com.qa.coolstore.utils.Utils;

public class CalcController {

	private final static Utils utils = new Utils();
	private final static Logger LOGGER = LogManager.getLogger();
	private final static Calculator calc = new Calculator();

	public double calculate() {
		LOGGER.info("Please enter the reference number for the order you wish to cost");
		Long id = utils.getLong();
		Double cost = calc.calculate(id);
		System.out.println("The total cost for order #" + id + " is £" + cost);
		return cost;
	}
}
