package com.velocity.user.login;
// 2 no
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.velocity.jdbc.JDBCImpl;

public class UserLoginImpl implements UserLogin{

	@Override
	public void getUserLogin() throws SQLException {
		
		System.out.println("Enter the username>>");
		Scanner scanner = new  Scanner(System.in);
		String username = scanner.next();
		
		System.out.println("Enter the password>>");
		String password = scanner.next();

		setUserInfo(username, password);
	}
	
	@Override
	public void setUserInfo(String uname, String pass) throws SQLException
	{
		JDBCImpl jdbcImpl = new JDBCImpl();
		
		Connection connection = jdbcImpl.getConnection();
		
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(
					"select * from user where username = ? and password = ?");
			preparedStatement.setString(1, uname);
			preparedStatement.setString(2, pass);
	
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				System.out.println("Username and password is match...");
			}else {
				throw new UserLoginException("Either you enter the wrong username or password or you have not done the registration");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
			preparedStatement.close();
		}
	}
	
}
