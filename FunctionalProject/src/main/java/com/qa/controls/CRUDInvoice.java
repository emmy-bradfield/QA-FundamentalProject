package com.qa.controls;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.database.DBInvoice;
import com.qa.objects.Invoice;
import com.qa.tools.Input;

public class CRUDInvoice implements CRUD<Invoice>{

	public static final Logger LOGGER = LogManager.getLogger();

	private DBInvoice dbin;
	private Input userIn;

	public CRUDInvoice(DBInvoice dbin, Input userIn) {
		super();
		this.dbin = dbin;
		this.setUserIn(userIn);
	}

	public CRUDInvoice() {
	};

	public Invoice add() {
		
		return null;
	}

	public List<Invoice> view() {
		List<Invoice> invoices = dbin.viewAll();
		return invoices;
	}

	@Override
	public int delete() {
		
		return 0;
	}

	@Override
	public Invoice update() {
		
		return null;
	}

	public Input getUserIn() {
		return userIn;
	}

	public void setUserIn(Input userIn) {
		this.userIn = userIn;
	}


}
