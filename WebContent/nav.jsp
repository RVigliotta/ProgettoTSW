<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<div class="navbar-right">
	<form action="./" method="POST">
		<img src="<%=request.getContextPath()%>/icons/home.png" alt="Home">
		<input type="submit" value="Home">
	</form>
	<% if (session.getAttribute("username") != null) { %>
	<form action="LogoutServlet" method="POST">
		<img src="<%=request.getContextPath()%>/icons/logout.png" alt="Logout">
		<input type="submit" value="Logout">
	</form>
	<form action="PersonalAreaServlet" method="POST">
		<img src="<%=request.getContextPath()%>/icons/area-personale.png" alt="Area-Personale">
		<input type="submit" value="Area Personale">
	</form>
	<% } else { %>
	<form action="LoginScreen.jsp" method="POST">
		<img src="<%=request.getContextPath()%>/icons/registration.png" alt="Registrazione">
		<input type="submit" value="Accedi o Registrati">
	</form>
	<% } %>
	<form action="CartServlet" method="post">
		<img src="<%=request.getContextPath()%>/icons/cart.png" alt="Carrello">
		<input type="submit" value="Carrello">
	</form>
</div>
</html>