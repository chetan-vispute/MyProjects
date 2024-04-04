package com.chetan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	private String itemName;
	private String category;
	private int stock;
	private float price;
	private int rating;
	public Item(String itemName, int stock, float price, int rating,String category) {
		super();
		this.itemName = itemName;
		this.stock = stock;
		this.price = price;
		this.rating = rating;
		this.category =category;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public Item(int itemId, String itemName, String category, int stock, float price, int rating) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.category = category;
		this.stock = stock;
		this.price = price;
		this.rating = rating;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
