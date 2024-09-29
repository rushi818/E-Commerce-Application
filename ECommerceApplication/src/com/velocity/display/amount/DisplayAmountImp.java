package com.velocity.display.amount;

// 9 no
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayAmountImp implements DisplyAmount {

	@Override
	public void amount() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double totalAmount = 0.0;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");

			String query = "select price from cart";
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				double price = rs.getDouble("price");
				totalAmount += price;
			}

			System.out.println("Display the amount to End User: " + totalAmount);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			rs.close();
			ps.close();
			con.close();
		}
	}
}
