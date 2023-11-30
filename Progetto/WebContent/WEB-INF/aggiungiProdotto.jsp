 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, model.Utente"%> 
<%	
	Utente user = (Utente) session.getAttribute("Utente");
  	if(user == null)
		response.sendRedirect("login.jsp");
 %>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
<jsp:include page="./header.jsp" flush="true"/>
<script>
    document.addEventListener("DOMContentLoaded", function() {
      var inputContainer = document.querySelector(".file-row");
      var fileInput = inputContainer.querySelector("input[type=file]");

      fileInput.addEventListener("change", function(event) {
        var file = event.target.files[0];

        if (file) {
          var fileName = file.name;
          var fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

          if (!(fileExt === ".jpg" || fileExt === ".png")) {
            Swal.fire("Errore!", "Inserire un formato valido (.jpg, .png)", "error");
            fileInput.value = "";
          }
        }
      });

      var form = document.querySelector("form");
      form.addEventListener("submit", function(event) {
        var codiceInput = document.querySelector("input[name=codice]");
        var nomeInput = document.querySelector("input[name=nome]");
        var descrizioneInput = document.querySelector("textarea[name=descrizione]");
        var prezzoInput = document.querySelector("input[name=prezzo]");
        var quantitaInput = document.querySelector("input[name=quantita]");
        var marcaInput = document.querySelector("select[name=marca]");
        var categoriaInput = document.querySelector("select[name=categoria]");
        var immagineInput = document.querySelector("input[name=immagine]");

        if ((codiceInput.value === "") || (codiceInput.value === "0")) {
          event.preventDefault();
          Swal.fire("Errore!", "Inserire un codice valido", "error");
        }

        if (nomeInput.value === "") {
          event.preventDefault();
          Swal.fire("Errore!", "Inserire un nome", "error");
        }

        if (descrizioneInput.value === "") {
          event.preventDefault();
          Swal.fire("Errore!", "Inserire una descrizione", "error");
        }

        if (prezzoInput.value === "" || prezzoInput.value === "0") {
          event.preventDefault();
          Swal.fire("Errore!", "Il prezzo deve essere maggiore di 0", "error");
        }

        if (quantitaInput.value === "") {
          event.preventDefault();
          Swal.fire("Errore!", "Inserire una quantità", "error");
        }

        if (marcaInput.value === "-scegliere marca-") {
          event.preventDefault();
          Swal.fire("Errore!", "Selezionare una marca", "error");
        }

        if (categoriaInput.value === "-scegliere categoria-") {
          event.preventDefault();
          Swal.fire("Errore!", "Selezionare una categoria", "error");
        }

        if (!/^\.\/images\/[a-zA-Z0-9_]+\.(jpg|png)$/.test(immagineInput.value)) {
          event.preventDefault();
          Swal.fire("Errore!", "Il percorso dell'immagine non è valido", "error");
        }
      });
    });
  </script>
<body>
	<div class="justaimg">
	<jsp:include page="./Nav.jsp" flush="true"/>
	<section id="new_product">
		<div class="form-wrapper">
			<form enctype ="multipart/form-data" action="AddProductServlet" method="POST">
				<h2>Aggiungi un Prodotto</h2>
				<div class="form-row">
					<label for="codice">CODICE: </label>
					<input type="text" name="codice" placeholder="0">
				</div>
				<div class="form-row">
					<label for="nome">Nome: </label>
					<input type="text" name="nome">
				</div>
				<div class="form-row">
					<label for="descrizione">Descrizione: </label>
					<textarea id ="descrizione" name="descrizione"></textarea>
				</div>
				<div class="form-row">
					<label for="immagine">Immagine: </label>
					<input type="text" name="immagine" placeholder="./images/nome_img.formato">
				</div>
				<div class="file-row">
					<label for="file">File: </label>
					<input type="file" class = "input_container" name="file" id="file">
				</div>
				<div class="form-row">
					<label for="prezzo">Prezzo: </label>
					<input type="number" step="0.01" min="1" name="prezzo" >
				</div>
				<div class="form-row">
					<label for="quantita">Quantità: </label>
					<input type="number" min="0" name="quantita">
				</div>
				<div class="form-row">
					<label for="marca">Marca: </label>
					<select name="marca" required>
						<option>-scegliere marca-</option>
						<option>Adidas</option>
						<option>Armani</option>
						<option>Balenciaga</option>
						<option>Burberry</option>
						<option>Calvin Klein</option>
						<option>Chanel</option>
						<option>Diesel</option>
						<option>Fendi</option>
						<option>Gucci</option>
						<option>Hugo Boss</option>
						<option>Lacoste</option>
						<option>Michael Kors</option>
						<option>Prada</option>
						<option>Ralph Lauren</option>
						<option>Tommy Hilfiger</option>
						<option>Valentino</option>
						<option>Versace</option>
						<option>Zara</option>					
					</select>
				</div>
				<div class="form-row">
					<label for="categoria">Categoria: </label>
						<select name="categoria">
							<option>-scegliere categoria-</option>
							<option>Bermuda</option>
							<option>Cappelli</option>
							<option>Cappotti</option>
							<option>Felpe</option>
							<option>Maglie</option>
							<option>Pantaloni</option>
							<option>Scarpe</option>
							<option>T-shirt</option>
							<option>Giacche</option>			
					</select>
				</div>
				<div class= "sub-class">
	      			<button type="submit">Aggiungi</button>
      			</div>
			</form>
		</div>
	</section>
	</div>
<jsp:include page="./footer.jsp" flush="true" />
</body>
</html>