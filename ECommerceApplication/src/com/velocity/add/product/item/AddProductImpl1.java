package com.velocity.add.product.item;
// 7 no
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.velocity.jdbc.JDBCImpl;

public class AddProductImpl1 implements AddProduct1 {

    @Override
    public void setAddProduct() throws SQLException {

        	Scanner scanner = new Scanner(System.in);
            System.out.println("Product Id>> ");
            int productID = scanner.nextInt();

            System.out.println("Product Name>> ");
            scanner.nextLine();
            String productName = scanner.nextLine();

            System.out.println("Product Description>> ");
            String productDescription = scanner.nextLine();

            System.out.println("Quantity>> ");
            String productQuantity = scanner.next();

            System.out.println("Price>> ");
            int productPrice = scanner.nextInt();

            addProduct(productID, productName, productDescription, productQuantity, productPrice);

    }

    @Override
    public void addProduct(int id, String name, String description, String quantity, int price) throws SQLException {
        JDBCImpl jdbcImpl = new JDBCImpl();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = jdbcImpl.getConnection();
            preparedStatement = connection.prepareStatement(
                    "insert into product (productid, productname, productdescription, productquantity, price) values (?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, quantity);
            preparedStatement.setInt(5, price);

            int res = preparedStatement.executeUpdate();
            
            if (res == 0) {
                throw new ProductNotFoundException("Product with ID " + id + " not found.");
            }
            
            System.out.println("Record is inserted successfully...");

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
                preparedStatement.close();
                connection.close();
        }
    }
}
