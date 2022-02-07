<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | ${ pesma.imePesme }</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div class="container">
			<div id="centerPesme">
				<h2>${ pesma.imePesme }</h2>
				<p> - ${ pesma.izvodjac.imeIzvodjaca }</p>
				<br><hr><br>
				<p style="white-space: pre-line">${ pesma.tekst }</p>
			</div>
			
			<div class="album">
				<c:if test="${ !empty editUspesno }">
					<p>${ editUspesno }</p>
					<% request.getSession().setAttribute("editUspesno", null); %>
					<br><br>
				</c:if>
				<c:if test="${ !empty greskaEditZahtev }">
					<p>${ greskaEditZahtev }</p>
					<% request.getSession().setAttribute("greskaEditZahtev", null); %>
					<br><br>
				</c:if>
				
				<security:authorize access="isAuthenticated()">
					<c:if test="${ (trenutniKorisnik.uloga eq 'ADMIN') or (trenutniKorisnik.uloga eq 'MODERATOR') or (trenutniKorisnik.uloga eq 'KORISNIK') }">
						<p><a href="/Lyrics/jsp/unos/unosZahteva/EditPesmeZahtev.jsp">Request to edit this song</a></p>
					</c:if>
				</security:authorize>
				<br><br>
				<jsp:include page="/jsp/delovi/JedanAlbum.jsp" />
			</div>
		</div>
		
		<jsp:include page="/jsp/delovi/Komentari.jsp" />
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>