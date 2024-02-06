<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, javax.servlet.jsp.tagext.TagExtraInfo, model.Order, model.ProductBean, model.OrderDao, model.OrderDaoDataSource" %>
<%@ page import="java.util.Collection" %>
<%@ page import="javax.sql.DataSource" %>


   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
   <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html> 
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="meta.html"%>
    <%@ include file="header.jsp"%>
    <title>Elenco Ordini</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div class="OrderPage">
    <h2>Elenco Ordini</h2>
    

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
                
                <th colspan="4">Stato: <%= bean.getState()%></th>
                
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
            <th colspan="4">Prezzo Totale: <%= bean.getTotalPrice()%> </th>
				  </tr>
        </table>
    <% } %>
    
    </div>
    
    <%@ include file="footer.jsp"%>
</body>
</html>