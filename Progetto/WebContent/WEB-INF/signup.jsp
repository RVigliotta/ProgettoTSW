<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="./header.jsp" flush="true"/>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
<%String status = (String) request.getAttribute("status");%>
<script type ="text/javascript">
	if('<%= status %>' == 'success'){
		Swal.fire("Congratulazione!", "Account creato correttamente!", "success");
	}else if('<%= status %>' == 'duplicato'){
		Swal.fire("Spiacente!", "Email già presente nel sito","error");
	}else if('<%= status %>' == 'Invalid_email'){
		Swal.fire("Spiacente!", "Inserire indirizzo email valido", "error");
	}
</script>
<body>
	<jsp:include page="./Nav.jsp" flush="true"/>
	<main>
		<section id ="signup">
 			<div id="signup-img"><img src="./images/anya-signup.png" alt=""/></div>
 			<form  action = "SignupServlet" method = "POST" class = "signup-form">
 				<h3>Iscrizione</h3>
 				<div class="form-row">
 		      		<label for="nome">Nome:</label>
 		      		<input type="text" id="nome" name = "nome"required placeholder="Nome"/>
 	      		</div>
 	      		<div class="form-row">
 		      		<label for="cognome">Cognome:</label>
 		      		<input type="text" id="cognome" name="cognome" required placeholder="Cognome"/>
 	      		</div>
 	      		<div class="form-row">
 		      		<label for="email">Email:</label>
 		      		<input type="email" id="email"  name = "email"required placeholder="E-mail"/>
 	      		</div>
 	      		<div class="form-row">
 		      		<label for="password">Password:</label>
 		      		<input type="password" id="password" name="password" required placeholder="Password"/>
 	      		</div>
 	      		<div class= "sub-class">
 	      			<button type="submit">Invia</button>
       			</div>
   			</form>
 		</section>
 	</main>
</body>
</html>