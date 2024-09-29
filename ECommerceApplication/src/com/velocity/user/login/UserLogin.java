package com.velocity.user.login;

import java.sql.SQLException;

public interface UserLogin {

	public void getUserLogin() throws SQLException;
	public void setUserInfo(String uname, String pass) throws SQLException;
}
