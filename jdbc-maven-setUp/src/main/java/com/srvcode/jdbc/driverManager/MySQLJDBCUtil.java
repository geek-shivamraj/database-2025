package com.srvcode.jdbc.driverManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLJDBCUtil {

	public static Connection getConnection() throws SQLException {
		Connection con = null;
		
		try(InputStream in = MySQLJDBCUtil.class.getResourceAsStream("/mysqldb.properties");) {
			
			Properties props = new Properties();
			props.load(in);
			
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("pass");
			
			con = DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

}
