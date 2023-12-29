package control;

import model.Order;
import model.OrderDao;
import model.OrderDaoDataSource;
import model.Cart;
import model.CartDao;
import model.CartDaoDataSource;
import model.IProductDao;
import model.ProductBean;
import model.ProductDaoDataSource;
import model.User;
import com.google.gson.Gson;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
			CartDao cartDao = new CartDaoDataSource(ds);
			OrderDao orderDao = new OrderDaoDataSource(ds);
			
			LocalDateTime now = LocalDateTime.now();

			// Formatta la data e l'ora in una stringa leggibile
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = now.format(formatter);
			Gson gson = new Gson();
		Order order = (Order) new Order();
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		Integer pdid = (Integer)request.getSession().getAttribute("productId");
		List<ProductBean> OrderCart = cart.getProducts();
		for(ProductBean orderBean : OrderCart)
			System.out.println(orderBean.getQuantity());
		
		order.addProductsFromCart(OrderCart);
		String cartJson=gson.toJson(cart);
		
		
		request.setAttribute("order", order);
		
		try {
			String action = request.getParameter("action");
			  
				
			if (action != null) {
		        if (action.equalsIgnoreCase("Acquista")) {
		            orderDao.OrderSave(userId, formattedDateTime, cartJson,pdid);
		            orderDao.CartDelete(userId);
		            for (ProductBean orderBean : OrderCart) {
		                int productId = orderBean.getCode(); // Sostituisci con il metodo corretto per ottenere l'ID del prodotto
		                int quantityPurchased = orderBean.getQuantity(); // Ottieni la quantità acquistata

		                // Ottieni il prodotto esistente dal database
		                ProductBean existingProduct = productDao.doRetrieveByKey(productId);

		                if (existingProduct != null) {
		                    int currentQuantity = existingProduct.getQuantity();
		                    int newQuantity = currentQuantity - quantityPurchased;

		                    if (newQuantity >= 0) {
		                        // Aggiorna la quantità disponibile nel database
		                        existingProduct.setQuantity(newQuantity);
		                        productDao.doUpdate(existingProduct);
		                    }
		                }
		            }
		            request.getSession().removeAttribute("cart");
		            	            
		            // Recupera gli ordini dell'utente
		            List<Order> orders = orderDao.DoRetrieveOrders(userId);
		            
		            // Passa gli ordini alla pagina JSP
		            request.setAttribute("orders", orders);
		            
		            // Inoltra alla pagina JSP per visualizzare gli ordini
		            request.getRequestDispatcher("OrderPage.jsp").forward(request, response);
		        }
		    }
		
		
		
		request.getRequestDispatcher("OrderView.jsp").forward(request, response);
	
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}	
	}
	
}

