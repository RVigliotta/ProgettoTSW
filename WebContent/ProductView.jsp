<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
	ProductBean existingProduct= (ProductBean) request.getAttribute("existingProduct");
	
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProductBean"%>

<head>
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<%@ include file="meta.html"%>
	<%@ include file="header.jsp"%>
	<title>Catalogo</title>
</head>

<body>
	<%@ include file="nav.jsp"%>
	<div class="ProductView">
	<h2 class="ProductView-h2">Prodotti</h2>
	<table border="1" class="ProductView-table">
		<tr>
			<th>Codice</th>
			<th>Nome <a href="product?sort=name">Ordina</a></th>
			<th>Descrizione</th>
			<th>Categoria <a href="product?sort=CategoriaID">Ordina</a></th>
			<th>Immagine</th>
			<th></th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
					String imagePath = "./Images/products/" + bean.getImage(); //?
				
		%>
		<tr>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>
			<td><%=bean.getCategoriaID()%></td>
			<td><img src="<%=imagePath%>" width="100" class="ProductView-img"></td>
			<td>
				<form action="product" method="post">
           			<input type="hidden" name="driver" value="drivermanager">
            		<input type="hidden" name="action" value="pdate">
            		<input type="hidden" name="id" value="<%=bean.getCode()%>">
           			<input type="submit" value="Aggiorna" class="ProductView-top">
        		</form>
        		<br>
        		<form action="product" method="post">
           			<input type="hidden" name="driver" value="drivermanager">
            		<input type="hidden" name="action" value="delete">
           			<input type="hidden" name="id" value="<%=bean.getCode()%>">
           			<input type="submit" value="Rimuovi" class="ProductView-top">
       			</form>
        	<br>
        		<form action="product" method="post">
           			<input type="hidden" name="driver" value="drivermanager">
            		<input type="hidden" name="action" value="details">
            		<input type="hidden" name="id" value="<%=bean.getCode()%>">
           			<input type="submit" value="Dettagli" class="ProductView-top">
        		</form>
        	
        		
   			</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessun prodotto disponibile</td>
		</tr>
		<%
			}
		%>
	</table>
	
	
	<%
		if (product != null) {
	%>
	<h2 class="ProductView-h2">Dettagli</h2>
	<table border="1" class="ProductView-table">
		<tr>
			<th>Codice</th>
			<th>Nome</th>
			<th>immagine</th>
			<th>Descrizione</th>
			<th>Prezzo</th>
			<th>Quantità</th>
			<th>CategoriaID</th>
		</tr>
		<tr>
			<td><%=product.getCode()%></td>
			<td><%=product.getName()%></td>
			<td><img src="./Images/products/<%=product.getImage()%>" width="100" class="ProductView-img"></td>
			<td><%=product.getDescription()%></td>
			<td><%= String.format("%.2f", product.getPrice()) %></td>
			<td><%=product.getQuantity()%></td>
			<td><%=product.getCategoriaID()%></td>
		</tr>
	</table>
	<%
		}
	%><% if (request.getAttribute("existingProduct") != null) { %>
    <h2 class="ProductView-h2">Aggiornamento Prodotto</h2>
    <form action="product" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%=existingProduct.getCode()%>">
        
        <label for="name">Nome:</label><br> 
        <input name="name" type="text" maxlength="20" required placeholder="Inserisci nome" value="<%=existingProduct.getName()%>"><br> 

        <label for="description">Descrizione:</label><br>
		<textarea name="description" maxlength="100" rows="3" required placeholder="inserisci descrizione"><%=existingProduct.getDescription() %></textarea><br>
		
		<label for="price">Prezzo:</label><br> 
		<input name="price" type="number"  step="0.01" min="0.00" value="<%=existingProduct.getPrice() %>" required><br>

		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="<%=existingProduct.getQuantity() %>" required><br>
		
		<label for="CategoriaID">CategoriaID:</label><br> 
		<input name="CategoriaID" type="number" min="1" value="<%=existingProduct.getCategoriaID() %>" required><br>
		
		<label for="id">CodiceProdotto:</label><br> 
		<input name="id" type="number" min="1" value="1" required><br>
		
		<label for="image">Immagine:</label><br>
  		<input type="file" name="image" accept="image/*"><br>
        
        <input type="submit" value="Aggiorna" class="ProductView-down"><input type="reset" value="Reset" class="ProductView-down"><input type="submit" name="action "value="Annulla" class="ProductView-down"> 
    </form>
    
<% } else { %>
	<h2 class="ProductView-h2">Inserimento</h2>
	<form action="product" method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Nome:</label><br> 
		<input name="name" type="text" maxlength="20" required placeholder="Inserisci nome"><br> 
		
		<label for="description">Descrizione:</label><br>
		<textarea name="description" maxlength="100" rows="3" required placeholder="inserisci descrizione"></textarea><br>
		
		<label for="price">Prezzo:</label><br> 
		<input name="price" type="number"  step="0.01" min="0.00" value="0" required><br>

		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>
		
		<label for="CategoriaID">CategoriaID:</label><br> 
		<input name="CategoriaID" type="number" min="1" value="0" required><br>
		
		<label for="id">CodiceProdotto:</label><br> 
		<input name="id" type="number" min="1" value="1" required><br>
		
		
		<label for="image">Immagine:</label><br>
  		<input type="file" name="image" accept="image/*"><br>
  
		<input type="submit" value="Aggiungi" class="ProductView-down"><input type="reset" value="Reset" class="ProductView-down"> 
	</form>
	<% } %>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>