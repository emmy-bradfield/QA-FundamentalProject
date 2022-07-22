package com.qa.coolstore.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;


public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

}
