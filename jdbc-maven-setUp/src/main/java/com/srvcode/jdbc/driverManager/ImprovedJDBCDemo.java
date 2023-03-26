package com.srvcode.jdbc.driverManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ImprovedJDBCDemo {

	public static void main(String[] args) {
		
		String databaseUrl = "jdbc:mysql://localhost:3307?user=root&password=admin";
		
		Properties connectionProps = new Properties();
		connectionProps.put("user", "root");
		connectionProps.put("password", "pass");
		
		Connection con = null;
		
		try {
			// 1st way
			con = DriverManager.getConnection(databaseUrl);
			
			// 2nd way
			con = DriverManager.getConnection(databaseUrl, connectionProps);
			
			// 3rd way (Loading properties from .properties file
			con = MySQLJDBCUtil.getConnection();
			
			if(!Objects.isNull(con)) {
				System.out.println("The connection has been successfully established");
			}
		} catch (SQLException e) {
			System.out.println("A connection error has occured");
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
