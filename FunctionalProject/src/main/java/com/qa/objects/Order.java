package com.qa.objects;

import java.sql.Date;

public class Order {
	
	/**
	 * Class for constructing an Order object to match the SQL database. 
	 * Includes Getters and Setters to retrieve the object information
	 */
	
	private Long id;
	private Date date;
	private Long custID;
	private Long itemID;
	
	public Order(Long id, Date date, Long custID, Long itemID) {
		this.setId(id);
		this.setDate(date);
		this.setCustID(custID);
		this.setItemID(itemID);
	}
	
	public Order(Date date, Long custID, Long itemID) {
		this.setDate(date);
		this.setCustID(custID);
		this.setItemID(itemID);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getCustID() {
		return custID;
	}

	public void setCustID(Long custID) {
		this.custID = custID;
	}

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((custID == null) ? 0 : custID.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
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
		Order other = (Order) obj;
		if (getDate() == null) {
			if (other.getDate() != null)
				return false;
		} else if (!getDate().equals(other.getDate()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (custID == null) {
			if (other.custID != null)
				return false;
		} else if (!custID.equals(other.custID))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (custID == null) {
			if (other.itemID != null)
				return false;
		} else if (!custID.equals(other.custID))
			return false;
		return true;
	}
	
}
