package com.velocity.add.product.item;

import java.sql.SQLException;

public interface AddProduct1 {

	public void setAddProduct() throws SQLException;
	public void addProduct(int id, String name, String description, String quantity, int price) throws SQLException;
}
