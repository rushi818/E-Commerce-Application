package com.velocity.buy.product;

public class QuantityExceedException extends RuntimeException{
	
	public QuantityExceedException (String message){
		super(message);
	}
}
