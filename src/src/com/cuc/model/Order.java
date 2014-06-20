package com.cuc.model;

import java.sql.Date;
import java.util.ArrayList;

import com.cuc.cart.CartItem;

public class Order {
	private int orderid;
	private int webuserid;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String status;
	private Date saletime;
	private ArrayList<CartItem> itemList;
	
	public ArrayList<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<CartItem> itemList) {
		this.itemList = itemList;
	}

	public Date getSaletime() {
		return saletime;
	}

	public void setSaletime(Date saletime) {
		this.saletime = saletime;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getWebuserid() {
		return webuserid;
	}

	public void setWebuserid(int webuserid) {
		this.webuserid = webuserid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
