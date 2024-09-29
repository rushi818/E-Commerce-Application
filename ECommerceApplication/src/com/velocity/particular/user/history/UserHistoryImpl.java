package com.velocity.particular.user.history;

// 12 no
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserHistoryImpl implements UserHistory {
	private int userId;

	@Override
	public void userInput() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Enter the user id>>  ");
			userId = sc.nextInt();
		} catch (Exception e) {
			throw new UserIDNotFoundException("Invalid input. Please enter valid data.");
		} finally {
			sc.close();
		}

	}

	@Override
	public void fetchUserHistors() throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");

			String query = "select * from product where productid = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Product id>> " + rs.getInt("productid"));
				System.out.println("Product Description>> " + rs.getString("productdescription"));
				System.out.println("Quantity>> " + rs.getString("productquantity"));

			}
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}
	}
}
