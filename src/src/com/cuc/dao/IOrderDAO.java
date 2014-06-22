package com.cuc.dao;

import java.util.ArrayList;
import java.util.Date;

import com.cuc.model.Order;

public interface IOrderDAO {
	public int insert(Order order);
	public ArrayList<Order> searchAll();
	public ArrayList searchOrderInfo(int orderid);
	public int getOrderCount();
	public int getOrderCount(int userid);
	public ArrayList<Order> GetUserOrders(int userid,int skip,int take,Date startDate,Date endDate);
	public ArrayList<Order> GetAllOrders(int skip,int take,Date startDate,Date endDate);
}
