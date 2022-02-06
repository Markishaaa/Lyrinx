<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | ${ pesma.imePesme }</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
<link href="${ root }/css/link.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<h2>${ izvodjac.imeIzvodjaca }</h2>
			<h3>Albums:</h3>
			<c:forEach var="album" items="${ izvodjac.albums }">
				<h4><a href="/Lyrics/jsp/pregled/PregledAlbuma.jsp">${ album.nazivAlbuma }</a></h4>
				<ol>
					<c:forEach var="pesma" items="${ album.pesmas }">
						<li><a href="/Lyrics/komentari/nadjiKomentare?pesma=${ pesma.imePesme }">${ pesma.imePesme }</a></li>
					</c:forEach>
				</ol>
			</c:forEach>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>