<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./header.jsp" flush="true" />
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
		Swal.fire("Congratulazione!", "Password cambiata correttamente!", "success");
	}else if('<%= status %>' == 'failed'){
		Swal.fire("Spiacente!", "Si è verificato un errore","error");
	}else if('<%= status %>' == 'Invalid_email'){
		Swal.fire("Spiacente!", "Inserire indirizzo email", "error");
	}else if('<%= status %>' == 'Invalid_password'){
		Swal.fire("Spiacente!", "Inserire password", "error");
	}else if('<%= status %>' == 'Invalid_password2'){
		Swal.fire("Spiacente!", "Le password non combaciano", "error");
	}
</script>
<body>
	<jsp:include page="./Nav.jsp" flush="true"/>
	<main>
		<section id ="reset-pass">
 			<div id="reset-img"><img src="./images/forgot.jpg" alt=""/></div>
 			<form  action = "ForgotPasswordServlet" method = "POST" class = "reset-form">
 				<h3>Reset password</h3>
 	      		<div class="form-row">
 		      		<label for="email">Email:</label>
 		      		<input type="email" id="email" value="${emailValue}" name = "email" placeholder="E-mail"/>
 	      		</div>
 	      		<div class="form-row">
 		      		<label for="password">Password:</label>
 		      		<input type="password" id="password" name="password" placeholder="Password"/>
 	      		</div>
 	      		<div class="form-row">
 		      		<label for="conf-password">Conferma Password:</label>
 		      		<input type="password" id="conf-password" name="conf-password" placeholder="Password"/>
 	      		</div>
 	      		<div class= "sub-class">
 	      			<button type="submit">Invia</button>
       			</div>
   			</form>
 		</section>
 	</main>
</body>
</body>
</html>