package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/welcomeServlet")
public class Welcome extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	       
	       String param1 = (String) request.getSession().getAttribute("uname");
	 
	       PrintWriter out = response.getWriter();
	       out.println("<!doctype html>");
	       out.println("<html><body>");
	       out.println("<h2>Servlet SendRedirect Example</h2>");
	       out.println("<p style='color: green;'>");
	       out.println("Congratulations! "+ param1 + ", You are an authorised login!</p>");
	       out.println("</body></html>");
	}
}
