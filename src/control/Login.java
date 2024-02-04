package control;

import model.ProductBean;
import model.User;
import model.PADaoDataSource;
import model.PADao;
import model.IProductDao;
import model.Cart;
import model.CartDao;
import model.CartDaoDataSource;
import model.DriverManagerConnectionPool;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/Login")
public class Login extends HttpServlet {


	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        String login = request.getParameter("username");
        String pwd = request.getParameter("password");
        
        ResultSet logincheck=null;
        PreparedStatement statement = null;
        Connection connection = null;
        PADao paDao = new PADaoDataSource(ds);
        
        
        	
            try {
            	DriverManagerConnectionPool connectionPool = DriverManagerConnectionPool.getInstance();
            	connection = connectionPool.getConnection();
            	String query = "SELECT * FROM Utente WHERE (Username,Password) = (?, ?)";
            	statement = connection.prepareStatement(query);
            	statement.setString(1, login);
            	statement.setString(2, pwd);
            	logincheck=statement.executeQuery();
    			if(logincheck.next()) {
    				HttpSession session = request.getSession();
                    int userId = logincheck.getInt("ID");
                    String username = logincheck.getString("Username");
                    boolean isLogged = true; // Imposta isLogged a true
                    getServletContext().setAttribute("isLogged", isLogged); // Aggiorna il contesto dell'applicazione
                    session.setAttribute("userId", userId);
                    session.setAttribute("username", username);
                    User user = paDao.RetrieveUserData(userId);
                    session.setAttribute("user", user);
                 
                 // Dopo che l'utente ha effettuato il login con successo

                    // Salva il carrello non autenticato nel database
                    Cart cart = (Cart) session.getAttribute("cart");
                    if (cart != null && !cart.isEmpty()) {
                        // Ottieni l'ID dell'utente loggato
                        

                        // Salva il carrello non autenticato nel database
                        CartDao cartDao = new CartDaoDataSource(ds);
                        for (ProductBean product : cart.getProducts()) {
                            int productId = product.getCode();
                            int quantity = product.getQuantity();
                            cartDao.cartSave(userId, productId, quantity);
                        }

                        // Ora che il carrello è stato salvato nel database, puoi rimuovere il carrello non autenticato dalla sessione
                        //session.removeAttribute("cart");
                    }
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                    
                } else {//se resultset nullo utnte non in db
                	
                    // Autenticazione fallita, mostra un messaggio di errore
                	 String errorMessage = "Credenziali di accesso non valide";
                	    request.setAttribute("errorMessage", errorMessage);

                	    // Esegui uno script JavaScript per mostrare l'alert con SweetAlert2
                	    String script = "Swal.fire({ " +
                	            "title: 'Errore', " +
                	            "text: '" + errorMessage + "', " +
                	            "icon: 'error', " +
                	            "confirmButtonText: 'OK' " +
                	            "});";
                	    request.setAttribute("errorScript", script);
                    request.getRequestDispatcher("LoginScreen.jsp").forward(request, response);
                    return;
                }	
            }
            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (logincheck != null) {
                        logincheck.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	

}
