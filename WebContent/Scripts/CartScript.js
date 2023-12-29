function updateTotalPrice(input) {
    	var quantity = parseFloat(input.value);
    	var price = parseFloat(input.parentNode.previousElementSibling.innerHTML);
    	var totalPriceElement = document.getElementById('totalPrice');
    	var totalPrice = parseFloat(totalPriceElement.innerHTML);

    // Calcola il prezzo totale senza considerare la quantità
    	var previousPrice = parseFloat(input.getAttribute('data-previous-price')) || 0;
    	totalPrice -= previousPrice;

	    var newPrice = price * quantity;
    	totalPrice += newPrice;

    // Aggiorna l'attributo 'data-previous-price' con il nuovo prezzo
   		input.setAttribute('data-previous-price', newPrice);
    	totalPriceElement.innerHTML = totalPrice.toFixed(2);
}
