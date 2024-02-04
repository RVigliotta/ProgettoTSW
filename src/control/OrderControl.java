package control;

import model.IProductDao;
import model.Order;
import model.OrderDao;
import model.OrderDaoDataSource;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@MultipartConfig
/**
 * Servlet implementation class orderControl
 */
 @WebServlet("/OrderControl")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderControl() {
		super();
	}
///AGGIOUNGERE CONTROLLO SU INSERIMENTO ID
	
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		OrderDao orderDao = null;

			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			orderDao = new OrderDaoDataSource(ds);
		
		
		String action = request.getParameter("action");

		try {
			if (action != null) {///AGGIOUNGERE CONTROLLO SU INSERIMENTO ID
				if (action.equalsIgnoreCase("details")) {
					int id = Integer.parseInt(request.getParameter("id"));///////
					request.removeAttribute("order");
					request.setAttribute("order", orderDao.DoRetrieveOrders(id));
				} else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					orderDao.doDelete(id);
				
				}	else if(action.equalsIgnoreCase("pdate")) { //aggiorna vicino al prodotto
					int id = Integer.parseInt(request.getParameter("id"));
				    
				    request.setAttribute("existingorder", orderDao.DoRetrieveOrders(id));
				    
				} else if(action.equalsIgnoreCase("Annulla")) { //aggiorna vicino al prodotto
					   
					    
					    request.setAttribute("existingorder", null);
					    
					    /*
					     * private double totalPrice;
	private String DateTime;
	private int userId;
	private List<ProductBean> products;
	private String state;
					     */
					     
					    
					    
					    
				    
				} else if (action.equalsIgnoreCase("pdate")) {///AGGIOUNGERE CONTROLLO SU INSERIMENTO ID
					
					double totalPrice= Double.parseDouble(request.getParameter("totalprice"));
					String DateTime = request.getParameter("data");
					String state = request.getParameter("state");
					int userId= Integer.parseInt(request.getParameter("id"));
					
			        
					Order bean = new Order();
					
					bean.setUserId(userId);
					bean.setDateTime(DateTime);
					bean.setState(state);
					
					

					orderDao.doUpdate(bean);
					
				} /*else if (action.equalsIgnoreCase("insert")) {
					String name = request.getParameter("name");
					int id = Integer.parseInt(request.getParameter("id"));
					String description = request.getParameter("description");
					float price = Float.parseFloat(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					int CategoriaID = Integer.parseInt(request.getParameter("CategoriaID"));
					String imag =request.getParameter("image");
					
					orderBean bean = new orderBean();
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);
					bean.setCategoriaID(CategoriaID);
					bean.setImage(imag);
					bean.setCode(id);
					orderDao.doSave(bean);
				}*/
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		
		
		/*String sort = request.getParameter("sort");*/

		try {
			request.removeAttribute("orders");
			request.setAttribute("orders", orderDao.DoRetrieveAllOrder());
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderCTRL.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
