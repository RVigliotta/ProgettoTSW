<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.Utente"%>
<%
Utente user = (Utente) session.getAttribute("Utente");
int flag = 0;
if (user == null)
	response.sendRedirect("login.jsp");
else if (user.getId() == 1) {
	flag = 1;
}
%>
<jsp:include page="./header.jsp" flush="true" />
<body>
	<script>
		let id =<%=flag%>;
		if (id == 1) {
			$(document).ready(function() {
				let contenutoHtml = '';
				contenutoHtml += '<li> <a href="aggiungiProdotto.jsp"> <img class=\"adimg\" src="./icons/upload.ico"> Aggiungi Prodotto <p> (ADMIN) </p></a> </li>';
				contenutoHtml += '<li> <a href="modificaProdotto.jsp"> <img class=\"adimg\" src="./icons/edit.ico"> Modifica Prodotto <p> (ADMIN) </p></a> </li>';
				contenutoHtml += '<li> <a href="eliminaProdotto.jsp"> <img class=\"adimg\" src="./icons/delete.ico"> Elimina Prodotto <p> (ADMIN) </p></a> </li>';
				contenutoHtml += '<li> <a href="aggiungiNovita.jsp"> <img class=\"adimg\" src="./icons/news.ico"> Aggiungi Notizie <p> (ADMIN) </p></a> </li>';
				contenutoHtml += '<li> <a href="controllaordini.jsp"> <img class=\"adimg\" src="./icons/logistic.ico"> Controlla Ordini <p> (ADMIN) </p> </a> </li>';
				document.getElementById("admin").innerHTML = contenutoHtml;
			});
		}
	</script>

	<div class="justaimg">
		<jsp:include page="./Nav.jsp" flush="true" />
		<section class="mid">
			<h2>Il mio Account</h2>
			<div class="banner">
				<video autoplay>
    				<source src="./video/banner.mp4" type="video/mp4">
 	 			</video>
			</div>
			<div class="account">
				<ul>
					<li> <a href="datipersonali.jsp">
						<img class="proimg" alt="" src="./icons/profile.ico">
					 	Dati personali 
					 </a> </li>
					<li> <a href="indirizzo.jsp"> 
						<img class="proimg" alt="" src="./icons/address.ico">
						Indirizzo 
					</a></li>
					<li> <a href="carrello.jsp">
						<img class="proimg" alt="" src="./icons/cart.ico">
						Carrello 
						</a> </li>
					<li> <a href="OrdineServlet">
						<img class="proimg" alt="" src="./icons/calendar.ico"> 
						I miei Ordini 
					</a></li>
					<li> <a href="ExitServlet">
						<img class="proimg" alt="" src="./icons/exit.ico">
						Esci 
					</a></li>
				</ul>
				<ul id="admin">

				</ul>
			</div>
		</section>
	</div>
	<jsp:include page="./footer.jsp" flush="true" />
</body>
</html>