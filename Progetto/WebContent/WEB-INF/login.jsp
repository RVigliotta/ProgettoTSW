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
	if('<%= status %>' == 'failed'){
		Swal.fire("Spiacente!", "Email o Password errati", "error");
	}
</script>
<body>
	<jsp:include page="./Nav.jsp" flush="true"/>	
	<main>
		<section id="login">
			<div id="login-img"><img src="./images/anya.jpg" alt=""/></div>
				<h3>Accedi al tuo account</h3>
				<form action="Login" name="login" method="POST" class="login-form" >
					<input type="email" name="email" placeholder="E-mail">
					<input type="password" name="password" placeholder="Password">
					<button type="submit">Invia</button>
				</form>
				<div class="links">
					<a href="richiestapassword.jsp">Hai dimenticato la password?</a>
					<p>Non sei iscritto? <a href="signup.jsp">Iscriviti!</a></p>	
				</div>
		</section>
	</main>
</body>
</html>