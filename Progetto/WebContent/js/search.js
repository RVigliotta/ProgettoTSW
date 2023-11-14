function searchAndFilter() {
  let input, filter, schede, product;
  input = document.getElementById("search-input");
  filter = input.value.toUpperCase();
  schede = document.getElementById("schedeProdotto");
  product = schede.querySelectorAll(".scheda");

  const selectedCategories = Array.from(document.querySelectorAll('input.cat:checked')).map(input => input.value);
  const selectedMarche = Array.from(document.querySelectorAll('input.mar:checked')).map(input => input.value);

  for (const item of product) {
    let a = item.querySelector(".pname");
    let textValue = a.textContent || a.innerText;
    const prodottoCategoria = item.dataset.categoria;
    const prodottoMarca = item.dataset.marca;

    const nameMatches = textValue.toUpperCase().indexOf(filter) > -1;
    const categoryMatches = selectedCategories.length === 0 || selectedCategories.includes(prodottoCategoria);
    const marcheMatches = selectedMarche.length === 0 || selectedMarche.includes(prodottoMarche);

    if (filter && (selectedCategories.length > 0 || selectedMarche.length > 0)) {
      // Se è presente una ricerca per nome e filtri attivi, considera solo i prodotti che corrispondono a entrambi
      if (nameMatches && categoryMatches && marcheMatches) {
        item.style.display = "";
      } else {
        item.style.display = "none";
      }
    } else if (filter) {
      // Se è presente solo una ricerca per nome, considera solo i prodotti che corrispondono al nome
      if (nameMatches) {
        item.style.display = "";
      } else {
        item.style.display = "none";
      }
    } else if (selectedCategories.length > 0 || selectedMarche.length > 0) {
      // Se ci sono solo filtri attivi, considera solo i prodotti che corrispondono ai filtri
      if (categoryMatches && marcheMatches) {
        item.style.display = "";
      } else {
        item.style.display = "none";
      }
    } else {
      // Se non ci sono né ricerca né filtri attivi, mostra tutti i prodotti
      item.style.display = "";
    }
  }
}