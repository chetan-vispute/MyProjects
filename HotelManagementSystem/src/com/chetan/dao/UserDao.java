package com.chetan.dao;

import com.chetan.pojo.Item;

public interface UserDao {


	void showHistoryOrders();

	void showBill();

	boolean displayItem();

	boolean orderItem();

	Item getItem(int id);

}