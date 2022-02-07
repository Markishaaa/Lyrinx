<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Song Lyrics</title>
<link href="${ root }/css/style.css" rel="stylesheet">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<security:authorize access="isAuthenticated()">
			<div class="header">
				<c:if test="${ trenutniKorisnik.uloga eq 'ADMIN' }">
					<a href="/Lyrics/izvodjaci/getIzvodjaci?l=redirect:/albumi/getAlbumi">Add a Song | </a>
					<a href="/Lyrics/jsp/unos/UnosIzvodjaca.jsp">Add an Artist</a> | 
					<a href="/Lyrics/izvodjaci/getIzvodjaci?l=jsp/unos/UnosAlbuma">Add an Album</a> | 
					<a href="/Lyrics/albumi/getNePuneAlbume">Add a Song to an Album</a> | 
					<a href="/Lyrics/jsp/brisanje/BrisanjePesme.jsp">Delete a Song</a> | 
					<a href="/Lyrics/korisnici/getKorisnici">Ban a User</a> |
				</c:if>
				
				<c:if test="${ (trenutniKorisnik.uloga eq 'ADMIN') or (trenutniKorisnik.uloga eq 'MODERATOR') or (trenutniKorisnik.uloga eq 'KORISNIK') }">
					<a href="/Lyrics/zahtevi/getZahtevi">Send a request</a> 
				</c:if>
				<c:if test="${ (trenutniKorisnik.uloga eq 'ADMIN') or (trenutniKorisnik.uloga eq 'MODERATOR') }">
					| <a href="/Lyrics/zahtevi/getRandomZahtev">View a request</a>
				</c:if>
			</div>
		</security:authorize>
		
		<div id="center">
			<p style="font-size: 20px;" align="center">Newest added songs:</p>
			<c:choose>
				<c:when test="${ !empty najnovije }">
					<c:forEach var="p" items="${ najnovije }">
						<p><a href="/Lyrics/komentari/nadjiKomentare?pesma=${ p.imePesme }">${ p.imePesme }</a> - <a href="/Lyrics/izvodjaci/nadjiIzvodjaca?izvodjac=${ p.izvodjac.imeIzvodjaca }">${ p.izvodjac.imeIzvodjaca }</a></p>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>No songs yet.</p>
				</c:otherwise>
			</c:choose>
		</div>

		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>