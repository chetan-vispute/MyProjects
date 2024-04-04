package com.chetan.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.chetan.connection.JdbcConnection;
import com.chetan.exception.InvalidUserException;
import com.chetan.logging.UserLogging;
import com.chetan.pojo.User;

public class RestaurentApp extends Thread{
	Connection conn;
	Scanner sc=new Scanner(System.in);
	public void run() {
		System.out.println("Resturent Thread running ...");
	}


	@SuppressWarnings("static-access")
	public RestaurentApp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=JdbcConnection.getConnection().conn;
		}
		catch(Exception ex) {
			System.out.println("hi");
			System.out.println(ex.getMessage());
		}
	}



	public void register() {
		User user=new User();
		System.out.println("Enter username :");
		String name=sc.nextLine();
		user.setUserName(name);
		System.out.println("Enter email :");
		String email=sc.nextLine();
		user.setEmailId(email);
		System.out.println("Enter the password :");
		String password=sc.nextLine();
		user.setPassword(password);
		System.out.println("Enter the type - admin/user :");
		String type=sc.nextLine();
		user.setType(type);
		try {
			PreparedStatement pst=conn.prepareStatement("insert into user(userName,emailId,password,type) values(?,?,?,?)");
			pst.setString(1, user.getUserName());
			pst.setString(2,user.getEmailId());
			pst.setString(3,user.getPassword());
			pst.setString(4,user.getType());
			int i=pst.executeUpdate();
			if(i==1) {
				System.out.println("User added");
				UserLogging.writeLog("User regiter susscessfuly "+((String)user.getUserName()).subSequence(0, 2)+"***");
			}
			else {
				System.out.println("user not added");
				UserLogging.writeLog("User not regitered  "+((String)user.getUserName()).subSequence(0, 2)+"***");
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			UserLogging.writeLog("User not regitered  "+((String)user.getUserName()).subSequence(0, 2)+"***");
		}


	}

	@SuppressWarnings("deprecation")
	public void login() {
		String userName,pass,type;
		boolean temp=false;
		System.out.println("Enter the username :");
		userName=sc.nextLine();
		System.out.println("Enter password :");
		pass=sc.nextLine();
		System.out.println("Enter type admin/user :");
		type=sc.nextLine();
		try {
			PreparedStatement pst=conn.prepareStatement("Select * from user");
			ResultSet rs=pst.executeQuery();

			while(rs.next()) {
				String name=rs.getString("userName");
				String password=rs.getString("password");
				String type1=rs.getString("type");
				if(name.equals(userName) && password.equals(pass) && type1.equalsIgnoreCase(type)) {
					if(type.equalsIgnoreCase("admin")) {
						System.out.println("Hey admin "+userName+" you login successfuly");
						UserLogging.writeLog("User login Successfully "+userName.subSequence(0,3)+"***");
						temp=true;
						AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
						adminDaoImpl.setName(userName);
						adminDaoImpl.start();
						int choice;
						do {
							System.out.println("_________________________________________________________");
							System.out.println("\n----------------------Admin Menu---------------------");
							System.out.println("1. Add item");
							System.out.println("2. Delete item");
							System.out.println("3. Update item");
							System.out.println("4. Display item");
							System.out.println("5. Display all bills genereted by today");
							System.out.println("6. Display Total sale of this Month");
							System.out.println("0. For- LogOut");
							System.out.println("\nEnter your choice = ");
							try {
								choice=Integer.parseInt(sc.nextLine());
							}
							catch(NumberFormatException ne) {
								choice=-1;
								System.out.println("Please enter valid input ..");
							}
							switch(choice){
							case 1:
								adminDaoImpl.addItem();
								break;
							case 2:
								adminDaoImpl.deleteItem();
								break;
							case 3:
								adminDaoImpl.updateItem();
								break;
							case 4:
								adminDaoImpl.displayItem();
								break;
							case 5:
								adminDaoImpl.displayTodaySale();

								break;
							case 6:
								adminDaoImpl.displayMonthSale();
								break;
							case 0:
								UserLogging.writeLog("User is logout "+userName.subSequence(0,3)+"***");
								adminDaoImpl.stop();
								break;
							default :
								System.out.println("Please enter valid choice =");
							}

						}while(choice!=0);
						break;
					}
					else if(type.equalsIgnoreCase("user")){
						System.out.println("Hey "+userName+" you login successfuly");
						UserLogging.writeLog("User login Successfully "+userName.subSequence(0,3)+"***");
						UserDaoImpl userDaoImpl=new UserDaoImpl(rs.getInt("userId"));
						userDaoImpl.setName(userName);
						userDaoImpl.start();

						temp=true;
						int choice;
						do {
							System.out.println("_________________________________________________________");
							System.out.println("\n----------------------User Menu---------------------");
							System.out.println("1. Display item");
							System.out.println("2. Order item");
							System.out.println("3. Show current bill");
							System.out.println("4. Show orders history");
							System.out.println("0. For- LogOut");
							System.out.println("\nEnter your choice = ");
							try {
								choice=Integer.parseInt(sc.nextLine());
							}
							catch(NumberFormatException ne) {
								choice=-1;
								System.out.println("Please enter valid input ..");
							}
							switch(choice){
							case 1:
								userDaoImpl.displayItem();
								break;
							case 2:
								userDaoImpl.orderItem();
								break;
							case 3:
								userDaoImpl.showBill();
								break;
							case 4:
								userDaoImpl.showHistoryOrders();
								break;

							case 0:
								UserLogging.writeLog("User is logout "+userName.subSequence(0,3)+"***");
								userDaoImpl.stop();

								break;
							default :
								System.out.println("Please enter valid choice =");
							}

						}while(choice!=0);
						break;
					}
				}
			}
			if(!temp) {
				throw new InvalidUserException();
			}
		}
		catch(InvalidUserException ex){
			UserLogging.writeLog(ex.getMessage());
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}



}


