package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/NameServlet")
public class NameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(NameServlet.class.getName());
	private static final String error = "Errore";	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();

		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT nome FROM Prodotti ORDER BY nome");){				
			ResultSet rs = ps.executeQuery();
			PrintWriter out = response.getWriter();
			ArrayList<String> nomi = new ArrayList<>();
			
			while (rs.next()) 
				nomi.add(rs.getString("nome"));		
			
			rs.close();
			out.write(json.toJson(nomi));

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}

}