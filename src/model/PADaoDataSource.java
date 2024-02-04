package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class PADaoDataSource implements PADao{
	private static final String TABLE_NAME = "Utente";
	private DataSource ds = null;

	public PADaoDataSource(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public User RetrieveUserData(int UID) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    User user = new User();
	    
	    String selectSQL = "SELECT * FROM " + PADaoDataSource.TABLE_NAME + " WHERE id = ?";
	    
	    try {
	        connection = ds.getConnection();
	        connection.setAutoCommit(false); // Disabilita l'autocommit
	        
	        
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setInt(1, UID);
	        
	        ResultSet rs=preparedStatement.executeQuery();
	        while (rs.next()) {
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setIndirizzo(rs.getString("Indirizzo"));
				
			}
	    }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return user;
	}

	@Override
	public void ChangeAddress(int UID, String Address) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    
	    String selectSQL = "SELECT Indirizzo FROM " + PADaoDataSource.TABLE_NAME + " WHERE ID = ?";
	    
	    String insertSQL = "INSERT INTO " + PADaoDataSource.TABLE_NAME + " (Indirizzo) VALUES (?)";

	    // Query per l'aggiornamento dell'indirizzo
	    String updateSQL = "UPDATE " + PADaoDataSource.TABLE_NAME  + " SET Indirizzo = ? WHERE ID = ?";
	    
	    try {
	        connection = ds.getConnection();
	        connection.setAutoCommit(false); // Disabilita l'autocommit

	        // Verifica se l'indirizzo è già inserito
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setInt(1, UID);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	        	
	            // L'indirizzo è gia presente, quindi viene cambiato
	        	preparedStatement.close(); // Chiudi la query precedente

	            preparedStatement = connection.prepareStatement(updateSQL);
	            preparedStatement.setString(1, Address);
	            preparedStatement.setInt(2, UID);
	            preparedStatement.executeUpdate();
	        } else {
	        	
	            // L'indirizzo non è impostato, quindi inseriscilo
	            preparedStatement.close(); // Chiudi la query precedente

	            preparedStatement = connection.prepareStatement(insertSQL);
	            preparedStatement.setString(1, Address);
	            preparedStatement.executeUpdate();
	            
	        }
	        
	        connection.commit(); // Esegui il commit delle operazioni nel database
	    } catch (SQLException e) {
	        if (connection != null) {
	            try {
	                connection.rollback(); // Esegui il rollback in caso di errore
	                
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public boolean UserCheck(int id, String tab) throws SQLException  {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    String chkOrd = "SELECT * FROM "+ tab +" where Utente_id ="+ id+";";
	    
		try {
			  connection = ds.getConnection();
		        connection.setAutoCommit(false);
	    	
	    	PreparedStatement s = connection.prepareStatement(chkOrd);
	    	ResultSet r=s.executeQuery();
	    	if(r!=null)
	    		return true;
	    	else
	    		return false;
	    		
			
		
			
        	
        	
			}catch(SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
		
	}
	
	
	@Override
	public void ChangeEmail(int UID, String Email) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
