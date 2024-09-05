package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

@MultipartConfig
public class ProductDaoDataSource implements IProductDao {
	
	private static final String TABLE_NAME = "prodotti";
	private DataSource ds = null;

	public ProductDaoDataSource(DataSource ds) {
		this.ds = ds;
		
		System.out.println("DataSource Product Model creation....");
	}
	
	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductDaoDataSource.TABLE_NAME
				+ " (nome, descrizione, prezzo, Disponibilita, CategoriaID, immagine,codice) VALUES (?, ?, ?, ?, ?, ?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setFloat(3, product.getPrice());
			preparedStatement.setInt(4, product.getQuantity());
			preparedStatement.setInt(5, product.getCategoriaID());
			preparedStatement.setString(6, product.getImage());
			preparedStatement.setInt(7, product.getCode());
			preparedStatement.executeUpdate();

			//connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE codice = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCode(rs.getInt("codice"));
				bean.setName(rs.getString("nome"));
				bean.setDescription(rs.getString("descrizione"));
				bean.setPrice(rs.getFloat("prezzo"));
				bean.setQuantity(rs.getInt("Disponibilita"));
				bean.setCategoriaID(rs.getInt("CategoriaID"));
				bean.setImage(rs.getString("immagine"));
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	} 

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE codice = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();
			//connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	public synchronized void doUpdate(ProductBean product) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String updateSQL = "UPDATE " + TABLE_NAME + " SET nome = ?, descrizione = ?, prezzo = ?, Disponibilita = ?, CategoriaID = ?, immagine = ? WHERE codice = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(updateSQL);
	        preparedStatement.setString(1, product.getName());
	        preparedStatement.setString(2, product.getDescription());
	        preparedStatement.setFloat(3, product.getPrice());
	        preparedStatement.setInt(4, product.getQuantity());
	        preparedStatement.setInt(5, product.getCategoriaID());
	        preparedStatement.setString(6, product.getImage());
	        preparedStatement.setInt(7, product.getCode());
	       

	        preparedStatement.executeUpdate();
	        
	        
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            if (connection != null)
	                connection.close();
	        }
	    }
	    
	}


	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCode(rs.getInt("codice"));
				bean.setName(rs.getString("nome"));
				bean.setDescription(rs.getString("descrizione"));
				bean.setPrice(rs.getFloat("prezzo"));
				bean.setQuantity(rs.getInt("Disponibilita"));
				bean.setCategoriaID(rs.getInt("CategoriaID"));
				bean.setImage(rs.getString("immagine"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
	public synchronized Collection<ProductBean> doRetrieveProducts() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME;


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCode(rs.getInt("codice"));
				bean.setName(rs.getString("nome"));
				bean.setDescription(rs.getString("Descrizione"));
				bean.setPrice(rs.getFloat("prezzo"));
				bean.setQuantity(rs.getInt("Disponibilita"));
				bean.setCategoriaID(rs.getInt("CategoriaID"));
				bean.setImage(rs.getString("immagine"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}

}