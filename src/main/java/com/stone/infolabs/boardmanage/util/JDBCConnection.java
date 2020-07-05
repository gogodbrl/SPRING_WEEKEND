package com.stone.infolabs.boardmanage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCConnection {
	/*******************************************************
	 * Static
	 *******************************************************/
	private static Connection conn = null;
	private static Statement cursor = null;

	/*******************************************************
	 * GetJDBCConnection
	 *******************************************************/
	public static void GetJDBCConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		if(conn == null) {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test", "1234");
		}
	}
	/*******************************************************
	 * ExecuteSql
	 *******************************************************/
	public static boolean ExecuteSql(String sql) {
		try {
			if(conn == null) { GetJDBCConnection(); }
			if(cursor == null) {
				cursor = conn.createStatement();
			}
			cursor.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Close();
		}
		return false;
	}
	
	/*******************************************************
	 * SelectSql
	 *******************************************************/
	public static boolean SelectSql(String sql) {
		try {
			if(conn == null) { GetJDBCConnection(); }
			if(cursor == null) {
				cursor = conn.createStatement();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Close();
		}
		return false;
	}
	
	/*******************************************************
	 * Close
	 *******************************************************/
	public static boolean Close() {
		try {
			if(cursor != null) { cursor.close(); }
			if(conn != null) { conn.close(); }			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
