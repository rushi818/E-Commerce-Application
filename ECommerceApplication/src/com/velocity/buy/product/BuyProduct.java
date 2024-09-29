package com.velocity.buy.product;

import java.sql.SQLException;

public interface BuyProduct {
	public void getBuyProduct() throws SQLException;
	public void Shoppingcart(int productId, int quantity)throws SQLException;
}
