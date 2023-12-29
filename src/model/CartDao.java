package model;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface CartDao {
	
	public void cartSave(int userId, int productId, int quantity) throws SQLException;

	public void cartDelete(int UID, int PID, int quantity) throws SQLException;
	
	public void cartDelAll(int UID, int PID) throws SQLException;
	
	public Collection<ProductBean> doRetrieveProducts(int UID) throws SQLException;

}
