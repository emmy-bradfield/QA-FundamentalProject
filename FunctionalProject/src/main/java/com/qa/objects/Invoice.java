package com.qa.objects;

import java.sql.Date;

public class Invoice {
	
	/**
	 * Class for constructing an Invoice object to match the SQL database. 
	 * Includes Getters and Setters to retrieve the object information
	 */
	
	private Long OrderID;
	private String Forename;
	private String Surname;
	private Date OrderDate;
	private Double OrderTotal;
	
	public Invoice(Long OrderID, String Forename, String Surname, Date OrderDate, Double OrderTotal) {
		this.setOrderID(OrderID);
		this.setForename(Forename);
		this.setSurname(Surname);
		this.setOrderDate(OrderDate);
		this.setOrderTotal(OrderTotal);
	}

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}

	public String getForename() {
		return Forename;
	}

	public void setForename(String forename) {
		Forename = forename;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public Double getOrderTotal() {
		return OrderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		OrderTotal = orderTotal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((OrderID == null) ? 0 : OrderID.hashCode());
		result = prime * result + ((Forename == null) ? 0 : Forename.hashCode());
		result = prime * result + ((Surname == null) ? 0 : Surname.hashCode());
		result = prime * result + ((OrderDate == null) ? 0 : OrderDate.hashCode());
		result = prime * result + ((OrderTotal == null) ? 0 : OrderTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (OrderID == null) {
			if (other.OrderID != null)
				return false;
		} else if (!OrderID.equals(other.OrderID))
			return false;
		if (Forename == null) {
			if (other.Forename != null)
				return false;
		} else if (!Forename.equals(other.Forename))
			return false;
		if (Surname == null) {
			if (other.Surname != null)
				return false;
		} else if (!Surname.equals(other.Surname))
			return false;
		if (OrderDate == null) {
			if (other.OrderDate != null)
				return false;
		} else if (!OrderDate.equals(other.OrderDate))
			return false;
		if (OrderTotal == null) {
			if (other.OrderTotal != null)
				return false;
		} else if (!OrderTotal.equals(other.OrderTotal))
			return false;
		return true;
	}

}
