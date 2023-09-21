package control;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Prodotto;


@WebServlet("/RimuoviProdotto")
public class RimuoviProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("Carrello");
		String codiceProdotto = request.getParameter("codice");	
		ArrayList<Prodotto> list = (ArrayList<Prodotto>) carrello.getCarrello();	

		for(Prodotto p : list) {
			if(codiceProdotto != null) {
				if(p.getCodice().equals(codiceProdotto)) {
					list.remove(p);
					break;
				}
			}else return;
		}
		
		carrello.setCarrello(list);
		session.setAttribute("Carrello", carrello);		
	}
}