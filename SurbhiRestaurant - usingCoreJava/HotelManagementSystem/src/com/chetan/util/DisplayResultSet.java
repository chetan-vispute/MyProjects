package com.chetan.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.chetan.exception.MenuNotExist;
import com.chetan.logging.ItemLogging;
import com.chetan.pojo.Item;
import com.chetan.pojo.Orders;

public class DisplayResultSet {

	//To casting of rs to Item Object and print as an ORM
	static public void displayItemCast(ResultSet rs) {
		int i=0;
		try {
			while(rs.next()) {
				Item item=new Item();
				item.setItemName(rs.getString("itemName"));
				item.setMenuId(rs.getInt("menuId"));
				item.setPrice(rs.getFloat("price"));
				item.setRating(rs.getInt("rating"));
				item.setStock(rs.getInt("stock"));
				System.out.println(item.toString());
				i++;
			}
			if(i==0) {
				throw new MenuNotExist();
			}
		}
		catch(MenuNotExist ex) {
			System.out.println(ex.getMessage());
			ItemLogging.writeLog("No Items in Menu");
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}


	//To casting of rs to Orders Object and print as an ORM and return sum of all orders
	static public float  displayOrderCast(ResultSet rs) {
		float sum=0;
		try {
			while(rs.next()) {
				Orders order=new Orders();
				order.setOrderId(rs.getInt("orderId"));
				order.setMenuId(rs.getInt("menuId"));
				order.setUserId(rs.getInt("userId"));
				order.setPrice(rs.getFloat("price"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(LocalDate.parse(rs.getDate("date").toString()) );
				sum=sum+order.getPrice();
				System.out.println(order.toString());
			}
			return sum;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

}
