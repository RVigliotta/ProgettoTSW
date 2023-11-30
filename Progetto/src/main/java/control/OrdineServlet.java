package control;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Ordine;
import model.OrdineComparator;
import model.SingoloOrdine;
import model.OrdiniList;

@WebServlet("/OrdineServlet")
public class OrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(OrdineServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;

		try (Connection connection = DbManager.getConnection();
			Statement s = connection.createStatement();){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
			OrdiniList ol = new OrdiniList();
			Ordine o = null;
			SingoloOrdine os = null;

			String query = "SELECT * FROM Singolo_ordine";
			ResultSet rs = s.executeQuery(query);
			ArrayList<SingoloOrdine> osList = new ArrayList<>();
			/* ricerca di tutti gli ordini singoli del database */
			while (rs.next()) {
				int idSingle = rs.getInt("id");
				int quantita = rs.getInt("quantita");
				double totParziale = rs.getDouble("totale_parziale");
				int ordini_id = rs.getInt("Ordiniid");
				String prodCodice = rs.getString("Prodotticodice");
				String prodNome = rs.getString("nome");
				String prodImg = rs.getString("immagine");

				os = new SingoloOrdine(idSingle, quantita, totParziale, ordini_id, prodCodice, prodNome, prodImg);
				osList.add(os);
			}

			/* ricerca del gli ordini nel DataBase */
			query = "SELECT * FROM ordini";
			rs = s.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				java.sql.Date dateSql = rs.getDate("data");
				java.util.Date dateJava = new java.util.Date(dateSql.getTime());
				double totale = rs.getDouble("totale");
				int userId = rs.getInt("Utenteid");
				int stato = rs.getInt("Statoid");
				int spedizione = rs.getInt("Metodo_di_spedizioneid");
				int idIndirizzo = rs.getInt("Indirizzoid");
				o = new Ordine(id, sdf.format(dateJava), totale, userId, stato, spedizione, idIndirizzo);
				/* associo ogni ordine ai suoi ordini singoli */
				for (SingoloOrdine temp : osList) {
					if (temp.getOrdini_id() == id)
						o.add(temp);
				}
				ol.add(o);
			}
			Collections.sort(ol.getOrdiniList(), new OrdineComparator());
			
			session.setAttribute("ordini", ol);
			dispatcher = request.getRequestDispatcher("ordine.jsp");
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