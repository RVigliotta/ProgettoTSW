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

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CategoriaServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();
		try (Connection connection = DbManager.getConnection(); Statement s = connection.createStatement();) {
			PrintWriter out = response.getWriter();
			ArrayList<String> categorie = new ArrayList<>();
			String query = "SELECT nome FROM Categoria";
			ResultSet rs = s.executeQuery(query);

			/* Aggiunta categorie all'arraylist */
			while (rs.next()) {
				String c = rs.getString("nome");
				categorie.add(c);
			}

			Collections.sort(categorie);
			out.write(json.toJson(categorie));

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}