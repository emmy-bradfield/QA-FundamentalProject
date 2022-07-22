package com.qa.coolstore.persistence.domain;

import java.util.List;

import com.qa.coolstore.persistence.dao.ItemDAO;
import com.qa.coolstore.persistence.dao.OrderDAO;

public class Calculator {
	final OrderDAO orderDAO = new OrderDAO();
	final ItemDAO itemDAO = new ItemDAO();

	public Calculator() {
	}

	public Double calculate(Long id) {
		List<Order> orders = orderDAO.readAll();
		Double total = 0.0;
		for (Order order : orders) {
			if (order.getRef() == id) {
				Long itemID = order.getItemID();
				Long quant = order.getItemAmount();
				Double cost = costItem(itemID);
				total = total + (cost*quant);
			}
		}
		return total;
	}

	public Double costItem(Long itemID) {
		Double value = null;
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			if (item.getId() == itemID) {
				value = item.getCost();
			}
		}
		return value;
	}
}

//	public Double calculate(Long id) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection.prepareStatement("SELECT total FROM Calculator WHERE ref = ?");) {
//			statement.setLong(1, id);
//			ResultSet resultSet = statement.executeQuery();
//			resultSet.next();
//			Double cost = getCost(resultSet);
//			return cost;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0.0;
//
//	}

//	Long getID(Long id) {
//		return id;
//	}
//
//	private static double getCost(ResultSet resultSet) throws SQLException {
//		Double cost = resultSet.getDouble("total");
//		return cost;
//	}
