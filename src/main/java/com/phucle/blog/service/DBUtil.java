package com.phucle.blog.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static String jdbcUrl="jdbc:mysql://localhost:3306/demo-blog?useEncoding=true&characterEncoding=UTF-8";
	public static String jdbcUsername="root";
	public static String jdbcPassword="";
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return conn;
	}
}
