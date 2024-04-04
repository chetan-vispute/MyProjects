package com.chetan.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.chetan.connection.JdbcConnection;
import com.chetan.dao.UserDao;
import com.chetan.exception.InvalidMenuId;
import com.chetan.logging.OrderLogging;
import com.chetan.pojo.Item;
import com.chetan.util.DisplayResultSet;

public class UserDaoImpl extends Thread implements UserDao{
	Connection conn;
	
	Scanner sc =new Scanner(System.in);
	int userId;


	public void run() {
		System.out.println("Thread "+Thread.currentThread().getName()+" is running...");
	}
	@SuppressWarnings("static-access")
	public UserDaoImpl(int id) {
		conn=JdbcConnection.getConnection().conn;
		userId=id;
		
	}
	@Override
	public void showHistoryOrders() {
		System.out.println("\n______________________________________________________");
		System.out.println("--------------------Order History---------------------");
		try {
			PreparedStatement pst=conn.prepareStatement("select * from orders where userId=?");
			pst.setInt(1, userId);

			ResultSet rs=pst.executeQuery();
			DisplayResultSet .displayOrderCast(rs);


		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}


	}
	@Override
	public void showBill() {
		System.out.println("\n__________________________________________________________");
		System.out.println("-----------------------Today Bill--------------------------");
		try {
			PreparedStatement pst=conn.prepareStatement("select * from orders where userId=? and date=?");
			pst.setInt(1, userId);
			pst.setDate(2, Date.valueOf(LocalDate.now()));
			ResultSet rs=pst.executeQuery();
			float amount=DisplayResultSet.displayOrderCast(rs);
			System.out.println("Total Amount = "+amount);

		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}
	@Override

	public boolean displayItem() {
		System.out.println("\n________________________________________________");
		System.out.println("-----------------Display Item------------------");
		try {
			PreparedStatement pst=conn.prepareStatement("select * from item");
			ResultSet rs=pst.executeQuery();
			DisplayResultSet.displayItemCast(rs);
			return true;
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;

	}
	@Override
	public boolean orderItem() {
		System.out.println("\n______________________________________________________");
		System.out.println("------------------------Add Order------------------------");
		try {
			int id,oQuantity;
			displayItem();
			System.out.println("Enter menu id = ");
			id=Integer.parseInt(sc.nextLine());
			System.out.println("Enter no of quantity =");
			oQuantity=Integer.parseInt(sc.nextLine());
			Item item =getItem(id);
			if(item!=null) {
				int nQuant=item.getStock()-oQuantity;
				if(nQuant>=0) {
					PreparedStatement pst=conn.prepareStatement("insert into orders(userId,menuId,price,quantity,date) values(?,?,?,?,?)");
					pst.setInt(1, userId);
					pst.setInt(2, item.getMenuId());
					pst.setFloat(3,item.getPrice()*oQuantity);
					pst.setInt(4, oQuantity);
					pst.setDate(5, Date.valueOf(LocalDate.now()));
					int i=pst.executeUpdate();
					if(i>0) {
						pst=conn.prepareStatement("update item set stock=? where menuId=?");
						pst.setInt(1,nQuant);
						pst.setInt(2,item.getMenuId());

						i=pst.executeUpdate();
						System.out.println("Order added");
						OrderLogging.writeLog("Order the item id= "+id+" by user ");
						return true;
					}
					else {
						System.out.println("Somethings wents wrong");
					}
				}
				else {
					System.out.println("You can't order it... ");
					OrderLogging.writeLog("Order the item id= "+id+" no stock/less stock ");
				}
			}
			else {
				throw new InvalidMenuId();
			}

		}
		catch(InvalidMenuId ex) {
			System.out.println(ex.getMessage());
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(NumberFormatException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}
	
	
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
