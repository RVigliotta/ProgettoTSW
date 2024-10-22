<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Collection"%>
<%@ page import="javax.sql.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<%@ include file="meta.html"%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
	<div class="main-content">
		<%@ include file="nav.jsp"%>

		<div class="slider-container">
			<div class="slide">
				<h1>"Lo stile e' la risposta a tutto. Qualunque cosa sia la domanda, l'abbigliamento giusto e' la soluzione." <br> <br>- Gianni Versace</h1>
				<img src="<%=request.getContextPath()%>/Images/Slider-1.jpg" alt="Prima immagine">
			</div>
			<div class="slide">
				<h1>Dall'eleganza senza sforzo alla moda casual, trova il tuo look perfetto con la nostra collezione di abbigliamento maschile</h1>
				<img src="<%=request.getContextPath()%>/Images/Slider-2.jpg" alt="Seconda immagine">
			</div>
			<div class="slide">
				<h1>Esplora il tuo stile unico con le nostre ultime tendenze uomo</h1>
				<img src="<%=request.getContextPath()%>/Images/Slider-3.jpg" alt="Terza immagine">
			</div>
			<div class="buttons">
				<button class="prev" onclick="prevSlide()">&nbsp;&lt;&nbsp;</button>
				<button class="next" onclick="nextSlide()">&nbsp;&gt;&nbsp;</button>
			</div>
		</div>

		<div class="flexbox-container">
			<div class="flexbox-container-column">
				<div class="flexbox-item flexbox-item-1">
					<img src="<%=request.getContextPath()%>/Images/box1.jpg" alt="box1">
				</div>
				<div class="flexbox-item flexbox-item-2">
					<img src="<%=request.getContextPath()%>/Images/box2.jpg" alt="box2">
				</div>
			</div>
			<div class="flexbox-item flexbox-item-3">
				<img src="<%=request.getContextPath()%>/Images/box3.jpg" alt="box3">
			</div>
		</div>

		<div class=prodotti align="center">
			<%		IProductDao productDao = null;
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	productDao = new ProductDaoDataSource(ds);
	CartDao cd = new CartDaoDataSource(ds);

	Collection<ProductBean> products = productDao.doRetrieveProducts(); %>

			<h2>Novita' Del Momento</h2>

			<div class="product-container" id="product-container">
				<% if (products != null && !products.isEmpty()) { %>
				<% for (ProductBean bean : products) {%>
				<div class="product-item">
					<img
						src="<%=request.getContextPath()%>/Images/products/<%= bean.getImage() %>"
						alt="Immagine" width="100" />
					<p><%= bean.getName() %></p>
					<p>&euro;&nbsp;<%= String.format("%.2f", bean.getPrice()) %></p>

					<% if (bean.getQuantity() > 0) { %>
					<form action="CartServlet" method="POST" class="IndexProduct">
						<input type="hidden" name="action" value="addC"> 
						<input type="hidden" name="id" id="code" value="<%=bean.getCode()%>"> 
						<input type="hidden" name="quantity" id="quantity_<%=bean.getCode()%>" value="1"> 
							<% 
								
							if (session.getAttribute("username") != null){
								int uid = (int) session.getAttribute("userId");
								int f = cd.quantiSpecifica(uid,bean.getCode());										//if f < bean.getQuantity(){//
								
								if(f<bean.getQuantity()){	%>
							<input type="submit"
							class="press"
							value="Aggiungi al carrello">
							
							<% }else{ %>
						<p><b>Quantità nel carrello massima raggiunta</b></p>	
							<% } %>
							
							<% }else{ %>
						<p><b>Accedi per aggiungere al carrello</b></p>	
							<% } %>
					</form>
					<p>Disponibili in magazzino: <%=bean.getQuantity() %></p>
					<% } else { %>
					<p class="esaurito">Prodotto esaurito</p>
					<% } %>
				</div>
				<% } %>
				<% } else { %>
				<p>Nessun prodotto disponibile</p>
				<% } %>
			</div>
		</div>
		</div>
		<%@ include file="footer.jsp"%>
		
				
		<script>	
	
		const boxes = document.querySelectorAll('.press');
		boxes.forEach(box => {
  box.addEventListener('click', function al(){
		
		 Swal.fire({ 
         title: 'Aggiunto',
         text: 'Prodotto aggiunto al carrello !', 
         icon: 'success', 
         confirmButtonText: 'OK'
         }).then((result) => {
             if (result['isConfirmed']){
             	location.reload();
               }
             });
	
	});
});
</script>
		
	
</body>
</html>