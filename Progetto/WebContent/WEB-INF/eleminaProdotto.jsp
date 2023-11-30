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
function confermaEliminazione(event) {
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

	            // Esegui la chiamata AJAX per inviare i dati al server
	            $.ajax({
	                url: '<%=request.getContextPath()%>/DeleteProductServlet',
	                type: 'POST',
	                data: {
	                    risposta: risposta,
	                    scelta: prodotto
	                },
	                }).done(function (response) {
	                    var status = response.status;
	                    status = status.trim();
	                    status = status.replace(/[\u0000-\u001F]+/g, '');
	                    if(status == 'success'){
	                    	Swal.fire('Prodotto eliminato correttamente','','success')
	                    	 location.reload();
	                    }
	                    else if(status =='Invalid_Prodotto'){
	                    	Swal.fire('Scegliere un Prodotto da eliminare!','Riprovare','error')
	                    }
	                    else{
	                    	Swal.fire('Il prodotto è già stato eliminato!','Riaggiornare','error')
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
			<form  method="POST" onsubmit="confermaEliminazione(event)">
				<h2>Elimina un Prodotto</h2>
				<div class="form-row">
					<label for="scelta">Prodotto: </label>
					<select id="chooseProduct" name="scelta" required>
						
					</select>
				</div>
				<div class="sub-class"> 
					<button type="submit">Elimina</button>
				</div>
			</form>
		</div>
	</section>
<jsp:include page="./footer.jsp" flush="true" />
</body>
</html>