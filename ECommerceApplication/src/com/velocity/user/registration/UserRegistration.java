package com.velocity.user.registration;

import java.sql.SQLException;

public interface UserRegistration {

	public void getUserInputData() throws SQLException;
	
	public void setUserDetails(String fname, String lname, String uname, String password, String city, String mail, String mobno) throws SQLException;
}
