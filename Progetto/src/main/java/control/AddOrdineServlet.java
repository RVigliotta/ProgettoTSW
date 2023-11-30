package control;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Prodotto;
import model.Utente;

@WebServlet("/AddOrdineServlet")
public class AddOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddOrdineServlet.class.getName());
	private static final String error = "Errore";
	
	@SuppressWarnings("resource")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Statement s = null;
		PreparedStatement ps = null;
		
		RequestDispatcher dispatcher = null;
		Utente user = (Utente) session.getAttribute("Utente");
		Carrello carrello = (Carrello) session.getAttribute("Carrello");
		
		int address_id = -1;

		try (Connection connection = DbManager.getConnection();){		
			String query = "SELECT id FROM Indirizzo WHERE Utenteid = " + user.getId();
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			if (rs.next())
				address_id = rs.getInt("id");
			else {
				request.setAttribute("status", "noAddress");
				dispatcher = request.getRequestDispatcher("indirizzo.jsp");
				dispatcher.forward(request, response);
				return;
			}

			Calendar c = Calendar.getInstance();
			java.util.Date javaDate = c.getTime();
			java.sql.Date sqlDate = new Date(javaDate.getTime());

			double totale = Double.parseDouble(request.getParameter("totale"));
			SecureRandom random = new SecureRandom();
			int ordine_id = 10000 + random.nextInt(90000);

			query = "INSERT INTO Ordini (id, data, totale, Utenteid, Statoid, Metodo_di_spedizioneid, Indirizzoid) values(?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, ordine_id);
			ps.setDate(2, sqlDate);
			ps.setDouble(3, totale);
			ps.setInt(4, user.getId());
			ps.setInt(5, 1); // completato
			ps.setInt(6, 1); // posteItaliane
			ps.setInt(7, address_id);
			ps.executeUpdate();

			String[] values = request.getParameter("quantita").split(",");
			int i = 0;

			for (Prodotto p : carrello.getCarrello()) {
				p.setQuantita(Integer.parseInt(values[i]));
				i++;
			}
			
			for (Prodotto p : carrello.getCarrello()) {
				query = "INSERT INTO Singolo_ordine (quantita, totale_parziale, Ordiniid, Prodotticodice, Prodottinome, Prodottiimmagine) values("
						+ p.getQuantita() + ", " + (p.getPrezzo() * p.getQuantita()) + ", " + ordine_id + ", '"
						+ p.getCodice() + "', '" + p.getNome() + "', '" + p.getImg() + "')";
				
				s.executeUpdate(query);
				query= "SELECT quantita FROM Prodotti WHERE codice = ?";
				ps = connection.prepareStatement(query);
				ps.setString(1,p.getCodice());
				rs = ps.executeQuery();
				if(rs.next()) {
					query= "UPDATE Prodotti SET quantita = ? WHERE codice = ?";
					ps = connection.prepareStatement(query);
					ps.setInt(1, (rs.getInt("quantita")-p.getQuantita()));
					ps.setString(2, p.getCodice());
					ps.executeUpdate();
					ps.close();
				}
			}
			carrello.empty();
			session.setAttribute("Carrello", carrello);

			dispatcher = request.getRequestDispatcher("profilo.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (ServletException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		} catch (NumberFormatException e) {
			logger.log(Level.ALL, error, e);
		} finally {
			try {
				s.close();
				ps.close();
			} catch (SQLException e) {
				logger.log(Level.ALL, error, e);
			} catch (NullPointerException e) {
				logger.log(Level.ALL, error, e);
			}
		}
	}
}