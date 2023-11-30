package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SignupServlet.class.getName());
	private static final String error = "Errore";
	private static final String status = "status";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		String q = "SELECT email FROM Utente WHERE email=?";	
		String query = "INSERT INTO Utente(nome,cognome,email,password) values(?,?,?,?)";
		
		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps1 = connection.prepareStatement(q);
			PreparedStatement ps2 = connection.prepareStatement(query)){		
			ps1.setString(1,  email);
			ResultSet rs = ps1.executeQuery(q);
			
			if(rs.next()) {
				request.setAttribute(status, "duplicato");
				dispatcher = request.getRequestDispatcher("signup.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			ps2.setString(1, nome);
			ps2.setString(2, cognome);
			ps2.setString(3, email);
			ps2.setString(4, password);

			int rowCount = 0;

			if (!((nome.equals("")) || (cognome.equals("")) || (email.equals("")) || (password.equals("")))) {
				rowCount = ps2.executeUpdate();
			}
			dispatcher = request.getRequestDispatcher("login.jsp");

			if (rowCount > 0) {
				request.setAttribute(status, "success");

			} else {
				request.setAttribute(status, "failed");
			}

			dispatcher.forward(request, response);

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (ServletException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}