package com.velocity.user.view.product.item.sorted.order;
// 3 no
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.velocity.jdbc.JDBCImpl;

public class ViewProductImpl implements ViewProduct{

	@Override
	public void getProductInfo() throws SQLException {
		JDBCImpl jdbcImpl = new JDBCImpl();
		
		Connection connection = jdbcImpl.getConnection();
		
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement( "select * from product order by price asc");
			
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("List of Product Available in Stock");
			System.out.println();
			
			
			while(resultSet.next())
			{
				System.out.println("Product Id>> "+resultSet.getInt("productid"));
				System.out.println("Product Name>> "+resultSet.getString("productname"));
				System.out.println("Product Description>> "+resultSet.getString("productdescription"));
				System.out.println("Available Quantity>> "+resultSet.getString("productquantity"));
				System.out.println("Price>> "+resultSet.getString("price"));
				System.out.println();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}	
	}
}
