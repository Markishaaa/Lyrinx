<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Search Results</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<h2>Search results:</h2>
			<c:choose>
				<c:when test="${ (empty nadjenePesme) and (empty nadjeniIzvodjaci) and (empty nadjeniAlbumi) }">
					<p>There are no search results.</p>
				</c:when>
				<c:otherwise>
					<a href="#pesmePretraga">songs   </a>
					<a href="#izvodjaciPretraga">artists   </a>
					<a href="#albumiPretraga">albums</a>
					
					<br><hr id="pesmePretraga">
					<h3>Songs:</h3>
					<c:choose>
						<c:when test="${ empty nadjenePesme }">
							<p>No songs found.</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="pesma" items="${ nadjenePesme }">
								<p><a href="/Lyrics/komentari/nadjiKomentare?pesma=${ pesma.imePesme }">${ pesma.imePesme }</a> - ${ pesma.izvodjac.imeIzvodjaca }</p>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
					<br><hr id="izvodjaciPretraga">
					<h3>Artists:</h3>
					<c:choose>
						<c:when test="${ empty nadjeniIzvodjaci }">
							<p>No artists found.</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="izvodjac" items="${ nadjeniIzvodjaci }">
								<p><a href="/Lyrics/izvodjaci/nadjiIzvodjaca?izvodjac=${ izvodjac.imeIzvodjaca }">${ izvodjac.imeIzvodjaca }</a></p>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
					<br><hr id="albumiPretraga">
					<h3>Albums:</h3>
					<c:choose>
						<c:when test="${ empty nadjeniAlbumi }">
							<p>No albums found.</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="album" items="${ nadjeniAlbumi }">
								<p><a href="/Lyrics/albumi/nadjiAlbum?album=${ album.nazivAlbuma }">${ album.nazivAlbuma }</a> - <a href="/Lyrics/izvodjaci/nadjiIzvodjaca?izvodjac=${ izvodjac.imeIzvodjaca }">${ album.izvodjac.imeIzvodjaca }</a></p>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
				</c:otherwise>
			</c:choose>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>