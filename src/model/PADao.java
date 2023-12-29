package model;

import java.sql.SQLException;

public interface PADao {
	public User RetrieveUserData(int UID) throws SQLException;
	
	public void ChangeAddress(int UID, String Address) throws SQLException;
	
	public void ChangeEmail(int UID, String Email) throws SQLException;
	
	
}
