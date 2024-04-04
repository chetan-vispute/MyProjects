package com.chetan.main;

import java.util.Scanner;

import com.chetan.daoimpl.RestaurentApp;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		RestaurentApp rApp=new RestaurentApp();
		rApp.start();
		int choice;
		try {
			System.out.println("\n-----------------Welcome ------------------");
			do {
				System.out.println("_____________________________________________________");
				System.out.println("\n-----------------Main Menu-----------------\n");
				System.out.println("1.Register");
				System.out.println("2.Login");
				System.out.println("0.Exit");
				System.out.println("\nEnter your choice = ");
				try {
					choice=Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException in) {
					choice=-1;
					System.out.println("Exception :"+in.getMessage()+"Please enter valid choice ");
				}
				switch(choice) {
				case 1:
					UserRegister userRegister=()->{rApp.register();};
					userRegister.register();
					break;
				case 2:
					UserLogin login=()->{rApp.login();};
					login.login();
					break;
				case 0:
					System.out.println("Thank you...");
					break;
				default:
					System.out.println("Please enter the valid choice :");

				}
			}while(choice!=0);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}

@FunctionalInterface
interface UserRegister{
	public void register();
}

@FunctionalInterface
interface UserLogin{
	public void login();
}
