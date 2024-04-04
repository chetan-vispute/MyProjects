package com.chetan.pojo;

import java.time.LocalDate;

public class Orders {
	int orderId;
	int userId;
	int menuId;
	int quantity;
	float price;
	LocalDate date;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "orderId = " + orderId + ", userId = " + userId + ", menuId = " + menuId + ", quantity = " + quantity
				+ ", price = " + price + ", date = " + date;
	}
	
	
}
