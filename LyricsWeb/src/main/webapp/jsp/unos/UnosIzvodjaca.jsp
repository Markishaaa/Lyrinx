<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | New Artist</title>
<link href="${ root }/css/style.css" rel="stylesheet">
<link href="${ root }/css/form.css" rel="stylesheet">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<form action="/Lyrics/izvodjaci/dodajIzvodjaca" method="post">
				<jsp:include page="/jsp/delovi/unos/DeoUnosIzvodjaca.jsp" />
			</form>
			<br>
			<c:if test="${ !empty izvodjac }">
				<p>Artist successfully saved. Artist ID: ${ izvodjac.idIzvodjaca }</p>
			</c:if>
			<c:if test="${ empty izvodjac }">
				<p>${ greska }</p>
			</c:if>
			<br>
			<br>
			<a href="/Lyrics/index.jsp">Back to main page.</a>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>