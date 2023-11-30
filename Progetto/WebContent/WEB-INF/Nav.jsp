<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="./header.jsp" flush="true"/>
<body>
	<header class="main-head">
		<nav aria-label="principale">
			<h1 id="logo">Black &amp; White</h1>
			<ul class="links-nav">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="catalogo.jsp">Catalogo</a></li>
			</ul>
			<ul class="utilities">
				<li class="searchbar">
					<div class="navsearch">
						<button class="icon"></button>
						<div class="input">
							<input type="text" id="search-input" onkeyup="searchAndFilter()"
								placeholder="Inserisci il nome del prodotto">
						</div>
						<span class="clear" onclick="document.getElementById('search-input').value = '' "></span>
					</div> 
					<script>
						const icon = document.querySelector(".icon");
						const search = document.querySelector(".navsearch");
						icon.onclick = function(){
							if("<%= request.getServletPath() %>" == "/catalogo.jsp")
								search.classList.toggle("active");
							else
								 window.location.href = "catalogo.jsp";
						}
					</script>
				</li>
				<li><a href="./profilo.jsp"><img
						src="./icons/user_person_profile_avatar_icon_190943.ico"
						alt="avatar utente" /></a></li>
				<li><a href="carrello.jsp"><img
						src="./icons/shopping-cart_icon-icons.com_65051.ico" alt="" /></a>
				</li>
				<li><button class="hamburger">
					<div class="bar"></div>
				</button></li>
			</ul>
		</nav>
	</header>
	<nav class ="mobile-nav" aria-label="menuHamburger">
		<div class="nav-container">
			<a href="index.jsp">Home</a>
			<a href="catalogo.jsp">Catalogo</a>
		</div>
	</nav>
	<script>
		const menu_btn = document.querySelector('.hamburger');
		const mobile_menu = document.querySelector('.mobile-nav');
		menu_btn.addEventListener('click', function(){
			menu_btn.classList.toggle('is-active');
			mobile_menu.classList.toggle('is-active');
		});
	</script>
</body>