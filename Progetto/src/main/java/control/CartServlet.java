package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Carrello;
import model.Prodotto;
import model.Utente;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CartServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("Carrello");
		String codice = request.getParameter("codice");
		Utente user = (Utente) session.getAttribute("Utente");
		Gson json = new Gson();
		String query = "SELECT * FROM Prodotti WHERE codice = ?";

		try (Connection connection = DbManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);) {
			PrintWriter out = response.getWriter();

			if (codice == null) {
				HashMap<String, String> hashMap = new HashMap<>();
				hashMap.put("url", "carrello.jsp");
				out.write(json.toJson(hashMap));
				return;
			}

			if (user == null)
				response.sendRedirect("login.jsp");
			else {
				ps.setString(1, codice);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String nome = rs.getString("nome");
					String descrizione = rs.getString("descrizione");
					String img = rs.getString("immagine");
					String marca = rs.getString("Marcanome");
					String categoria = rs.getString("Categorianome");
					int quantita = rs.getInt("quantita");
					double prezzo = rs.getDouble("prezzo");
					Prodotto prodotto = new Prodotto(codice, nome, descrizione, img, marca, categoria, quantita, prezzo);

					/* controlla che ci sia solo una ripetizione per ogni prodotto */
					int flag = 0;
					for (Prodotto p : carrello.getCarrello()) {
						if (prodotto.getCodice().equals(p.getCodice()))
							flag = 1;
					}

					if (flag == 0) {
						carrello.add(prodotto);
						session.setAttribute("Carrello", carrello);
					}
				}

				rs.close();
				out.write(json.toJson(carrello.getCarrello()));
			}

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}