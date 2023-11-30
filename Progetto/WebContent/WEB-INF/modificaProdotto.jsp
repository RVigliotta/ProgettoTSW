<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, model.Utente"%> 
<%	
	Utente user = (Utente) session.getAttribute("Utente");
  	if(user == null)
		response.sendRedirect("login.jsp");
 %>
<jsp:include page="./header.jsp" flush="true" />
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
<script>
function confermaModifica(event) {
    event.preventDefault(); // Impedisce l'invio automatico del modulo

    Swal.fire({
        title: 'Sei sicuro di voler effettuare i cambiamenti?',
        showDenyButton: true,
        confirmButtonText: 'Salva',
        denyButtonText: 'Non Salvare',
    }).then((result) => {
        if (result.isConfirmed) {
            var risposta = result.isConfirmed ? "conferma" : "annulla";
            var prodotto = document.getElementById('chooseProduct').value;
            var nome = document.getElementById('nome').value
            var descrizione = document.getElementById('descrizione').value
            var immagine =  document.getElementById('immagine').value
            var prezzo = document.getElementById('prezzo').value
            var quantita = document.getElementById('quantita').value
            var marca = document.getElementById('marca').value
            var categoria = document.getElementById('categoria').value

            // Esegui la chiamata AJAX per inviare i dati al server
            $.ajax({
                url: '<%=request.getContextPath()%>/EditProductServlet',
                type: 'POST',
                data: {
                    risposta: risposta,
                    scelta: prodotto,
                    nome: nome,
                    descrizione: descrizione ,
                    immagine : immagine,
                    prezzo: prezzo,
                    quantita: quantita,
                    marca : marca,
                    categoria : categoria
                },
                }).done(function (response) {
                    var status = response.status;
                    if (typeof status !== 'undefined') {
                    	  status = status.trim();
                    }
                    else{
                    	Swal.fire('errore','','error');
                    }
                    status = status.replace(/[\u0000-\u001F]+/g, '');
                    
                    if(status == 'Invalid_prodotto'){
                    	Swal.fire('Scegliere un prodotto da modificare','','error')
                    }
                    else if(status =='Invalid_nome'){
                    	Swal.fire('Scegliere un nome!','','error')
              		}else if(status =='Invalid_nome_caratteri_speciali'){
                    	Swal.fire('Scegliere un nome senza caratteri speciali!','','error')
              		}else if(status =='Invalid_descrizione'){
                    	Swal.fire('Descrivere il prodotto!','','error')
                    }else if(status =='Invalid_path'){
                    	Swal.fire('Inserire un path valido!','','error')
                    }else if(status =='Invalid_prezzo'){
                    	Swal.fire('Inserire un prezzo!','','error')
                    }else if(status =='Invalid_quantita'){
                    	Swal.fire('Inserire almeno una quantità!','','error')
                    }else if(status =='Invalid_marca'){
                    	Swal.fire('Scegliere marca prodotto!','','error')
                    }else if(status =='Invalid_categoria'){
                    	Swal.fire('Scegliere categoria!','','error')
                    }else if(status =='success'){
                    	Swal.fire('Prodotto aggiornato correttamente!','','success')
                    	 setTimeout(function() {
							    window.location.assign(response.url);
						}, 2000); // Ritardo di 2 secondi (2000 millisecondi)
                    }
                    else{
                    	Swal.fire('Errore!','Il prodotto potrebbe non essere più presente sul sito','error')
                    }
                })
        } else if (result.isDenied) {
            Swal.fire('Modifiche non salvate', '', 'info');
        }
    });
}
</script>
<body>
<jsp:include page="./Nav.jsp" flush="true"/>
<script src="./Script/dynamicCode.js"></script>
<script>

	document.addEventListener("DOMContentLoaded", dynamicModificaProdotto("<%=request.getContextPath()%>/NameServlet")); 	

</script>
	<section id="new_product">
		<div class="form-wrapper">
			<form onsubmit="confermaModifica(event)" method="POST">
				<h2>Modifica un Prodotto</h2>
				<div class="form-row">
					<label for="scelta">Prodotto: </label>
					<select id="chooseProduct" name="scelta">
						
					</select>
				</div>
				<div class="form-row">
					<label for="nome">Nome: </label>
					<input type="text" name="nome" id="nome">
				</div>
				<div class="form-row">
					<label for="descrizione">Descrizione: </label>
					<textarea id ="descrizione" name="descrizione" ></textarea>
				</div>
				<div class="form-row">
					<label for="immagine">Immagine: </label>
					<input type="text" id="immagine" name="immagine" placeholder="./images/nome_img.formato">
				</div>
				<div class="form-row">
					<label for="prezzo">Prezzo: </label>
					<input type="number" step="0.01" min="0"  id="prezzo"name="prezzo">
				</div>
				<div class="form-row">
					<label for="quantita">Quantità: </label>
					<input type="number" min="0" name="quantita" id ="quantita">
				</div>
				<div class="form-row">
					<label for="marca">Marca: </label>
					<select name="marca" id ="marca">
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
						<select name="categoria" id ="categoria">
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
				<div class="sub-class"> 
					<button type="submit">Modifica</button>
				</div>
			</form>
		</div>
	</section>
<jsp:include page="./footer.jsp" flush="true" />
</body>
</html>