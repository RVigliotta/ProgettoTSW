 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*, model.User" %>
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
<meta charset="ISO-8859-1">
<title>Area Personale</title>
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
</div>
<div class="PA" align="center">
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
            	<button id="toggleButton" onclick="toggleAddressDropdown()">Inserire indirizzo</button>
            <% } else { %>
            	<%=user.getIndirizzo()%>
            <% } %>	
        </div>
        
        <div id="addressDropdown" style="display: none;">
            <!-- Campi per l'indirizzo -->
             <form id="addressForm" action="PersonalAreaServlet" method="POST" onsubmit="return validateForm();">
    <!-- Campi per l'indirizzo -->
    		<input type="text" id="state" name="state" placeholder="Stato"><br>
    		<input type="text" id="street" name="street" placeholder="Via"><br>
    		<input type="text" id="number" name="number" placeholder="Numero civico"><br>
    		<input type="text" id="city" name="city" placeholder="Città"><br>
    		<input type="text" id="provincia" name="provincia" placeholder="Provincia"><br>
    		<input type="text" id="zip" name="zip" placeholder="CAP"><br>
    <!-- Bottone per confermare l'aggiornamento -->
    		<input type="hidden" name="action" value="ChangeAddress">
    		<input type="submit" value ="Conferma">
			</form>
        </div>
        <%if(user.getIndirizzo()!=null){ %>
        <button id="toggleButton" onclick="toggleAddressDropdown()">Aggiorna indirizzo</button>
    	<% } %>
    </td>
        </tr>
   
    </table>
</div>
</body>
</html>