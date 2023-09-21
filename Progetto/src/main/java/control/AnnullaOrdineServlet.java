package control;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AnnullaOrdineServlet")
public class AnnullaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AnnullaOrdineServlet.class.getName());
	private static final String error = "Errore";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		RequestDispatcher dispatcher = null;

		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE Ordini SET Statoid = 2 WHERE id = ?");){					
			ps.setString(1, id);
			ps.executeUpdate();

			dispatcher = request.getRequestDispatcher("controllaordini.jsp");
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