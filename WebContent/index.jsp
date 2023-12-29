<%@ page import="model.IProductDao" %>
<%@ page import="model.ProductDaoDataSource" %>
<%@ page import="model.ProductBean" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./Scripts/IndexScript.js"></script>
<meta charset="ISO-8859-1">
<title>BlackWhite</title>
</head>
<body>
<% if (session.getAttribute("username") != null) { %>
    <form action="LogoutServlet" method="POST">
        <input type="submit" value="Logout">
    </form>
    <form action="PersonalAreaServlet" method="POST">
        <input type="submit" value="Area Personale">
    </form>
<% } else { %>
    <form action="LoginScreen.jsp" method="POST">
        <input type="submit" value="Accedi o registrati">
    </form>
<% } %>
	<form action="CartServlet" method="post">
		<input type="submit" value="carrello">
	</form>
<div align="center" class="header">
<h1>Black&White</h1>
<img src="./Images/rock.png" alt="logo sito">
<p>Benvenuti su Black & White</p>
</div>
<div class=prodotti align="center">
	<%		IProductDao productDao = null;
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	productDao = new ProductDaoDataSource(ds);
	Collection<ProductBean> products = productDao.doRetrieveProducts(); %>
  
  <h2>Prodotti</h2>
<% if (products != null && !products.isEmpty()) { %>
    <% for (ProductBean bean : products) { %>
        <img src="http://drive.google.com/uc?export=view&id=<%= bean.getImage() %>" alt="Immagine" width="100" />
        <p><%=bean.getName()%></p>
        <p><%= String.format("%.2f", bean.getPrice()) %></p>
        <form action="CartServlet" method="POST" class="IndexProduct">
            <input type="hidden" name="action" value="addC">
            <input type="hidden" name="id" value="<%=bean.getCode()%>">
            <input type="hidden" name="quantity" id="quantity_<%=bean.getCode()%>" value="1">
            
            <input type="submit" value="Aggiungi al carrello">
        </form>
    <% } %>
<% } else { %>
    <p>Nessun prodotto disponibile</p>
<% } %>


</div>	
</body>
</html>