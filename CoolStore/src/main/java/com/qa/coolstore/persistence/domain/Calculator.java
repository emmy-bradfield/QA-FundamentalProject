package com.qa.coolstore.persistence.domain;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.coolstore.persistence.dao.ItemDAO;
import com.qa.coolstore.persistence.dao.OrderDAO;
import com.qa.coolstore.utils.Utils;

public class Calculator {
	public static final Logger LOGGER = LogManager.getLogger();
	private static OrderDAO orderDAO;
	private static ItemDAO itemDAO;
	public static Scanner scan = new Scanner(System.in);
	static Long id;
	
	public Calculator(Utils util) {
		calculator(util);
	}
	
	public static double calculator(Utils util) {
		LOGGER.info("Please enter the reference number for the order you wish to cost");
		Long id = util.getLong();
		Double total = 0D;
		for (Order o : orderDAO.readAll()) {
			if (o.getRef() == id) {
				Long refID = o.getItemID();
				Long quant = o.getItemAmount();
				List<Item> items = itemDAO.readAll();
				for (Item item : items) {
					if (item.getId() == refID) {
						Double cost = item.getCost();
						total = total + (cost * quant);
					}
				}
			}
		}
		LOGGER.info("The total cost for order ref: " + id + " is £" + total);
		return total;
	}

}