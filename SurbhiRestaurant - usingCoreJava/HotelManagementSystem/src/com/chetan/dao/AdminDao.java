package com.chetan.dao;

import com.chetan.pojo.Item;

public interface AdminDao {

	boolean addItem();

	boolean deleteItem();

	boolean updateItem();

	void displayItem();

	void displayTodaySale();

	void displayMonthSale();

	//Get Item object by passing Id
	Item getItem(int id);

}