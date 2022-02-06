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

		<div id="center">
			<p style="font-size: 20px;" align="center">Welcome.</p>
			
			<security:authorize access="isAuthenticated()">
				<c:if test="${ trenutniKorisnik.uloga eq 'ADMIN' }">
					<a href="/Lyrics/izvodjaci/getIzvodjaci?l=redirect:/albumi/getAlbumi">Add a Song</a>
					<a href="/Lyrics/jsp/unos/UnosIzvodjaca.jsp">Add an Artist</a>
					<a href="/Lyrics/izvodjaci/getIzvodjaci?l=jsp/unos/UnosAlbuma">Add an Album</a>
					<a href="/Lyrics/albumi/getNePuneAlbume">Add a Song to an Album</a>
				</c:if>
				
				<a href="/Lyrics/zahtevi/getZahtevi">Send a request</a>
				<a href="/Lyrics/zahtevi/getRandomZahtev">View a request</a>
			</security:authorize>
		</div>

		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>