package control;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EditProductServlet.class.getName());
	private static final String error = "Errore";
	private static final String status = "status";
	private static final String contentType = "application/json";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();

		String pattern = "\\./images/[^/]+\\.[a-zA-Z]{3,4}";
		String prodotto = request.getParameter("scelta");
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String immagine = request.getParameter("immagine");
		double prezzo = 0.00;
		String prezzoString = request.getParameter("prezzo");
		String quantitaString = request.getParameter("quantita");
		int quantita = -1;
		String marca = request.getParameter("marca");
		String categoria = request.getParameter("categoria");

		try (Connection connection = DbManager.getConnection();
				Statement s = connection.createStatement();
				PreparedStatement ps = connection.prepareStatement("UPDATE Prodotti "
						+ "SET nome=?, descrizione=?, immagine=?, prezzo=?, quantita=?, Categorianome=?, Marcanome=? "
						+ "WHERE codice=?");) {
			PrintWriter out = response.getWriter();

			if (prodotto.equals("") || prodotto.equals("-seleziona un prodotto-")) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_prodotto");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			}

			
		    if (nome.matches(".*[^a-zA-Z0-9 ].*")) {
		        HashMap<String, String> responseMap = new HashMap<>();
		        responseMap.put(status, "Invalid_nome_caratteri_speciali");
		        String jsonResponse = json.toJson(responseMap);
		        response.setContentType(contentType);
		        out.write(jsonResponse);
		        out.flush();
		        return;
		    }
		    
			if (nome.equals("")) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_nome");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			}

			if (descrizione.equals("")) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_descrizione");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			}

			Pattern regex = Pattern.compile(pattern);
			Matcher matcher = regex.matcher(immagine);

			if (immagine.equals("") || !(matcher.matches())) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_path");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			}

			if ((prezzoString == null) || (prezzoString.isEmpty())) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_prezzo");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			} else {
				prezzo = Double.parseDouble(prezzoString);
				if (prezzo == 0.00) {
					HashMap<String, String> responseMap = new HashMap<>();
					responseMap.put(status, "Invalid_prezzo");
					String jsonResponse = json.toJson(responseMap);
					response.setContentType(contentType);
					out.write(jsonResponse);
					out.flush();
					return;
				}
			}

			if ((quantitaString == null) || (quantitaString.isEmpty())) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_quantita");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			} else {
				quantita = Integer.parseInt(quantitaString);
				if (quantita == 0) {
					HashMap<String, String> responseMap = new HashMap<>();
					responseMap.put(status, "Invalid_quantita");
					String jsonResponse = json.toJson(responseMap);
					response.setContentType(contentType);
					out.write(jsonResponse);
					out.flush();
					return;
				}
			}

			if (marca.equals("") || marca.equals("-scegliere marca-")) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_marca");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			}

			if (categoria.equals("") || categoria.equals("-scegliere categoria-")) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_categoria");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			}

			int rowCount = 0;
			String query = "SELECT codice FROM Prodotti WHERE nome = '" + prodotto + "'";

			ResultSet rs = s.executeQuery(query);

			rs.next();
			String codice = rs.getString("codice");

			ps.setString(1, nome);
			ps.setString(2, descrizione);
			ps.setString(3, immagine);
			ps.setDouble(4, prezzo);
			ps.setInt(5, quantita);
			ps.setString(6, categoria);
			ps.setString(7, marca);
			ps.setString(8, codice);
			rowCount = ps.executeUpdate();

			if (rowCount > 0) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "success");
				responseMap.put("url", "profilo.jsp");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
			} else {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "failed");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
			}

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (NumberFormatException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}