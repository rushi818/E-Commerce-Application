package com.velocity.view.product.item;

import java.sql.SQLException;

public interface ViewProductItem {
	public  void productInput();
	
	public  void productCheck() throws SQLException, ClassNotFoundException;
}
