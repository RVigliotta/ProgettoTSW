<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, javax.servlet.jsp.tagext.TagExtraInfo, model.Order, model.ProductBean" %>


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
    
    <c:forEach var="order" items="${orders}">
        <h2>Data e Ora: ${order.dateTime}</h2>
        <table border ="1">
            <tr>
            	<th></th>
                <th>Nome Prodotto</th>
                <th>Quantità</th>
                
                <!-- Altre colonne, se necessario -->
            </tr>
            <c:forEach var="product" items="${order.products}">
                <tr>
					<td><img src="./Images/${product.image}"  width="80" height="80"></td>               
				    <td>${product.name}</td>
                    <td>${product.quantity}</td>
                    
                    
                    <!-- Altre colonne, se necessario -->
                </tr>
                
            </c:forEach>
            <tr>
            <th>Totale</th>
                <td colspan="2">
				<fmt:formatNumber value="${order.totalPrice}" type="number" minFractionDigits="2" maxFractionDigits="2" />          
				</td>
				  </tr>
        </table>
    </c:forEach>
</body>
</html>