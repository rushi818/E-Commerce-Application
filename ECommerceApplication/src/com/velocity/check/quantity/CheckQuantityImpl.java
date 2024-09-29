package com.velocity.check.quantity;

// 10 no
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckQuantityImpl implements CheckQuantity {

	private int productId;

	@Override
	public void userInput() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Enter Product Id>> ");
			productId = sc.nextInt();
		} catch (Exception e) {
			throw new ProductNotFoundException("Invalid input. Please enter valid data.");
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

			String query = "select productquantity from product where productid = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Quantity is>>" + rs.getInt(1));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}
	}
}
