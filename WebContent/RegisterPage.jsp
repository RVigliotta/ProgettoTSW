<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<%@ include file="meta.html"%>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Registrati</title>
</head>
<body class="Register-page">
	<div class="Register-div">
	<form action="RegisterServlet" method="POST" onsubmit="return validateRegistration()">
		<label for="nome" class="Register-label">Nome:</label>
		<input type="text" id="nome" name="nome" class="Register-input"><br><br>
		
		<label for="cognome" class="Register-label">Cognome:</label>
		<input type="text" id="cognome" name="cognome" class="Register-input"><br><br>
		
		<label for="username" class="Register-label">Username:</label>
		<input type="text" id="username" name="username" class="Register-input"><br><br>

	    <label for="password" class="Register-label">Password:</label>
		<input type="password" id="password" name="password" required class="Register-input"><br><br>
		
		<label for="password2" class="Register-label"> Reinserisci Password:</label>
		<input type="password" id="password2" name="password2" required class="Register-input"><br><br>
		
		<label for="email" class="Register-label">Email:</label>
		<input type="text" id="email" name="email" class="Register-input"><br><br>
		
		<label for="adress" class="Register-label">Indirizzo di consegna:</label>
		<input type="text" id="adress" name="adress" class="Register-input"><br><br>
		
		<label for="pag" class="Register-label">Numero di carta:</label>
		<input type="text" id="pag" name="pag" class="Register-input"><br><br>
		
		<input type="submit" value="Registrati" class="Register-button">	
		 	
    </form>
    </div>
    <%@ include file="footer.jsp"%>
  <script>
  var error = "<%= request.getParameter("error") %>";

  if (error === "username_exists") {
	    Swal.fire({
	        title: 'Errore',
	        text: 'Username già esistente!',
	        icon: 'error',
	        confirmButtonText: 'OK'
	    });
	    //aggiungi controllo indirizzo
	} else if (error === "email_exists") {
	    Swal.fire({
	        title: 'Errore',
	        text: 'Email già esistente!',
	        icon: 'error',
	        confirmButtonText: 'OK'
	    });
	}
  function validateRegistration() {
    
    	var emailInput = document.getElementById("email").value;//validazione email
        if (emailInput.indexOf('@') === -1) {
        	Swal.fire({
    	        title: 'Errore',
    	        text: 'Email non valida!',
    	        icon: 'error',
    	        confirmButtonText: 'OK'
    	    });
            return false; // Blocca l'invio del form se la validazione fallisce
        }
        var domain = emailInput.split('@')[1]; // Ottieni il dominio dell'email dopo la '@'

        if (domain.endsWith('.com') || domain.endsWith('.it')) {
            // Consente l'invio del form se la validazione è passata
        } else {
        	Swal.fire({
    	        title: 'Errore',
    	        text: 'Email non valida!',
    	        icon: 'error',
    	        confirmButtonText: 'OK'
    	    });
            return false; // Blocca l'invio del form se la validazione fallisce
        }
        var password1 = document.getElementById("password").value; // Controllo password
        var password2 = document.getElementById("password2").value;

        if (password1 === password2) {
            // Le password sono uguali
            return true; // Consente l'invio del form
        } else {
            // Le password sono diverse
            Swal.fire({
	        title: 'Errore',
	        text: 'Le password non corrispondono',
	        icon: 'error',
	        confirmButtonText: 'OK'
	    });
            return false; // Blocca l'invio del form
        }
    }
</script>
</body>
</html>