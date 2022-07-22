package com.qa.coolstore.persistence.domain;

/**
 * Order object, models SQL entity, includes constructors and getters &
 * setters, as well as hashcode, equals, and tostring overwrites
 *
 */
public class Order {

	private Long id;
	private Long customerID;
	private Long itemID;
	private Long itemAmount;
	private Long ref;

	public Order(Long id, Long customerID, Long itemID, Long itemAmount, Long ref) {
		this.setId(id);
		this.setCustomerID(customerID);
		this.setItemID(itemID);
		this.setItemAmount(itemAmount);
		this.setRef(ref);
	}

	public Order(Long customerID, Long itemID, Long itemAmount, Long ref) {
		this.setCustomerID(customerID);
		this.setItemID(itemID);
		this.setItemAmount(itemAmount);
		this.setRef(ref);
	}

	public Order() {
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	@Override
	public String toString() {
		return "id: " + id + " customer: " + customerID + " item: " + itemID + " quantity: " + itemAmount
				+ " transaction: " + ref;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result + ((itemAmount == null) ? 0 : itemAmount.hashCode());
		result = prime * result + ((ref == null) ? 0 : ref.hashCode());
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
		if (getCustomerID() == null) {
			if (other.getCustomerID() != null)
				return false;
		} else if (!getCustomerID().equals(other.getCustomerID()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (itemAmount == null) {
			if (other.itemAmount != null)
				return false;
		} else if (!itemAmount.equals(other.itemAmount))
			return false;
		if (ref == null) {
			if (other.ref != null)
				return false;
		} else if (!ref.equals(other.ref))
			return false;
		return true;
	}

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public Long getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(Long itemAmount) {
		this.itemAmount = itemAmount;
	}

	public Long getRef() {
		return ref;
	}

	public void setRef(Long ref) {
		this.ref = ref;
	}

}
