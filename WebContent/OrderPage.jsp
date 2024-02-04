<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, javax.servlet.jsp.tagext.TagExtraInfo, model.Order, model.ProductBean, model.OrderDao, model.OrderDaoDataSource" %>
<%@ page import="java.util.Collection" %>
<%@ page import="javax.sql.DataSource" %>


   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
   <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html> 
<html>
<head>
    <meta charset="UTF-8">
    <title>Elenco Ordini</title>
</head>
<body>
<div class="navBar" align = "left">
	<form action="CartServlet" method="post">
		<input type="submit" value="carrello">
	</form>
	<form action="LogoutServlet" method="POST">
        <input type="submit" value="Logout">
    </form>
   	<form action="index.jsp" method="POST">
        <input type="submit" value="home">
    </form>
    <form action="PersonalAreaServlet" method="POST">
        <input type="submit" value="Area Personale">
    </form>
</div>
    <h1>Elenco Ordini</h1>
    

    <% 
    Integer i= (Integer)session.getAttribute("userId");
    OrderDao ord = null;
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	ord = new OrderDaoDataSource(ds);
	Collection<Order> orders= ord.DoRetrieveOrders(i);
	    
    for (Order bean : orders) { %>
        <h2>Data e Ora: <%= bean.getDateTime() %> </h2>
        <table border ="1">
            <tr>
                
                <th>Stato: <%= bean.getState()%></th>
                
                <!-- Altre colonne, se necessario -->
            </tr>
          <%  for (ProductBean p : bean.products){ %>
                <tr>
					<td> <img src="./Images/products/<%= p.getImage() %>" alt="Immagine" width="100" /></td>               
				    <td><%=p.getName() %></td>
                    <td>Quantità: <%=p.getQuantity() %></td>
                    <td>Prezzo: <%=p.getPrice() %></td>
                    
                    
                </tr>
                
            <% } %>
            <tr>
            <th>Prezzo Totale: <%= bean.getTotalPrice()%> </th>
            </td>
				  </tr>
        </table>
    <% } %>
</body>
</html>