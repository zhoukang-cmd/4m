package com.fourm.client.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDBC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=master","sa","qwer1234");
			stmt = conn.prepareStatement("delete from t_test");
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
		
	}

}
