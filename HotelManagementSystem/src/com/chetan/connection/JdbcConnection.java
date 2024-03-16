package com.chetan.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	public static Connection conn;
	private static JdbcConnection jdbcConnection=null;
	
	private JdbcConnection(){
		try {
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement","root","root");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}
	
	public static JdbcConnection getConnection() {
		if(jdbcConnection==null) {
			jdbcConnection=new JdbcConnection();
		}
		return jdbcConnection;
	}
}
