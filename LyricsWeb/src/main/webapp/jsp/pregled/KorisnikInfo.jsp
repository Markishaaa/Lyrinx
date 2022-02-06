<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Profile</title>
<link href="${ root }/css/style.css" rel="stylesheet">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />

		<div id="center">
			<c:choose>
				<c:when test="${ empty korisnik } }">
					<p>Username: ${ korisnik.username }</p>
					<p>Role: ${ korisnik.uloga }</p>
					
					<security:authorize access="isAuthenticated()">
						<a href="/Lyrics/korisnici/banujKorisnika?k=${ korisnik.idKorisnika }">Ban user</a>
					</security:authorize>
				</c:when>
				<c:otherwise>
					<p>Username: ${ trenutniKorisnik.username }</p>
					<form action="/Lyrics/korisnici/promovisiKorisnika" method="post">
						<input type="submit" value="Promote" />
					</form>
					<br>
					<c:if test="${ !empty korisnik.pesmas }">
						<p>Contributed to songs:</p>
						<c:forEach var="p" items="${ korisnik.pesmas }">
							<p>${ p.imePesme }</p>
						</c:forEach>
					</c:if>
					<c:if test="${ !empty korisnik.albums }">
						<p>Contributed to albums:</p>
						<c:forEach var="a" items="${ korisnik.albums }">
							<p>${ a.nazivAlbuma }</p>
						</c:forEach>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>

		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>