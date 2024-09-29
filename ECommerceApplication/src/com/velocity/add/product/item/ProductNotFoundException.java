package com.velocity.add.product.item;

public class ProductNotFoundException extends RuntimeException{
	
	public ProductNotFoundException(String message)
	{
		super(message);
	}

}
