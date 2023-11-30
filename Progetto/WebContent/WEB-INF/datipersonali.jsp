<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.Utente" %>    
 <% if(session.getAttribute("Utente")==null)
		response.sendRedirect("login.jsp");
 %>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
<%String status = (String) request.getAttribute("status");%>
<script type ="text/javascript">
	if('<%= status %>' == 'Invalid_nome')
		Swal.fire("Spiacente!", "Inserire nome ", "error");
	else if('<%= status %>' == 'Invalid_cognome')
		Swal.fire("Spiacente!", "Inserire cognome", "error");
	else if('<%= status %>' == 'Invalid_email')
		Swal.fire("Spiacente!", "Inserire email", "error");
	else if('<%= status %>' == 'Invalid_password')
		Swal.fire("Spiacente!", "Inserire password", "error");
	else if('<%= status %>' == 'Invalid_password2')
		Swal.fire("Spiacente!", "Le password non combaciano", "error");
	else if('<%= status %>' == 'success')
		Swal.fire("Congratulazione!", "Dati registrati correttamente", "success");
	else if('<%= status %>' == 'failed')
		Swal.fire("Siamo spiacenti!", "Dati non registrati , reinserire", "error");
	
</script>
<jsp:include page="./header.jsp" flush="true"/>
<body>
	<div class="justaimg">
	<jsp:include page="./Nav.jsp" flush="true"/>
	<% Utente user = (Utente) session.getAttribute("Utente");%>
	<main>
      <section id="personal-info">
      		<div class="form-wrapper">
      			<form action="DatiPersonaliServlet" method="POST">
      			<h2>Informazioni personali</h2>
	   			<div class="form-row">
		      		<label for="nome">Nome:</label>
		      		<input type="text" id="nome" name="nome"  placeholder="<%=user.getNome()%>"/>
	      		</div>
	      		<div class="form-row">
		      		<label for="cognome">Cognome:</label>
		      		<input type="text" id="cognome" name="cognome"  placeholder="<%=user.getCognome()%>"/>
	      		</div>
	      		<div class="form-row">
		      		<label for="email">Email:</label>
		      		<input type="email" id="email"name="email"  placeholder="<%=user.getEmail()%>"/>
	      		</div>
	      		<div class="form-row">
		      		<label for="password">Password:</label>
		      		<input type="password" id="password" name="password1" />
	      		</div>
	      		<div class="form-row">
		      		<label for="conferma-pass">Conferma Password:</label>
		      		<input type="password" id="conferma-pass" name="password2" />
	      		</div>
	      		<div class= "sub-class">
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