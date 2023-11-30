package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeleteProductServlet.class.getName());
	private static final String error = "Errore";
	private static final String status = "status";
	private static final String contentType = "application/json";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scelta = request.getParameter("scelta");
		Gson json = new Gson();

		try (Connection connection = DbManager.getConnection();
			Statement s = connection.createStatement();){
			PrintWriter out = response.getWriter();
			
			if (scelta.equals("") || scelta.equals("-seleziona un prodotto-")) {
				HashMap<String, String> responseMap = new HashMap<>();
				responseMap.put(status, "Invalid_Prodotto");
				String jsonResponse = json.toJson(responseMap);
				response.setContentType(contentType);
				out.write(jsonResponse);
				out.flush();
				return;
			}
			
			String query = "DELETE FROM Prodotti WHERE nome = '" + scelta + "'";

			if (request.getParameter("risposta").equals("conferma")) {
				int rowsDeleted = s.executeUpdate(query);
				if (rowsDeleted > 0) {
					HashMap<String, String> responseMap = new HashMap<>();
					responseMap.put(status, "success");
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
			}

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}