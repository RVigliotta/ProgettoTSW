package control;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
import model.Indirizzo;
import model.Prodotto;
import model.Utente;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = "indirizzo";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		String query = "SELECT * FROM Utente WHERE email = ? and password = ?";

		try (Connection connection = DbManager.getConnection();
			Statement s = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(query);){			
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				Utente user = new Utente(id, email, nome, cognome);

				query = "SELECT * FROM Indirizzo WHERE Utenteid = " + id;
				rs = s.executeQuery(query);

				/* quando si fa il log in, vengono caricate anche le info sull'indirizzo */
				if (rs.next()) {
					String indirizzo = rs.getString(address);
					String cap = rs.getString("cap");
					String citta = rs.getString("citta");
					String provincia = rs.getString("provincia");
					String nazione = rs.getString("nazione");
					Indirizzo i = new Indirizzo(indirizzo, cap, citta, provincia, nazione);
					session.setAttribute(address, i);
				} else {
					/* se l'indirizzo è inesistente, ne viene creato uno di default (serve per i placeholder */
					session.setAttribute(address, new Indirizzo("Inserisci i tuoi dati", "", "", "", ""));
				}
				
				query = "SELECT * FROM Carrello WHERE Utenteid=" + id;
				rs =  s.executeQuery(query);
				int carrelloid = 0;
				Carrello carrello = new Carrello(0);
				
				//se l'utente ha un carrello assegnato lo si prende e lo si assegna 
				if(rs.next()) {
					carrelloid = rs.getInt("id");
					 carrello = new Carrello(carrelloid);
				}
				else {
					query = "INSERT INTO Carrello(Utenteid) VALUES (" + id + ")";
					s.executeUpdate(query);
					query = "SELECT * FROM Carrello WHERE Utenteid=" + id;
					rs = s.executeQuery(query);
					if(rs.next()) {
						carrelloid = rs.getInt("id");
						 carrello = new Carrello(carrelloid);
					}
				}
				
				query = "SELECT * FROM Carrello WHERE id=" + carrelloid;
				rs = s.executeQuery(query);
				
				ArrayList<Prodotto> list = (ArrayList<Prodotto>) carrello.getCarrello();
				ArrayList<String> codiceList = new ArrayList<>();
				while(rs.next()) {
					String codice = rs.getString("Prodotticodice");
					codiceList.add(codice);	
				}
				
				query = "SELECT Prodotti.* FROM ProdottiCarrello JOIN Prodotti ON ProdottiCarrello.Prodotticodice = Prodotti.codice WHERE Carrelloid=" + carrelloid;
				rs = s.executeQuery(query);
				while(rs.next()) {
					if(codiceList.contains(rs.getString("codice"))){
						String nomeprod = rs.getString("nome");
						String descrizione = rs.getString("descrizione");
						String img = rs.getString("immagine");
						String marca = rs.getString("Marcanome");
						String categoria = rs.getString("Categorianome");
						int quantita = rs.getInt("quantita");
						double prezzo = rs.getDouble("prezzo");
						Prodotto prodotto = new Prodotto(rs.getString("codice"), nomeprod, descrizione, img, marca, categoria, quantita, prezzo);
						list.add(prodotto);
					}
				}
				for(String codice : codiceList) {
					query = "DELETE  FROM Carrello WHERE Prodotticodice=" + codice;
					ps.executeUpdate(query);
				}
				carrello.setCarrello(list);
				session.setAttribute("Carrello", carrello);

				session.setAttribute("Utente", user);
				dispatcher = request.getRequestDispatcher("index.jsp");
				
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
			rs.close();
		} catch (Exception e) {
			logger.log(Level.ALL, error, e);
		}
	}
}