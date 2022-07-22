package com.qa.coolstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.coolstore.controller.Action;
import com.qa.coolstore.controller.CalcController;
import com.qa.coolstore.controller.CrudController;
import com.qa.coolstore.controller.CustomerController;
import com.qa.coolstore.controller.ItemController;
import com.qa.coolstore.controller.OrderController;
import com.qa.coolstore.persistence.dao.CustomerDAO;
import com.qa.coolstore.persistence.dao.ItemDAO;
import com.qa.coolstore.persistence.dao.OrderDAO;
import com.qa.coolstore.persistence.domain.Domain;
import com.qa.coolstore.utils.DBUtils;
import com.qa.coolstore.utils.Utils;

/**
 * Takes user input and delegates to appropriate class to execute actions and
 * access the database
 *
 */
public class CoolStore {

	public static final Logger LOGGER = LogManager.getLogger();

	private final CustomerController customers;
	private final ItemController items;
	private final OrderController orders;
	private final Utils utils;
	private final CalcController calc;

	public CoolStore() {
		this.utils = new Utils();
		final CustomerDAO custDAO = new CustomerDAO();
		final ItemDAO itemDAO = new ItemDAO();
		final OrderDAO orderDAO = new OrderDAO();
		this.customers = new CustomerController(custDAO, utils);
		this.items = new ItemController(itemDAO, utils);
		this.orders = new OrderController(orderDAO, utils);
		this.calc = new CalcController();
	}

	/**
	 * Asks the user which domain they wish to access then feeds the input to the
	 * domainAction() function
	 */
	public void imsSystem() {
		LOGGER.info("Welcome to the Inventory Management System!");
		DBUtils.connect();

		Domain domain = null;
		do {
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();

			domain = Domain.getDomain(utils);

			if (domain == Domain.CALCULATOR) {
				calc.calculate();
			} else {

				domainAction(domain);
			}

		} while (domain != Domain.STOP);
	}

	/**
	 * Takes user input to determine what action to execute within that doman, then
	 * feeds to the doAction() function
	 * 
	 * @param domain
	 */
	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
			case CUSTOMER:
				active = this.customers;
				break;
			case ITEM:
				active = this.items;
				break;
			case ORDER:
				active = this.orders;
				break;
			case STOP:
				return;
			default:
				break;
			}

			LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction(utils);

			if (action == Action.RETURN) {
				changeDomain = true;
			} else {
				doAction(active, action);
			}
		} while (!changeDomain);
	}

	/**
	 * Instigates the appropriate CRUD Controller based on the domain and action
	 * input from the user
	 * 
	 * @param crudController
	 * @param action
	 */
	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}

}
