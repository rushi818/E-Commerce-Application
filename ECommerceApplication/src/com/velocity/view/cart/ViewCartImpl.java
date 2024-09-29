package com.velocity.view.cart;
// 5 no
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewCartImpl implements ViewCart{

	@Override
	public void getViewCart() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product id to buy product>>");
        int productId = scanner.nextInt();
        System.out.print("Enter the quantity>>");
        int quantity = scanner.nextInt();
		
        setCart(productId, quantity);
	}
	@Override
	public void setCart(int productId,  int quantity) {
		
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");

            String query = "insert into cart (productid, productquantity) values (?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, quantity);
            ps.executeUpdate();
            
            System.out.println("Product item has been added to cart.");
            Scanner scanner = new Scanner(System.in);
            String viewCart = scanner.next();

            if (viewCart.equalsIgnoreCase("Yes")) {
                String selectCartSQL = "select * from cart";
                try (PreparedStatement pst = con.prepareStatement(selectCartSQL);
                     ResultSet rst = pst.executeQuery()) {
                    while (rst.next()) {
                        System.out.println("Product ID: " + rst.getInt("productid") + ", Quantity: " + rst.getInt("productquantity"));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                 ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


