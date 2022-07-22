package com.qa.coolstore.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
/**
 * Confirms proper overwriting of the hashcode and equals functions
 *
 */
public class ItemTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}

}
