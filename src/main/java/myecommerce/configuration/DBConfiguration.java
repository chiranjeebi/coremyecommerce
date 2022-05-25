package com.myecommerce.configuration;


import java.sql.DriverManager;

import com.myecommerce.constant.ApplicationConstant;
import java.sql.Connection;

public class DBConfiguration {
	
	public static Connection getDBconnection() {

		Connection conn = null;
		
		try  {
			
			Class.forName("com.mysql.jdbc.Driver");//Creates a driver object for interacting with mysql database
            // different databases have different types of drivers. Ex: Oracle will have a different driver
			conn =DriverManager.getConnection(ApplicationConstant.DB_URL,ApplicationConstant.DB_USERNAME,ApplicationConstant.DB_PASSWORD);//Creates a connection to mysql database server by taking dbURL, username, password parameters

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
