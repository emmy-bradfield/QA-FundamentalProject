package com.qa.persistence.domain;

import org.junit.Test;

import com.qa.coolstore.persistence.domain.Customer;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

}
