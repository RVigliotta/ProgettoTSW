package control;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddProductServlet.class.getName());
	private static final String error = "Errore";

	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		String[] tokens = contentDisposition.split(";");

		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return "";
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		final String query = "INSERT INTO Prodotti(codice, nome, descrizione, immagine, prezzo, quantita, Marcanome, Categorianome) values(?,?,?,?, ?, ?, ?, ?)";

		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);) {
			String codice = request.getParameter("codice");
			String nome = request.getParameter("nome");
			String descrizione = request.getParameter("descrizione");
			String immagine = request.getParameter("immagine");
			String prezzoString =(request.getParameter("prezzo"));
			String quantitaString = (request.getParameter("quantita"));
			String marca = request.getParameter("marca");
			String categoria = request.getParameter("categoria");


			ps.setString(1, codice);
			ps.setString(2, nome);
			ps.setString(3, descrizione);
			ps.setString(4, immagine);
			ps.setDouble(5, Double.parseDouble(prezzoString));
			ps.setInt(6, Integer.parseInt(quantitaString));
			ps.setString(7, marca);
			ps.setString(8, categoria);
			ps.executeUpdate();

			Part imagePart = request.getPart("file");
			if (imagePart.getSize() != 0) {
				String file = getFileName(imagePart);
				String saveDirectory = "/Users/Rocco/git/repository/Progetto/WebContent/Images";
				String imagePath = saveDirectory + File.separator + file; // Percorso per salvare l'immagine

				imagePart.write(imagePath);
			}

			dispatcher = request.getRequestDispatcher("profilo.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (ServletException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		} catch (NumberFormatException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}