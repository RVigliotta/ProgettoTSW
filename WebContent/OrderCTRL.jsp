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
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,model.Order"%>

<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<link href="ProductStyle.css" rel="stylesheet" type="text/css">
<%@ include file="meta.html"%>
<%@ include file="header.jsp"%>
<title>Gestione Ordini</title>
</head>

<body>
	<%@ include file="nav.jsp"%>
	<div class="OrderCTRL">
	
	<h2>Ordini effettuati</h2>
	<table>
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
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="id" value="<%=bean.getUserId()%>">
					<input type="submit" value="Rimuovi" class="OrderCTRL-top">
				</form> <br>
				<form action="OrderControl" method="post">
					<input type="hidden" name="driver" value="drivermanager">
					<input type="hidden" name="action" value="details">
					<input type="hidden" name="id" value="<%=bean.getUserId()%>">
					<input type="submit" value="Dettagli" class="OrderCTRL-top">
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
	<table>
		<tr>
			<th>Codice Utente</th>
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
	%>
	
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>