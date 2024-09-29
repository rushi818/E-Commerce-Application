package com.velocity.buy.product;

//4
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BuyProductImpl implements BuyProduct {
	
	@Override
	public void getBuyProduct() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the product id to buy product>>");
		int productId = scanner.nextInt();
		System.out.print("Enter the quantity>>");
		int quantity = scanner.nextInt();
		
		Shoppingcart(productId, quantity);
	}
	
	@Override
	public void Shoppingcart(int productId, int quantity) throws SQLException{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");

			String fetchProductQuery = "select productname, price, productquantity from product where productid = ?";
			ps = con.prepareStatement(fetchProductQuery);
			ps.setInt(1, productId);
			rs = ps.executeQuery();

			if (rs.next()) {
				String productName = rs.getString("productname");
				String price = rs.getString("price");
				int availableQuantity = rs.getInt("productquantity");

				if (quantity > availableQuantity) {
					throw new QuantityExceedException("Quantity exceeds available stock.");
				}

				String insertCartQuery = "insert into cart (productid, productname, productquantity, price) values (?, ?, ?, ?)";
				try (PreparedStatement insertPs = con.prepareStatement(insertCartQuery)) {
					insertPs.setInt(1, productId);
					insertPs.setString(2, productName);
					insertPs.setInt(3, quantity);
					insertPs.setString(4, price);
					insertPs.executeUpdate();
				}

			} else {
				System.out.println("Product not found.");
			}

			System.out.print("Do you want to view cart (Yes/No)>>");
			Scanner scanner = new Scanner(System.in);
			String viewCart = scanner.next();

			if (viewCart.equalsIgnoreCase("Yes")) {
				String selectCartSQL = "select * from cart";
				try (PreparedStatement pst = con.prepareStatement(selectCartSQL); ResultSet rst = pst.executeQuery()) {
					while (rst.next()) {
						System.out.println("Cart ID: " + rst.getInt("cartid") + ", Product ID: " + rst.getInt("productid")
										+ ", Product Name: " + rst.getString("productname") + ", Quantity: "
										+ rst.getInt("productquantity") + ", Price: " + rst.getString("price"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			rs.close();
			ps.close();
			con.close();
		}
	}
}
