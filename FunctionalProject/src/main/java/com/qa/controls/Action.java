package com.qa.controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.tools.Input;

/**
 * Action is a collection of commands which are used to determine the type of
 * function to apply to an entity.
 *
 */

public enum Action {

	ADD("Create a new entry"), UPDATE("Change an existing entry"), DELETE("Remove an entry"), VIEW("View all entries"),
	RETURN("Exit the system");

	public static final Logger LOGGER = LogManager.getLogger();
	private String description;

	Action(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */

	public String getDescription() {
		return this.name() + " - " + this.description;
	}

	/**
	 * Prints out all of the actions available to the user
	 */

	public static void printActions() {
		for (Action action : Action.values()) {
			LOGGER.info(action.getDescription());
		}
	}

	/**
	 * Returns an action based on user input. If the input does not match a valid
	 * action, method will display an error and ask for another input
	 * 
	 * @return Action type
	 */

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
