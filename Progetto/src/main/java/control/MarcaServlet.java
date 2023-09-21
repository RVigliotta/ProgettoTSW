package control;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/MarcaServlet")
public class MarcaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MarcaServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();
		try (Connection connection = DbManager.getConnection(); 
				Statement s = connection.createStatement();) {
			PrintWriter out = response.getWriter();

			String query = "SELECT nome FROM Marca";
			ResultSet rs = s.executeQuery(query);
			ArrayList<String> marche = new ArrayList<>();

			/* Aggiunta generi all'arraylist */
			while (rs.next()) {
				String g = rs.getString("nome");
				marche.add(g);
			}

			Collections.sort(marche);
			out.write(json.toJson(marche));

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}