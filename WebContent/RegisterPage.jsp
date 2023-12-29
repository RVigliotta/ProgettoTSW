<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<meta charset="ISO-8859-1">
<title>Registrati</title>
</head>
<body>
	<form action="RegisterServlet" method="POST" onsubmit="return validateRegistration()">
		<label for="nome">Nome:</label>
		<input type="text" id="nome" name="nome"><br><br>
		
		<label for="cognome">Cognome:</label>
		<input type="text" id="cognome" name="cognome"><br><br>
		
		<label for="username">Username:</label>
		<input type="text" id="username" name="username"><br><br>

	    <label for="password">Password:</label>
		<input type="password" id="password" name="password" required><br><br>
		
		<label for="password2"> Reinserisci Password:</label>
		<input type="password" id="password2" name="password2" required><br><br>
		
		<label for="email">Email</label>
		<input type="text" id="email" name="email"><br><br>
		
		<label for="adress">Indirizzo di consegna</label>
		<input type="text" id="adress" name="adress"><br><br>
		
		<label for="pag">numero di carta</label>
		<input type="text" id="pag" name="pag"><br><br>
		
		<input type="submit" value="Registrati">	
		 	
  </form>
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