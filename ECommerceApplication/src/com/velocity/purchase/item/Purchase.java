package com.velocity.purchase.item;

import java.sql.SQLException;

public interface Purchase {
	
	public void getPurchase() throws SQLException;
	public void Shoppingcart(String username) throws SQLException;
}
