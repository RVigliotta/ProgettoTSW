package control;

import java.util.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Cart;
import model.DriverManagerConnectionPool;
import model.IProductDao;
import model.ProductDaoDataSource;
import model.CartDaoDataSource;
import model.CartDao;
import model.ProductBean;
/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection=null;  
	ResultSet cartCheck=null;
    PreparedStatement statement = null;
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() throws ServletException {
        super.init();
        try {
        	DriverManagerConnectionPool connectionPool = DriverManagerConnectionPool.getInstance();
        	connection = connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		IProductDao productDao = null;

			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			productDao = new ProductDaoDataSource(ds);
			Boolean isLogged=(Boolean) getServletContext().getAttribute("isLogged");
			Boolean cartLoaded=(Boolean) getServletContext().getAttribute("cartLoaded");
			CartDao cartDao = new CartDaoDataSource(ds);
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			Integer userId = (Integer)request.getSession().getAttribute("userId");
			
			if(cart == null){
				cart = new Cart();
				request.getSession().setAttribute("cart", cart);
		}
			
			
		if (isLogged && !cartLoaded) {
			// Recupera la lista dei prodotti nel carrello
			try {
				
				Collection<ProductBean> productsInCart = cartDao.doRetrieveProducts(userId);
		        
		        // Crea una lista temporanea per i prodotti nel carrello
		        Collection<ProductBean> productsToAdd = new ArrayList<>();
		        
		        // Itera attraverso i prodotti recuperati e aggiungili alla lista temporanea
		        for (ProductBean product : productsInCart) {
		            productsToAdd.add(product);
		        }
		        
		        // Aggiungi tutti i prodotti dalla lista temporanea al carrello
		        cart.addProducts(productsToAdd);
		        
		        getServletContext().setAttribute("cartLoaded", true);
		        // Imposta l'attributo nella richiesta
		        request.setAttribute("products", productsInCart);
				request.getSession().setAttribute("cart", cart);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("addC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					
					
			/*else*/ cart.addProduct(productDao.doRetrieveByKey(id));
			int quantity = 1; // Imposta sempre la quantità a 1
			if (isLogged) {
			    cartDao.cartSave(userId, id, quantity);
			}


				} else if (action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					int quantity=Integer.parseInt(request.getParameter("quantity"));
					cart.deleteProduct(productDao.doRetrieveByKey(id));
						if(isLogged) {
								cartDao.cartDelete(userId, id, quantity);								
						}
					}
				else if (action.equalsIgnoreCase("delAllC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.delAll(productDao.doRetrieveByKey(id));
						if(isLogged) {
								cartDao.cartDelAll(userId, id);								
						}
					}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CartView.jsp");
		dispatcher.forward(request, response);
	}
	

}