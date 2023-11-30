package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbManager {
	private static String userName = "root";
	private static String pass = "RocRoc777@#";
	private static String dbURL = "jdbc:mysql://localhost:3306/ecommerce";
	private static final Logger logger = Logger.getLogger(DbManager.class.getName());
	private static final String error = "Errore";

	protected DbManager() {
		super();
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(dbURL, userName, pass);
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
			return null;
		} catch (ClassNotFoundException e) {
			logger.log(Level.ALL, error, e);
			return null;
		}
	}
}
