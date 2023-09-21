package control;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;

@WebServlet("/DatiPersonaliServlet")
public class DatiPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DatiPersonaliServlet.class.getName());
	private static final String error = "Errore";
	private static final String status = "status";
	private static final String url = "datipersonali.jsp";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		Utente user = (Utente) session.getAttribute("Utente");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		int rowCount = 0;
		String query = "UPDATE Utente SET email = ?, password = ?, nome = ?, cognome = ? WHERE id = ?";
		
		try (Connection connection = DbManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);){
			if (nome.equals("")) {
				request.setAttribute(status, "Invalid_nome");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
			if (cognome.equals("")) {
				request.setAttribute(status, "Invalid_cognome");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
			if (email.equals("")) {
				request.setAttribute(status, "Invalid_email");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
			if (password1.equals("")) {
				request.setAttribute(status, "Invalid_password");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
			if (!(password1.equals(password2))) {
				request.setAttribute(status, "Invalid_password2");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
	
			ps.setString(1, email);
			ps.setString(2, password1);
			ps.setString(3, nome);
			ps.setString(4, cognome);
			ps.setInt(5, user.getId());
			rowCount = ps.executeUpdate();

			user.setEmail(email);
			user.setNome(nome);
			user.setCognome(cognome);

			session.setAttribute("Utente", user);

			if (rowCount > 0)
				request.setAttribute(status, "success");
			else
				request.setAttribute(status, "failed");

			dispatcher = request.getRequestDispatcher("datipersonali.jsp");
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