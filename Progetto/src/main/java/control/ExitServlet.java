package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Prodotto;
import model.Utente;

@WebServlet("/ExitServlet")
public class ExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ExitServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Utente user = (Utente) session.getAttribute("Utente");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		String insertIntoProdottiCarrello = "INSERT INTO ProdottiCarrello (Carrelloid, Prodotticodice, quantita) VALUES (?, ?, ?)";
		
		try (Connection connection = DbManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertIntoProdottiCarrello);) {

			Carrello carrello = (Carrello) session.getAttribute("Carrello");
			
			if (carrello.getCarrello().isEmpty()) {
				session.invalidate();
				response.sendRedirect("login.jsp");
				return;
			}

			for (Prodotto prod : carrello.getCarrello()) {
				ps.setInt(1, carrello.getId());
				ps.setString(2, prod.getCodice());
				ps.setInt(3, prod.getQuantita());
				ps.executeUpdate();
			}

			session.invalidate(); // Invalida la sessione
			response.sendRedirect("login.jsp"); 

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}