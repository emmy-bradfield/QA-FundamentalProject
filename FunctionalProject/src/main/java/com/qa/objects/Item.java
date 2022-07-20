package com.qa.objects;

public class Item {
	
	/**
	 * Class for constructing an Item object to match the SQL database. 
	 * Includes Getters and Setters to retrieve the object information
	 */
	
	private Long id;
	private String name;
	private Double cost;
	
	public Item(Long id, String name, Double cost) {
		this.setId(id);
		this.setName(name);
		this.setCost(cost);
	}
	
	public Item(String name, Double cost) {
		this.setName(name);
		this.setCost(cost);
	}

	public Item(Invoice modelFromResultSet) {
		// TODO Auto-generated constructor stub
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Long getID() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
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
		Item other = (Item) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		return true;
	}


}
