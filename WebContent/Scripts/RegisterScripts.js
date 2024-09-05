var error = "<%= request.getParameter('error') %>";

  if (error === "username_exists") {
	    Swal.fire({
	        title: 'Errore',
	        text: 'Username già esistente!',
	        icon: 'error',
	        confirmButtonText: 'OK'
	    });
	    
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