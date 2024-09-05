package control;

import model.DriverManagerConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection=null;  
	ResultSet usercheck=null;
    PreparedStatement statement = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() throws ServletException {
        super.init();
        try {
        	DriverManagerConnectionPool connectionPool = DriverManagerConnectionPool.getInstance();
        	connection = connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("nome");
		String surname = request.getParameter("cognome");
		String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String adress = request.getParameter("adress");
        String pag=request.getParameter("pag");
        try {
			if (isUsernameExists(username)) {
			    // Il nome utente esiste già nel database
				request.getRequestDispatcher("RegisterPage.jsp?error=username_exists").forward(request, response);
			    return; // Termina l'esecuzione della servlet
			}
		   if (isEmailExists(email)) {
			    // L'email esiste già nel database
			   request.getRequestDispatcher("RegisterPage.jsp?error=email_exists").forward(request, response);
				    return; // Termina l'esecuzione della servlet
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {	
            String insertQuery = "INSERT INTO Utente (Nome, Cognome, Username, Email, Password, indirizzo,metodopag) VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, username);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setString(6,adress);
            statement.setString(7,pag);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // Gestione degli errori
            e.printStackTrace();
        }
        response.sendRedirect("RegSucc.jsp");
	}
		
        private boolean isUsernameExists(String username) throws SQLException {
            String query = "SELECT * FROM Utente WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Restituisce true se il nome utente esiste già nel database, altrimenti false 
	}
        private boolean isEmailExists(String email) throws SQLException {
            String query = "SELECT * FROM Utente WHERE Email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Restituisce true se l'email esiste già nel database, altrimenti false 
	}
}
