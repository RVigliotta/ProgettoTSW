<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat,model.OrdiniList,model.Ordine,model.SingoloOrdine, model.Utente" %>    
<% 
	Utente user = (Utente) session.getAttribute("Utente");
 	if(user == null)
		response.sendRedirect("login.jsp");
%>
<jsp:include page="./header.jsp" flush="true"/>
<script>
	<% 
 		OrdiniList ordiniList = (OrdiniList) session.getAttribute("ordini");
 		ArrayList<Ordine> ordini = (ArrayList<Ordine>) ordiniList.getOrdiniList();
 		Collections.reverse(ordini);
 		String stato = "Annullato";
 			
 		String contenutoHtml = "";
 		for(Ordine o : ordini){
 			if(o.getUserId() == user.getId()){
 				contenutoHtml += "<div class=\"ordine\">";
 				if (o.getStato() == 1) stato = "Completato";
 				contenutoHtml += "<h3> ID: " + o.getId() + " - Data: " +  o.getData() + " (" + stato +") </h3>";
 				for(SingoloOrdine os : o.getSingoli()){
 					contenutoHtml += "<div class=\"product\">";
 					contenutoHtml += "<img class=\"orderImg\" src=\""+ os.getProdottoImg() +"\">";
 					contenutoHtml += "<ul class=\"info\">";	
 					contenutoHtml += "<li> Nome: " + os.getProdottoNome() + " x" + os.getQuantita() +"</li>";
 					contenutoHtml += "<li> Totale Prodotti: &#8364 " + String.format("%.2f", os.getTotParziale()) + "</li>";
 					contenutoHtml += "</ul> </div>";
 				}
 				contenutoHtml += "<h4> Totale: &#8364 " + String.format("%.2f", o.getTotale()) + "</li>";
 				contenutoHtml += "</div>";	
 			}
 		}
	 %> 

	const content = '<%=contenutoHtml.replace("'", "\\'").replace("\n", "\\n")%>';
	$(document).ready(function(){
		document.getElementById("container").innerHTML = content;
	});
</script>

<body>
<jsp:include page="./Nav.jsp" flush="true"/>
	<h2>I miei ordini</h2>	
	<section id="container">
	
	
	</section>	
<jsp:include page="./footer.jsp" flush="true"/>
</body>
</html>