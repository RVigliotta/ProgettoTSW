package control;

import model.ProductBean;
import model.IProductDao;

import model.ProductDaoDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;


@MultipartConfig
/**
 * Servlet implementation class ProductControl
 */
 @WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductControl() {
		super();
	}
///AGGIOUNGERE CONTROLLO SU INSERIMENTO ID
	
	
	
	private String getFileName(Part part) {
	    String contentDisposition = part.getHeader("content-disposition"); 
	    String[] tokens = contentDisposition.split(";");

	    for (String token : tokens) {
	        if (token.trim().startsWith("filename")) {
	            return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }

	    return null;
	}
	
	//Non funge :(
	//String saveDirectory = Paths.get(".").toAbsolutePath().normalize().toString().replace("java\\control\\", "WebContent\\Images\\Products");
	
	private static String saveDirectory = "C:\\Users\\Rocco\\OneDrive\\Desktop\\TSW\\eclipse-jee-2023-03-R-win32-x86_64\\ProgettoTSW\\WebContent\\Images\\";
	
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		IProductDao productDao = null;

			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			productDao = new ProductDaoDataSource(ds);
		
		
		String action = request.getParameter("action");

		try {
			if (action != null) {///AGGIOUNGERE CONTROLLO SU INSERIMENTO ID
				if (action.equalsIgnoreCase("details")) {
					int id = Integer.parseInt(request.getParameter("id"));///////
					request.removeAttribute("product");
					request.setAttribute("product", productDao.doRetrieveByKey(id));
				} else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					productDao.doDelete(id);
				
				}	else if(action.equalsIgnoreCase("pdate")) { //aggiorna vicino al prodotto
					int id = Integer.parseInt(request.getParameter("id"));
				    ProductBean existingProduct = productDao.doRetrieveByKey(id);
				    
				    request.setAttribute("existingProduct", existingProduct);
				    
				} else if(action.equalsIgnoreCase("Annulla")) { //aggiorna vicino al prodotto
					   
					    
					    request.setAttribute("existingProduct", null);
				    
				} else if (action.equalsIgnoreCase("update")) {///AGGIOUNGERE CONTROLLO SU INSERIMENTO ID
					int id = Integer.parseInt(request.getParameter("id"));////////////
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					float price = Float.parseFloat(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					int CategoriaID = Integer.parseInt(request.getParameter("CategoriaID"));
					

					//upload immagine
					Part imagePart = request.getPart("image");
			        String image = getFileName(imagePart); // Ottieni il nome dell'immagine
			       
			        //modo inutilmente complesso per prendere PWD indipendente da system
			        //ultima pate rimpiazzata con cartella di destinazione
			        //Brutto
			        System.out.println(saveDirectory);			        
			        String imagePath = saveDirectory + File.separator + image; // Percorso per salvare l'immagine
					
					
			        
					ProductBean bean = new ProductBean();
					bean.setCode(id);
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);
					bean.setCategoriaID(CategoriaID);
					
					if (imagePart != null && imagePart.getSize() > 0) { //se c'è una nuova immagine la aggiorni
						imagePart.write(imagePath);
						bean.setImage(image);
					}
					else {
						ProductBean existingProduct = productDao.doRetrieveByKey(id);
						bean.setImage(existingProduct.getImage());
					}

					productDao.doUpdate(bean);
					
				} else if (action.equalsIgnoreCase("insert")) {
					String name = request.getParameter("name");
					int id = Integer.parseInt(request.getParameter("id"));
					String description = request.getParameter("description");
					float price = Float.parseFloat(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					int CategoriaID = Integer.parseInt(request.getParameter("CategoriaID"));
				
					//img
					Part imagePart = request.getPart("image");
			        String image = getFileName(imagePart); // Ottieni il nome dell'immagine
			    
			        String imagePath = saveDirectory + File.separator + image; // Percorso per salvare l'immagine
					
			        imagePart.write(imagePath);
					
					ProductBean bean = new ProductBean();
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);
					bean.setCategoriaID(CategoriaID);
					bean.setCode(id);
					bean.setImage(image);
					productDao.doSave(bean);
				}
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		
		
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", productDao.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
