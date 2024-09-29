package com.velocity.user.registration;
// 1 No
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.velocity.jdbc.JDBCImpl;

public class UserRegistrationImpl implements UserRegistration{

	@Override
	public void getUserInputData() throws SQLException {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the first name>>");
		String firstname = scanner.next();
		
		System.out.println("Enter the last name>>");
		String lastname = scanner.next();
		
		System.out.println("Enter the username>>");
		String username = scanner.next();
		
		System.out.println("Enter the password>>");
		String password = scanner.next();
		
		System.out.println("Enter the city>>");
		String city = scanner.next();
		
		System.out.println("Enter the mail id>>");
		String mail = scanner.next();
		
		System.out.println("Enter the mobile number>>");
		String mobilenumber = scanner.next();
		
		setUserDetails(firstname, lastname, username, password, city, mail, mobilenumber);
	}
	
	@Override
	public void setUserDetails(String fname, String lname, String uname, String password, String city, String mail, String mobno) throws SQLException 
	{
		JDBCImpl jdbcImpl = new JDBCImpl();
		Connection connection = jdbcImpl.getConnection();
		
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(
					"insert into user(firstname,lastname,username,password,city,mailid,mobilenumber)values(?,?,?,?,?,?,?)");
			preparedStatement.setString(1, fname);
			preparedStatement.setString(2, lname);
			preparedStatement.setString(3, uname);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, city);
			preparedStatement.setString(6, mail);
			preparedStatement.setString(7, mobno);

			int res = preparedStatement.executeUpdate();

			System.out.println("Record is inserted >>>" + res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
			preparedStatement.close();
		}
	}
}
