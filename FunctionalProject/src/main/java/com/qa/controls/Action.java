package com.qa.controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.tools.Input;

public enum Action {

	ADD("Create a new entry"), UPDATE("Change an existing entry"), DELETE("Remove an entry"), VIEW("View all entries"), RETURN("Exit the system");

	public static final Logger LOGGER = LogManager.getLogger();
	private String description;

	Action(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + " - " + this.description;
	}

	public static void printActions() {
		for (Action action : Action.values()) {
			LOGGER.info(action.getDescription());
		}
	}

	public static Action getAction(Input userIn) {
		Action action = null;
		do {
			try {
				action = Action.valueOf(userIn.getString().toUpperCase());
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		} while (action == null);
		return action;
	}

}
