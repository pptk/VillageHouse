package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {
	private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;database=Village";
	private static final String USER = "sa";
	private static final String PASSWORD = "123";
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	// 获取数据库连接
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.out.println("连接失败");
			e.printStackTrace();
			return null;
		}
	}

	// 关闭方法 带三个参数
	public static void close(Connection conn, Statement stm, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stm != null)
				stm.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("关闭出错");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		System.out.println(ConnectionFactory.getConnection());
	}

}
