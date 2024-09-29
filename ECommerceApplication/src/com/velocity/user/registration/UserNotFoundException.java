package com.velocity.user.registration;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String message)
	{
		super(message);
	}

}
