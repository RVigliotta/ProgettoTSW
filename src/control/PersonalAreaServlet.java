package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.PADao;
import model.PADaoDataSource;
import model.User;
/**
 * Servlet implementation class PersonalAreaServlet
 */
@WebServlet("/PersonalAreaServlet")
public class PersonalAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalAreaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		PADao paDao = new PADaoDataSource(ds);
		
		try {
			String action = request.getParameter("action");
			  User user = (User) request.getSession().getAttribute("user");
			request.setAttribute("user", user);
				
				if (action != null) {
					if (action.equalsIgnoreCase("ChangeAddress")) {
						String Stato = request.getParameter("state");
						String Città = request.getParameter("city");
						String Provincia = request.getParameter("provincia");
						String Via = request.getParameter("street");
						String Number = request.getParameter("number");
						String Cap = request.getParameter("zip");
						String Address = Stato + "<br>" + Via + " " + Number +"<br>"+ Città + " " + "(" +Provincia +")" + "<br>" + Cap;
				
						paDao.ChangeAddress(userId,Address);
						user.setIndirizzo(Address);
					}
				}
		    // Ora puoi inoltrare la richiesta alla tua pagina JSP per visualizzare i dati dell'utente
		    request.getRequestDispatcher("PAView.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
