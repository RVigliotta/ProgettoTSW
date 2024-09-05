<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%String errorMessage = (String) request.getAttribute("errorMessage");%>
<%@ include file="header.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="./Scripts/LoginScreenScript.js"></script>
<%@ include file="meta.html"%>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body class="login-page">
	<form action="Login" method="POST" class="login-form">
		<label for="username" class="login-label">Username:</label>
		<input type="text" id="username" name="username" required class="login-input"><br><br>
		<label for="password" class="login-label">Password:</label>
		<input type="password" id="password" name="password" required class="login-input"><br><br>
		<input type="submit" value="login" class="login-button">
	</form>

	<p class="login-link">
		Non sei registrato? Registrati <a href="RegisterPage.jsp">qui</a>
	</p>
	<script>
		// Esegui lo script JavaScript per mostrare l'alert con SweetAlert2
	<%String errorScript = (String) request.getAttribute("errorScript");%>	
	<%if (errorScript != null) {%>		
	<%=errorScript%>
	<%}%>
		
	</script>
	<%@ include file="footer.jsp"%>
</body>
</html>