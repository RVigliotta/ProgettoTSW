<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*, java.text.SimpleDateFormat,model.OrdiniList,model.Ordine,model.SingoloOrdine, model.Utente"%>
<jsp:include page="./header.jsp" flush="true" />
<%
Utente user = (Utente) session.getAttribute("Utente");
if (user == null)
	response.sendRedirect("login.jsp");
%>
<script src="./Script/ordini.js"></script>
<script src="./Script/dynamicCode.js"></script>
<script>
	document.addEventListener("DOMContentLoaded", dynamicCheckOrders("<%=request.getContextPath()%>/CheckOrders"));
</script>

<jsp:include page="./Nav.jsp" flush="true" />

<body>
<section>
<h2> Controlla Ordini</h2>
	<div id="filtri">
	<h5>Filtri</h5>
		<div class="user-search">
			<label>Utente</label>
			<input type="text" id="searchInput" placeholder="Cerca per User ID" onkeyup="filterRows()">
		</div>
		<div>
			<label>da </label>
			<input type="date" id="startDateInput" onchange="filterRows()" />
			<label>a </label>
			<input type="date" id="endDateInput" onchange="filterRows()" />
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<th>Data</th>
				<th> Utente </th>
				<th>Codice Ordine</th>				
				<th>Nome</th>
				<th>Totale</th>
				<th>Stato</th>
				<th>Annulla ordine</th>
			</tr>
		</thead>
		<tbody id="container">

		</tbody>
	</table>
</section>
	<jsp:include page="./footer.jsp" flush="true" />
</body>
</html>