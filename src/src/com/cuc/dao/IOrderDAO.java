package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Order;

public interface IOrderDAO {
	public int insert(Order order);
	public ArrayList<Order> searchAll();
	public ArrayList searchOrderInfo(int orderid);
}
