package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni la sessione corrente
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Rimuovi gli attributi della sessione
            session.removeAttribute("userId");
            session.removeAttribute("username");
            session.removeAttribute("cart");
            boolean isLogged = false; // Imposta isLogged a false
            boolean cartLoaded = false;
            getServletContext().setAttribute("isLogged", isLogged); // Aggiorna il contesto dell'applicazione
            getServletContext().setAttribute("cartLoaded", cartLoaded);
            
            
            // Invalida la sessione
            session.invalidate();
        }
        
        // Reindirizza l'utente alla pagina di login o a un'altra pagina appropriata
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

