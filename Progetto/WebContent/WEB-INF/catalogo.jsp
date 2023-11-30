<%@ page import="com.google.gson.Gson"%>


<jsp:include page="./header.jsp" flush="true" />
<body>
	<jsp:include page="./Nav.jsp" flush="true" />
	<script src="./Script/search.js"></script>
	<script src="./Script/dynamicCode.js"></script>
	  <script>
    document.addEventListener('DOMContentLoaded', function() {
      var filtroBottone = document.getElementById('filtroBottone');
      var filtri = document.getElementById('filtri');

      filtroBottone.addEventListener('click', function() {
        if (filtri.style.display === 'none') {
          filtri.style.display = 'block';
          filtroBottone.textContent = 'Nascondi filtri';
        } else {
          filtri.style.display = 'none';
          filtroBottone.textContent = 'Mostra filtri';
        }
      });
    });
  </script>
	<script>
		$(document).ready(function() {
		  	dynamicCatalog("<%=request.getContextPath()%>/CatalogServlet");
		  	dynamicCategorie("<%=request.getContextPath()%>/CategoriaServlet");
		 	dynamicGeneri("<%=request.getContextPath()%>/GenereServlet");
		});
	</script>
	<main>
		<section id="container">
			<div id="filtri">
				<h2>Filtra Per</h2>
				<table>
					<tbody id="categorie">

					</tbody>
				</table>
				<table>
					<tbody id="marche">

					</tbody>
				</table>

			</div>
		
<div id="filtroBottone">Mostra filtri</div>
		<section id="prodotti">
			<div id="schedeProdotto"></div>
		</section>
	</section>
	</main>
	<jsp:include page="./footer.jsp" flush="true" />
</body>
</html>