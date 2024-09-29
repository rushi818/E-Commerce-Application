package com.velocity.check.quantity;

public class ProductNotFoundException extends RuntimeException{
	
	public ProductNotFoundException(String message)
	{
		super(message);
	}

}
