<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,model.ProductBean, model.Order, model.Cart, model.User"%>


<%ProductBean product = (ProductBean) request.getAttribute("product");
Cart cart = (Cart) request.getSession().getAttribute("cart");  
User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="./Scripts/OrderScripts.js"></script>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<%@ include file="meta.html"%>
<title>Checkout</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div class="OrderView">
		<h2>Procedi all'ordine</h2>
		<table>
			<tr>
				<th>Immagine</th>
				<th>Nome</th>
				<th>Prezzo</th>
				<th>Quantita'</th>

			</tr>
			<% List<ProductBean> prodOrder = cart.getProducts(); 
           for (ProductBean beanOrder: prodOrder) {
            %>

			<tr>
				<td><img src="./Images/products/<%= beanOrder.getImage() %>" alt="Immagine" width="100" /></td>
				<td><%=beanOrder.getName()%></td>
				<td><%= String.format(Locale.US, "%.2f", beanOrder.getPrice()) %></td>
				<td><%=beanOrder.getQuantity()%></td>
			</tr>
			<% } %>

			<tr>
				<td colspan="3" class="OrderView-color2"><strong>Prezzo Totale</strong></td>
				<td class="OrderView-color2"><span id="totalPrice"> <% double totalPrice = 0;
                for (ProductBean beancart : prodOrder) {
                    double quantity = beancart.getQuantity();
                    double price = beancart.getPrice();
                    totalPrice += price * quantity;
                    cart.setTotalPrice(totalPrice);
                   }
                   out.println("€" + String.format(Locale.US, "%.2f", totalPrice));
                %>
				</span></td>
			</tr>
			<tr>
				<th>Conferma i dati di spedizione</th>
				<td colspan="3" class="OrderView-color"><span id="indirizzo"> <%= user.getIndirizzo() %>
				</span></td>
			</tr>
		</table>
		<form action="OrderServlet" method="POST">
			<input type="hidden" name="action" value="Acquista">
			<input type="submit" value="Acquista" class="OrderView-down">
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>