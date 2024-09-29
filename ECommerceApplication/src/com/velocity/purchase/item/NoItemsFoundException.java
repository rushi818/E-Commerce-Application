package com.velocity.purchase.item;

public class NoItemsFoundException extends RuntimeException{
	
	public NoItemsFoundException(String message)
	{
		super(message);
	}

}
