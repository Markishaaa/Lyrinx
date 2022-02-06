<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Requests </title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<c:choose>
				<c:when test="${ empty randomZahtev }">
					<p>There are no requests at the moment</p>
				</c:when>
				<c:otherwise>
					<p>User: ${ randomZahtev.korisnik.username }</p>
					<p>User ID: ${ randomZahtev.korisnik.idKorisnika }</p>
					<br>
					<p>Request ID: ${ randomZahtev.idZahteva }</p>
					
					<c:if test="${ randomZahtev.tipZahteva eq 'PESMA' }">
						<p>Request type: song</p>
						<br>
						<h2>${ randomZahtev.imePesme }</h2>
						<p> - ${ randomZahtev.izvodjac.imeIzvodjaca }</p>
						<br><hr><br>
						<p style="white-space: pre-line">${ randomZahtev.tekst }</p>
					</c:if>
					
					<c:if test="${ randomZahtev.tipZahteva eq 'EDIT' }">
						<p>Request type: edit</p>
						<br>
						<h2>${ randomZahtev.imePesme }</h2>
						<p> - ${ randomZahtev.izvodjac.imeIzvodjaca }</p>
						<br><hr><br>
						<p style="white-space: pre-line">${ randomZahtev.tekst }</p>
					</c:if>
					
					<c:if test="${ randomZahtev.tipZahteva eq 'IZVODJAC' }">
						<p>Request type: artist</p>
						<br>
						<p>Artist name: ${ randomZahtev.imeIzvodjaca }</p>
					</c:if>
					
					<c:if test="${ randomZahtev.tipZahteva eq 'ALBUM' }">
						<p>Request type: album</p>
						<br>
						<p>Album name: ${ randomZahtev.nazivAlbuma }</p>
						<p>Artist name: ${ randomZahtev.izvodjac.imeIzvodjaca }</p>
					</c:if>
					
					<br>
					<form action="/Lyrics/zahtevi/odobrenjeZahteva" method="post">
						<select name="odobrenje">
							<option value="true" >Approve</option>
							<option value="false">Refuse</option>
						</select>
						<input type="submit" value="Proceed" />
					</form>
					
					<br><br>
					<c:choose>
						<c:when test="${ empty greska }">
							<p>${ poruka }</p>
							<br>
							<p><a href="/Lyrics/zahtevi/getRandomZahtev">Next</a></p>
						</c:when>
						<c:otherwise>
							<p>${ greska }</p>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<p><a href="/Lyrics/">Go back</a></p>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>