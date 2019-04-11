package com.ecommerce.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQL {
	static Connection conn = null;
	static PreparedStatement statement = null;
	
	public static ArrayList<String> getEmails(int clientId) throws SQLException {
		 ArrayList<String> returnEmails = new ArrayList<String>();
		try {
			makeJDBCConnection();
			
			String queryStatement = "select C.email as emailClient, V.email as emailSeller from CLIENTS as C LEFT JOIN SELLERS as V ON C.SellerID = V.ID WHERE C.ID = "+ clientId;
 
			statement = conn.prepareStatement(queryStatement);
 
			// Execute the Query, and get a java ResultSet
			ResultSet rs = statement.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				returnEmails.add(rs.getString("emailClient"));
				returnEmails.add(rs.getString("emailSeller"));
			}
 
		} catch (
 
				SQLException e) {
			e.printStackTrace();
		}
		finally {
			statement.close();
			conn.close();
		}
		return returnEmails;
	}
	
	private static void makeJDBCConnection() {
		 
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "java", "12345");
			if (conn != null) {
				log("Connected!");
			} else {
				log("Failed to connect!");
			}
		} 
		catch (SQLException e) {
			log("MySQL Connection Failed!");
			e.printStackTrace();
		} 
	}
	
	// Simple log
		private static void log(String string) {
			System.out.println(string);
	 
		}
	
}
