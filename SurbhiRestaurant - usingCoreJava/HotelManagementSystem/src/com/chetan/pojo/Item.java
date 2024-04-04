package com.chetan.pojo;

public class Item {
	private int menuId;
	private String itemName;
	private int stock;
	private float price;
	private int rating;
	
	public Item() {}
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "menuId=" + menuId + ", itemName=" + itemName + ", stock=" + stock + ", price=" + price
				+ ", rating=" + rating ;
	} 
	
	
	
	
}
