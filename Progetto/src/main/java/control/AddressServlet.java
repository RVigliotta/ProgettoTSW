package control;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Indirizzo;
import model.Utente;

@WebServlet("/AddressServlet")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddressServlet.class.getName());
	private static final String error = "Errore";
	private static final String url = "indirizzo.jsp";
	private static final String status = "status";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		Utente user = (Utente) session.getAttribute("Utente");
		String indirizzo = request.getParameter("indirizzo");
		String cap = request.getParameter("cap");
		String citta = request.getParameter("citta");
		String provincia = request.getParameter("provincia");
		String nazione = request.getParameter("nazione");

		try (Connection connection = DbManager.getConnection(); 
			Statement s = connection.createStatement();) {
			if (indirizzo.equals("")) {
				request.setAttribute(status, "Invalid_indirizzo");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
			if (citta.equals("")) {
				request.setAttribute(status, "Invalid_citta");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
			if (provincia.equals("")) {
				request.setAttribute(status, "Invalid_provincia");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
			if (cap.equals("") || (cap.length() != 5)) {
				request.setAttribute(status, "Invalid_cap");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}
			if (nazione.equals("") || nazione.equals("-effettua una scelta-")) {
				request.setAttribute(status, "Invalid_nazione");
				dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			}

			String query = "SELECT * FROM Indirizzo WHERE Utenteid=" + user.getId();
			ResultSet rs = s.executeQuery(query);
			int rowCount = 0;

			if (!rs.next()) {
				query = "INSERT INTO Indirizzo (indirizzo, cap, citta, provincia, nazione, Utenteid) values(?, ?, ?, ?, ?, ?)";
				try (PreparedStatement ps = connection.prepareStatement(query);) {
					ps.setString(1, indirizzo);
					ps.setString(2, cap);
					ps.setString(3, citta);
					ps.setString(4, provincia);
					ps.setString(5, nazione);
					ps.setInt(6, user.getId());
					rowCount = ps.executeUpdate();
				} catch (SQLException e) {
					logger.log(Level.ALL, error, e);
				}
			} else {
				query = "UPDATE Indirizzo SET indirizzo = ?, cap = ?, citta = ?, provincia = ?, nazione = ? WHERE Utenteid = ?";
				try (PreparedStatement ps = connection.prepareStatement(query);) {
					ps.setString(1, indirizzo);
					ps.setString(2, cap);
					ps.setString(3, citta);
					ps.setString(4, provincia);
					ps.setString(5, nazione);
					ps.setInt(6, user.getId());
					rowCount = ps.executeUpdate();
				} catch (SQLException e) {
					logger.log(Level.ALL, error, e);
				}
			}

			if (rowCount > 0) {
				request.setAttribute(status, "success");
				/*
				 * sia se si crea, sia se si modifica, le informazioni in sessione vanno
				 * aggiornate
				 */
				Indirizzo i = new Indirizzo(indirizzo, cap, citta, provincia, nazione);
				session.setAttribute("Indirizzo", i);

			} else {
				request.setAttribute(status, "failed");
			}

			dispatcher = request.getRequestDispatcher("indirizzo.jsp");
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