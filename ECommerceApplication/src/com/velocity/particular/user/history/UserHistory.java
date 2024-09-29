package com.velocity.particular.user.history;

import java.sql.SQLException;

public interface UserHistory {
	
	public void userInput();
	public void fetchUserHistors() throws SQLException,ClassNotFoundException ;


}
