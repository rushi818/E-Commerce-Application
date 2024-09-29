package com.velocity.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCImpl {

	public Connection getConnection()
	{
		Connection connection = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
}
