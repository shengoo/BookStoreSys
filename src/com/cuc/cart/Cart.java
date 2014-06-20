package com.cuc.cart;

import java.util.ArrayList;
import java.util.Iterator;

public class Cart {
	private ArrayList<CartItem> cart;

	public Cart() {
		cart = new ArrayList<CartItem>();
	}

	public void addCartItem(CartItem item) {
		CartItem oldItem = null;
		for (int i = 0; i < cart.size(); i++) {
			oldItem = cart.get(i);
			if (oldItem.getGoodId().equals(item.getGoodId())) {
				oldItem.setNumber(oldItem.getNumber() + item.getNumber());
				return;
			}
		}
		cart.add(item);
	}
	public void changeNumber(String goodId,int number) {
		CartItem oldItem = null;
		for (int i = 0; i < cart.size(); i++) {
			oldItem = cart.get(i);
			if (oldItem.getGoodId().equals(goodId)) {
				oldItem.setNumber(number);
				return;
			}
		}
	}

	public void removeCartItem(String goodId) {
		CartItem oldItem = null;
		for (int i = 0; i < cart.size(); i++) {
			oldItem = cart.get(i);
			if (oldItem.getGoodId().equals(goodId)) {
				cart.remove(i);
			}
		}
	}

	public ArrayList<CartItem> getCart() {
		return cart;
	}

	public double getTotal() {
		Iterator<CartItem> it = cart.iterator();
		double sum = 0.0;
		CartItem item = null;
		while (it.hasNext()) {
			item = it.next();
			sum = sum + item.getSum();
		}
		return sum;
	}
}
