package model;

import java.sql.SQLException;
import java.util.*;

public interface OrderDao {
	
	public void OrderSave(int UserId,String Time, String cartJSON,String state) throws SQLException;
	
	public void doUpdate(Order o) throws SQLException;

	public void CartDelete(int UserId) throws SQLException;
	
	public void doDelete(int id)throws SQLException;
	
	public List<Order> DoRetrieveOrders(int UID) throws SQLException;
	
	public List<Order>  DoRetrieveAllOrder() throws SQLException;
	
}
