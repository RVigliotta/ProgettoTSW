document.addEventListener("DOMContentLoaded", function () {
    // Controlla se l'utente ha un indirizzo
    var userHasAddress = document.getElementById("indirizzo").innerHTML;
    
    // Se l'utente non ha un indirizzo, mostra l'alert
    if (userHasAddress.trim() === "null") {
        Swal.fire({
            title: "Attenzione!",
            text: "Per poter proseguire devi inserire un indirizzo di spedizione, Puoi farlo dalla tua area personale.",
            icon: "warning",
            showConfirmButton: true
        }).then(function () {
            // Reindirizza alla pagina PersonalAreaServlet con un metodo POST
            var form = document.createElement("form");
            form.method = "POST";
            form.action = "PersonalAreaServlet";
            document.body.appendChild(form);
            form.submit();
        });
    }
});
