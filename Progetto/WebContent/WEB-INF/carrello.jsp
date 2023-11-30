<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ page import="java.util.*,model.Prodotto,model.Carrello"%>
 <% if(session.getAttribute("Utente")==null)
		response.sendRedirect("login.jsp");
 %>
<jsp:include page="./header.jsp" flush="true"/>
<body>
	<jsp:include page="./Nav.jsp" flush="true"/>
	<script src="./Script/carrello.js"></script>
	<script src="./Script/dynamicCode.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", dynamicCart("<%=request.getContextPath()%>/CartServlet?codice=<%=request.getParameter("codice")%>"));
	</script>
	
	<main>
		<section id="container">
			<h2>Carrello</h2>
			<table id="table">
				<thead>
					<tr>
					<th>Elimina</th>
					<th>Immagine</th>
					<th>Prodotto</th>
					<th>Prezzo</th>
					<th>Quantità</th>
					<th>Totale</th>
					</tr>
				</thead>
				<tbody id="dinamico">
					
					
				</tbody>
			</table>
		</section>
		
		<section id="bottom">
				<div id="cassa">
					<h5>TOTALE</h5>
					<div class="totale">
						<h6>Prodotti: </h6>
						<p class="tot">&#8364 totale</p>
					</div>
					<div class="totale">
						<h6>Spedizione: </h6>
						<p>&#8364 10</p>
					</div>
					<hr>
					<div class="totale">
					<h6>Totale: </h6>
					
						<p class="totCumul">&#8364 totale</p>
					</div>
					<button onclick="checkout()"> Procedi al Pagamento</button>
				</div>
		
		</section>
	</main>
<jsp:include page="./footer.jsp" flush="true"/>	
</body>
</html>