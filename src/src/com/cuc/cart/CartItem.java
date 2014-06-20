package com.cuc.cart;

public class CartItem {
	private String goodId;
	private String goodName;
	private float price;
	private int number;

	public CartItem() {

	}

	public CartItem(String goodId, String goodName, float price, int number) {
		this.number = number;
		this.goodId = goodId;
		this.goodName = goodName;
		this.price = price;
	}

	public String getGoodId() {
		return goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getSum() {
		return this.price * this.number;
	}
}
