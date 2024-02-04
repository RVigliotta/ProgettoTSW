<%@page import="model.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 

	Collection<?> ordini = (Collection<?>) request.getAttribute("orders");
	if(ordini == null) {
		response.sendRedirect("./orderman");	
		return;
	}
	
	Collection<?> o = (Collection<?>) request.getAttribute("order");
	
	Collection<?> existingO= (Collection<?>) request.getAttribute("existingorder");
	
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.Order"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Gestione Ordini</title>
</head>

<body>
<form action="index.jsp" method="POST">
        <input type="submit" value="home">
        </form>

    <form action="LogoutServlet" method="POST">
        <input type="submit" value="Logout">
    </form>
    
    
	<h2>Ordini</h2>
	<table border="1">
		<tr>
			<th>CodiceCarrello</th>
			<th>PrezzoTotale</th>
			<th>Data</th>
			<th>Stato</th>
		</tr>
		<%
		
		
		
			if (ordini != null && ordini.size() != 0) {
				Iterator<?> it = ordini.iterator();
				while (it.hasNext()) {
					Order bean = (Order) it.next();
					
		%>
		<tr>
			<td><%=bean.getUserId() %></td>
			<td><%=bean.getTotalPrice()%></td>
			<td><%=bean.getDateTime()%></td>
			<td><%=bean.getState()%></td>
			<td>
				<form action="OrderControl" method="post">
           			<input type="hidden" name="driver" value="drivermanager">
            		<input type="hidden" name="action" value="pdate">
            		<input type="hidden" name="id" value="<%=bean.getUserId()%>">
           			<input type="submit" value="Aggiorna">
        		</form>
        		<br>
        		<form action="OrderControl" method="post">
           			<input type="hidden" name="driver" value="drivermanager">
            		<input type="hidden" name="action" value="delete">
           			<input type="hidden" name="id" value="<%=bean.getUserId()%>">
           			<input type="submit" value="Rimuovi">
       			</form>
        	<br>
        		<form action="OrderControl" method="post">
           			<input type="hidden" name="driver" value="drivermanager">
            		<input type="hidden" name="action" value="details">
            		<input type="hidden" name="id" value="<%=bean.getUserId()%>">
           			<input type="submit" value="Dettagli">
        		</form>
        	
        		
   			</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessun ordine disponibile</td>
		</tr>
		<%
			}
		%>
	</table>
	
	
	<%
		if (o != null && o.size() != 0) {
			Iterator<?> it = o.iterator();
			while (it.hasNext()) {
				Order o1 = (Order) it.next(); 
	%>
	<h2>Dettagli</h2>
	<table border="1">
		<tr>
			<th>Codice Utente </th>
			<th>Prezzo Totale</th>
			<th>Data</th>
			<th>Stato</th>
		</tr>
		<tr>
			<td><%=o1.getUserId()%></td>
			<td><%= String.format("%.2f", o1.getTotalPrice()) %></td>
			<td><%=o1.getDateTime()%></td>
			<td><%=o1.getState()%></td>
		</tr>
	</table>
	<%
		}}
	%><% if (request.getAttribute("existingorder") != null) { 
	
		if (existingO != null && existingO.size() != 0) {
			Iterator<?> it = existingO.iterator();
			while (it.hasNext()) {
				Order b = (Order) it.next();
	
	%>
    <h2>Aggiornamento ordine</h2>
    <form action="OrderControl" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%=b.getUserId()%>">
        
        <label for="id">Cambia UtenteCarrello:</label><br> 
        <input name="id" type="number" min="1" value="1" value="<%=b.getUserId()%>"><br> 

		<label for="totalprice">Prezzo Totale:</label><br> 
		<input name="totalprice" type="text"  step="0.01" min="0.00" value="<%=b.getTotalPrice() %>" required><br>

		<label for="data">Data:</label><br> 
		<input name="data" type="number" min="1" value="1" required><br>
		
		<label for="state">Stato:</label><br> 
		<input name="state" type="text" min="1" value="1" required><br>
		
        <input type="submit" value="Aggiorna"><input type="reset" value="Reset"><input type="submit" name="action "value="Annulla"> 
    </form>

	<% } } }%>
</body>
</html>