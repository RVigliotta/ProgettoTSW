<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="./header.jsp" flush="true"/>
<%@ page import="java.util.*,model.Indirizzo"%>
<% if(session.getAttribute("Utente")==null)
		response.sendRedirect("login.jsp");
 %>
<% Indirizzo indirizzo = (Indirizzo) session.getAttribute("indirizzo"); %>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
<%String status = (String) request.getAttribute("status");%>
<script type ="text/javascript">
	console.log('<%= status %>');
	if('<%= status %>' == 'Invalid_address'){
		Swal.fire("Spiacente!", "Inserire l'indirizzo", "error");
	} else if('<%= status %>' == 'Invalid_citta'){
		Swal.fire("Spiacente!", "Inserire nome città", "error");
	} else if('<%= status %>' == 'Invalid_provincia'){
		Swal.fire("Spiacente!", "Inserire nome provincia", "error");
	} else if('<%= status %>' == 'Invalid_cap'){
		Swal.fire("Spiacente!", "Inserire CAP valido", "error");
	} else if('<%= status %>' == 'Invalid_nazione'){
		Swal.fire("Spiacente!", "Inserire nazione", "error");
	} else if('<%= status %>' == 'success'){
		Swal.fire("Congratulazione!", "Dati registrati correttamente", "success");
	} else if('<%= status %>' == 'failed'){
		Swal.fire("Siamo spiacenti!", "Dati non registrati , reinserire", "error");
	} else if('<%= status %>' == 'noAddress'){
		Swal.fire("Spiacente!", "Devi inserire un indirizzo per ordinare reinserire", "error");
	}

	
</script>
<body>
	<div class="justaimg">
		<jsp:include page="./Nav.jsp" flush="true"/>
		<main>
			<section id= "address-info">
				<div class="form-wrapper">
					<h2>I tuoi dati</h2>
					<form action="AddressServlet" name="login" method="get">
						<div class="form-row">
							<label for="indirizzo">Indirizzo:</label>		
							<input type="text"   id= "indirizzo" placeholder="<%=indirizzo.getIndirizzo()%>" name="indirizzo">
						</div>
						<div  class="form-row">
							<label for="citta">Città:</label>
							<input type="text" id ="citta" placeholder="<%=indirizzo.getCitta()%>" name="citta"> 
						</div>
						<div  class="form-row">
							<label for="provincia">Provincia:</label>
							<input type="text" id="provincia"placeholder="<%=indirizzo.getProvincia()%>" name="provincia"> 
						</div>
						<div  class="form-row">
							<label for="cap">CAP:</label>
							<input type="text" id="cap" placeholder="<%=indirizzo.getCap()%>" pattern="^[0-9]{5}$"name="cap" placeholder="00000">
						</div>
						<div  class="form-row">
							<label for="nazione">Nazione:</label>
							<select name="nazione">
								<option>-effettua una scelta-</option>
								<option>Italia<option>
							</select>
						</div>
						<div class="sub-class">
							<button type="submit">Invia</button>
						</div>	
					</form>
				</div>
			</section>
		</main>
	</div>
<jsp:include page="./footer.jsp" flush="true"/>
</body>
</html>