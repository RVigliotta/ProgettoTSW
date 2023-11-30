<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.util.*,model.Prodotto" %>
<jsp:include page="./header.jsp" flush="true"/>

<body>
	<jsp:include page="./Nav.jsp" flush="true"/>

	<% Prodotto p = (Prodotto) request.getAttribute("prodotto");%>
	<script src="./Script/dynamicCode.js"></script>
	<script>
		var disponibilita = '';
		<%	if(p.getQuantita() == 0){%>
				disponibilita = "Esaurito";
		<%	}else if(p.getQuantita() <= 10){%>
				disponibilita = "Quasi Esaurito";
		<%	}else{%>
			disponibilita = "Disponibile";
		<%}%>
		
		$(document).ready(function(){
			document.getElementById("disponibilita").innerHTML = disponibilita ;
			if(disponibilita == "Esaurito")
				document.getElementById("disponibilita").style.color = 'red' ;
			else if(disponibilita == "Quasi Esaurito")
				document.getElementById("disponibilita").style.color = 'orange';
			else
				document.getElementById("disponibilita").style.color = 'green';
		});
		
		document.addEventListener("DOMContentLoaded", dynamicConsigliati("<%=request.getContextPath()%>/ConsigliatiServlet?categoria=<%=p.getCategoria()%>"));
	</script>
	<main>
	
		<section id="prodotto">
			<div class = "product_wrapper">
				<div class="prod_img">
					<img src="<%=p.getImg()%>" alt=""/>
				</div>
				<div class="specs">
					<h3><%=p.getNome()%></h3>
					<h4>Prezzo: &#8364 <%=p.getPrezzo()%></h4>
					<p>Marca: <%=p.getMarca()%><p>
					<p>Categoria: <%=p.getCategoria()%></p>
					<h3 id="disponibilita"></h3>
					<a onclick="addCart(<%=p.getQuantita()%>, '<%=p.getCodice()%>')"> Aggiungi al Carrello</a>
				</div>
			</div>
			<div class = "descrizione">
				<div><h4><span>Descrizione</span></h4></div>
				<p><%=p.getDescrizione()%></p>
			</div>
		</section>
		
		<section id="bottom">
			<h4>Potrebbero interessarti anche...</h4>
			<div id="consigliati">
			
			</div>
		</section>
		
	</main>
<jsp:include page="./footer.jsp" flush="true"/>
</body>
</html>