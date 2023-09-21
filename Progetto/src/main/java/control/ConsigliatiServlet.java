package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Prodotto;

@WebServlet("/ConsigliatiServlet")
public class ConsigliatiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ConsigliatiServlet.class.getName());
	private static final String error = "Errore";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoria = request.getParameter("categoria");
		Gson json = new Gson();

		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Prodotti WHERE Categorianome = ?");){
			
			ps.setString(1, categoria);
			ResultSet rs = ps.executeQuery();
			PrintWriter out = response.getWriter();
			ArrayList<Prodotto> consigliati = new ArrayList<>();

			while (rs.next()) {
				String codice = rs.getString("codice");
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				String img = rs.getString("immagine");
				String marca = rs.getString("Marcanome");
				int quantita = rs.getInt("quantita");
				double prezzo = rs.getDouble("prezzo");
				Prodotto prodotto = new Prodotto(codice, nome, descrizione, img, marca, categoria, quantita, prezzo);
				consigliati.add(prodotto);
			}
			consigliati = (ArrayList<Prodotto>) prodottiCasuali(consigliati, consigliati.size());
			out.write(json.toJson(consigliati));
			rs.close();

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
	public List<Prodotto> prodottiCasuali(List<Prodotto> prodotti, int quantita) {
		if(quantita > 5) 
			quantita = 5;
        
		List<Prodotto> prodottiCasuali = new ArrayList<>(5);
        ArrayList<Prodotto> copiaElementi = new ArrayList<>(prodotti);

        Collections.shuffle(copiaElementi);

        for (int i = 0; i < quantita; i++) {
            prodottiCasuali.add(copiaElementi.get(i));
        }

        return prodottiCasuali;
    }
}
