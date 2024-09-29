package com.velocity.purchase.item;

//6
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PurchaseImpl implements Purchase {

	@Override
	public void getPurchase() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter your username>> ");
		String username = scanner.next();
		
		Shoppingcart(username);
	}
	
	@Override
	public void Shoppingcart(String username) throws SQLException {
		Scanner scanner = new Scanner(System.in);

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");

			String userCheckQuery = "select * from user where username = ?";
			ps = con.prepareStatement(userCheckQuery);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {

				String selectQuery = "select sum(CAST(cart.productquantity as unsigned) * cast(product.price as decimal(10, 2))) AS totalBillAmount "
						+ "from cart join product on cart.productid = product.productid " + "where cart.username = ?";
				ps = con.prepareStatement(selectQuery);
				ps.setString(1, username);
				rs = ps.executeQuery();

				if (rs.next()) {
					double totalBillAmount = rs.getDouble("totalBillAmount");
					System.out.println("Username: " + username);
					System.out.println("Total Bill Amount: " + totalBillAmount);
				} else {
					throw new NoItemsFoundException("No items found in the cart for the given username...");
				}
			} else {
				throw new UserNotFoundException("User not found...");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
	}
}
