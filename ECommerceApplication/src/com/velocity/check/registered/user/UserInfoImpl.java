package com.velocity.check.registered.user;

// 11 no
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.velocity.user.registration.UserNotFoundException;

public class UserInfoImpl implements UserInfo {
	private String username;

	@Override
	public void userInput() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Username>> ");
			username = sc.next();
		} catch (Exception e) {
			throw new UserNotFoundException("Invalid input. Please enter valid data.");
		} finally {
			sc.close();
		}
	}

	@Override
	public void jdbc() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");

			String query = "select firstname, lastname,city,mailid, mobilenumber from user where username = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("First name>>" + rs.getString("firstname"));
				System.out.println("Last name>>" + rs.getString("lastname"));
				System.out.println("City>>" + rs.getString("city"));
				System.out.println("Email id>>  " + rs.getString("mailid"));
				System.out.println("Mobile>> " + rs.getString("mobilenumber"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
	}
}
