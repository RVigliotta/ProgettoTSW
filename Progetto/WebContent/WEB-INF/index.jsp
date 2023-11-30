<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Utente, model.Prodotto"%> 

<jsp:include page="./header.jsp" flush="true"/>
<body>
<jsp:include page="./Nav.jsp" flush="true"/>
<script src="./Script/dynamicCode.js"></script>
<script src="./Script/index.js"></script>
<script>
		document.addEventListener("DOMContentLoaded", dynamicIndex("<%=request.getContextPath()%>/IndexServlet"));
</script>
<main>
	<section class="banner">
	<div class="slider">
			<div class="content" id="slide1">
				<div class="textbox">
					<h2>Gucci t-shirt</h2>
					<p>Lascia che il lusso abbracci il tuo stile con la nostra T-shirt Gucci Elegance. Realizzata con tessuti pregiati e arricchita dallo stile iconico di Gucci, questa t-shirt è più di un capo d'abbigliamento.<p>
					<a href="ProductServlet?codice=10000000000000001"> Acquista ora</a>
				</div>
				<div class="imgbox">
					<img src="./images/db1.jpg" alt="" class="db">
				</div>
			</div>
			 <div class="content" id="slide2">
				<div class="textbox">
					<h2>Pantaloni Ralph Lauren</h2>
					<p> Eleva il tuo stile quotidiano con i Pantaloni Ralph Lauren Signature. Realizzati con una fusione impeccabile di comfort e classe, questi pantaloni riflettono l'eleganza senza sforzo che è la firma dello stile Ralph Lauren.<p>
					<a href="ProductServlet?codice=10000000000000006"> Acquista ora</a>
				</div>
				<div class="imgbox">
					<img src="./images/op1.jpg" alt="" class="db">
				</div>
			</div>
			<div class="content" id="slide3">
				<div class="textbox">
					<h2>Giacca Zara</h2>
					<p> Aggiungi un tocco di raffinatezza al tuo guardaroba con la Giacca Zara Chic. Questo capo di alta moda cattura l'essenza dell'eleganza moderna, con un design sofisticato e dettagli curati.<p>
					<a href="ProductServlet?codice=10000000000000004"> Acquista ora</a>
				</div>
				<div class="imgbox">
					<img src="./images/naruto1.jpg" alt="" class="db">
				</div>
			</div>
		</div>
		<div class="sliderNav">
			<a href="#slide1" onclick="scrollSenzaOffset(event)"></a>
			<a href="#slide2" onclick="scrollSenzaOffset(event)"></a>
			<a href="#slide3" onclick="scrollSenzaOffset(event)"></a>
		</div>
	</section>
	
	
	<section id="prodotti">
		<h2> Ultime Uscite </h2>
		<div id="schedeProdotto">
			
		</div>
	</section>
</main>	
	
<jsp:include page="./footer.jsp" flush="true"/>
</body>
</html>>