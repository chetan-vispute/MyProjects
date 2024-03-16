package com.chetan.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.chetan.connection.JdbcConnection;
import com.chetan.dao.AdminDao;
import com.chetan.exception.InvalidMenuId;
import com.chetan.logging.ItemLogging;
import com.chetan.pojo.Item;
import com.chetan.util.DisplayResultSet;


public class AdminDaoImpl extends Thread implements AdminDao{
	Connection conn;
	Scanner sc=new Scanner(System.in);
	@Override

	public void run() {
		System.out.println("Thread "+Thread.currentThread().getName()+" is running...");
	}

	@SuppressWarnings("static-access")
	public AdminDaoImpl() {
		conn=JdbcConnection.getConnection().conn;
	}
	//To add item in menu
	@Override
	public boolean addItem() {
		System.out.println("\n________________________________________________");
		System.out.println("-------------------Add Item-------------------");
		try {
			Item item=new Item();
			System.out.println("Enter the item name = ");
			item.setItemName(sc.nextLine());
			System.out.println("Enter the price = ");
			item.setPrice(Float.parseFloat(sc.nextLine()));
			System.out.println("Enter stock available = ");
			item.setStock(Integer.parseInt(sc.nextLine()));
			System.out.println("Enter the rating = ");
			item.setRating(Integer.parseInt(sc.nextLine()));
			try {
				PreparedStatement pst=conn.prepareStatement("insert into item(itemName,price,stock,rating) values(?,?,?,?)");
				pst.setString(1,item.getItemName());
				pst.setFloat(2,item.getPrice());
				pst.setInt(3,item.getStock());
				pst.setInt(4,item.getRating());

				int i=pst.executeUpdate();
				if(i>0) {
					System.out.println("Item added");
					ItemLogging.writeLog("Item added in DB");
					return true;
				}

			}
			catch(SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		catch(NumberFormatException ex) {
			System.out.println("Please try again...");
		}
		ItemLogging.writeLog("Item not added");
		return false;
	}


	//To delete item of the menu
	@Override
	public boolean deleteItem() {
		System.out.println("\n________________________________________________");
		System.out.println("----------------Delete Item-------------------");
		try {
			System.out.println("Enter the menu id for delete = ");
			int id=Integer.parseInt(sc.nextLine());
			PreparedStatement pst=conn.prepareStatement("delete from item where menuId=?");
			pst.setInt(1, id);
			int i=pst.executeUpdate();
			if(i>0) {
				System.out.println("Deleted");
				ItemLogging.writeLog("Item is deleted");
				return true;
			}
			else {
				System.out.println("not deleted");
				ItemLogging.writeLog("Item is not deleted");
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(NumberFormatException ex) {
			System.out.println(ex.getMessage()+" Please trye again");
		}
		return false;
	}


	//To Update Item of the Menu
	@Override
	public boolean updateItem() {
		System.out.println("\n________________________________________________");
		System.out.println("-------------------Update Item---------------------");
		displayItem();
		try {
			System.out.println("Enter the Id = ");
			int id=Integer.parseInt(sc.nextLine());
			Item item=new Item();
			System.out.println("Enter the item name = ");
			item.setItemName(sc.nextLine());
			System.out.println("Enter the price = ");
			item.setPrice(Float.parseFloat(sc.nextLine()));
			System.out.println("Enter stock available = ");
			item.setStock(Integer.parseInt(sc.nextLine()));
			System.out.println("Enter the rating = ");
			item.setRating(Integer.parseInt(sc.nextLine()));
			PreparedStatement pst=conn.prepareStatement("update item set itemName=?,price=?,stock=?,rating=? where menuId=?");
			pst.setString(1, item.getItemName());
			pst.setFloat(2,item.getPrice());
			pst.setInt(3,item.getStock());
			pst.setInt(4,item.getRating());
			pst.setInt(5, id);

			int i=pst.executeUpdate();
			if(i>0) {
				System.out.println("Item Updated");
				ItemLogging.writeLog("Item is updated in DB");
				return true;
			}
			else {
				System.out.println("Item Not update");
				throw new InvalidMenuId();
			}

		}
		catch(InvalidMenuId ex) {
			System.out.println(ex.getMessage());
			ItemLogging.writeLog(ex.getMessage()+"/ Item not updated");

		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(NumberFormatException ne) {
			System.out.println("Please try again...");
		}
		return false;
	}


	//To display the item or Menu
	@Override
	public void displayItem() {
		System.out.println("\n________________________________________________");
		System.out.println("-----------------Display Item------------------");
		try {
			PreparedStatement pst=conn.prepareStatement("select * from item");
			ResultSet rs=pst.executeQuery();
			DisplayResultSet.displayItemCast(rs);	
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}


	//To display today sale
	@Override
	public void displayTodaySale() {
		System.out.println("\n________________________________________________");
		System.out.println("-----------------Today Sale-------------------");
		try {
			PreparedStatement pst=conn.prepareStatement("select * from orders where date=?");
			pst.setDate(1,Date.valueOf(LocalDate.now()));
			ResultSet rs=pst.executeQuery();
			float sum=DisplayResultSet.displayOrderCast(rs);
			if(sum!=0) {
				System.out.println("\nTotal Sale of today = " +sum);
			}
			else {
				System.out.println("\nNo sale today...");
			}

		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}


	//To display month sale
	@Override
	public void displayMonthSale() {
		System.out.println("\n________________________________________________");
		System.out.println("-------------------Month Sale--------------------");
		try {
			PreparedStatement pst=conn.prepareStatement("select * from orders where Month(date)=?");
			pst.setInt(1,LocalDate.now().getMonthValue());
			ResultSet rs=pst.executeQuery();
			float monthSale=DisplayResultSet.displayOrderCast(rs);
			System.out.println("Monthly sale = "+monthSale);
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}


		//Get Item object by passing Id
	@Override
	public Item getItem(int id) {
		try {
			PreparedStatement pst=conn.prepareStatement("select * from item where menuId=?");
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Item item=new Item();
				item.setItemName(rs.getString("itemName"));
				item.setMenuId(rs.getInt("menuId"));
				item.setPrice(rs.getFloat("price"));
				item.setRating(rs.getInt("rating"));
				item.setStock(rs.getInt("stock"));
				return item;
			}

		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

}
