<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | New Album</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
<link href="${ root }/css/form.css" rel="stylesheet">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
	
		<div id="center">
			<form action="/Lyrics/albumi/dodajAlbum" method="post">
				<jsp:include page="/jsp/delovi/unos/DeoUnosAlbuma.jsp" />
			</form>
			<br>
			<c:if test="${ !empty album }">
				<p>Album saved successfully. Album ID: ${ album.idAlbuma }</p>
			</c:if>
			<c:if test="${ empty album }">
				<p>${ greska }</p>
			</c:if>
			<br> <br> <a href="/Lyrics/index.jsp">Back to main page.</a>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>