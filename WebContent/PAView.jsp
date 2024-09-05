 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, model.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="javax.sql.DataSource" %>
<%  
    User user = (User) request.getAttribute("user");
//BIsogna comunque un controllo per vedre se l'utente esiste in sessione ?
		//anche se guest non ci arriverà mai a questa pagina??
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="./Scripts/PAScripts.js"></script>
<%@ include file="meta.html"%>
<%@ include file="header.jsp"%>
<meta charset="UTF-8">
<title>Area Personale</title>
</head>
<body>
	<div class="PA-navBar">
		<form action="CartServlet" method="post">
			<input type="submit" value="Carrello">
		</form>
		
		<%		PADao p = null;
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	p = new PADaoDataSource(ds);
	
	Integer i= (Integer)session.getAttribute("userId");
	
	boolean b1 = p.UserCheck(i.intValue(), "ecommerce.gestorecatalogo");
	boolean b2= p.UserCheck(i.intValue(), "ecommerce.gestoreordini");
	//rifare con attribute nella servlet?
	//utente non deve vedre con f12 il nome delle tabelle
 if (b1 ==true||i.intValue()==1) { %>
		<form action="ProductView.jsp" method="POST">
			<input type="submit" value="Pagina Gestore Catalogo">
		</form>

		<% }if (b2== true||i.intValue()==1) { %>
		<form action="OrderCTRL.jsp" method="POST">
			<input type="submit" value="Pagina Gestore Ordini">
		</form>

		<% } %>

		<form action="LogoutServlet" method="POST">
			<input type="submit" value="Logout">
		</form>
		<form action="index.jsp" method="POST">
			<input type="submit" value="Home">
		</form>
	</div>
	<div class="PA">
	
	<div>
		<h2>Area Personale</h2>
		<table border="1">
			<tr>
				<th>Nome</th>
				<td><%= user.getNome()%></td>
			</tr>
			<tr>
				<th>Cognome</th>
				<td><%=user.getCognome() %></td>
			</tr>
			<tr>
				<th>Username</th>
				<td><%= user.getUsername()%></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><%= user.getEmail()%></td>
			</tr>
			<tr>
				<th>Indirizzo di Spedizione</th>
				<td>
					<div id="currentAddress">
						<%if(user.getIndirizzo()==null){ %>
						<button id="toggleButton" onclick="toggleAddressDropdown()">Inserire
							indirizzo</button>
						<% } else { %>
						<%=user.getIndirizzo()%>
						<% } %>
					</div>

					<div id="addressDropdown" style="display: none;">
						<!-- Campi per l'indirizzo -->
						<form id="addressForm" action="PersonalAreaServlet" method="POST"
							onsubmit="return validateForm();">
							<!-- Campi per l'indirizzo -->
							<input type="text" id="state" name="state" placeholder="Stato"><br>
							<input type="text" id="street" name="street" placeholder="Via"><br>
							<input type="text" id="number" name="number" placeholder="Numero civico"><br>
							<input type="text" id="city" name="city" placeholder="Città"><br>
							<input type="text" id="provincia" name="provincia" placeholder="Provincia"><br>
							<input type="text" id="zip" name="zip" placeholder="CAP"><br>
							<!-- Bottone per confermare l'aggiornamento -->
							<input type="hidden" name="action" value="ChangeAddress">
							<input type="submit" value="Conferma">
						</form>
					</div> <%if(user.getIndirizzo()!=null){ %>
					<button id="toggleButton" onclick="toggleAddressDropdown()">Aggiorna
						indirizzo</button> <% } %>
				</td>
			</tr>

		</table>
	</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>