package com.velocity.user.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserRegistrationJDBC {

	public Connection getConnection()
	{
		Connection connection = null;
		
		try
		{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void getUserRegistration()
	{
		UserRegistrationImpl userRegistrationImpl = new UserRegistrationImpl();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try
		{
			UserRegistrationJDBC userRegistractionJDBC = new UserRegistrationJDBC();
			
			con = userRegistractionJDBC.getConnection();
			
			ps = con.prepareStatement("insert into user(firstname, lastname, user, password, city, mailid, mobilenumber) values (?,?,?,?,?,?,?)");
			
			ps.setString(1, "Rushikesh");
			ps.setString(2, "Bhoi");
			ps.setString(3, "rushi818");
			ps.setString(4, "pass@123");
			ps.setString(5, "pune");
			ps.setString(6, "rushikeshbhoi@gmail.com");
			ps.setString(7, "7972544174");
			
			int res = ps.executeUpdate();
			
			System.out.println("Record is inserted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
