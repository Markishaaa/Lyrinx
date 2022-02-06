<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | New Song</title>
<link href="${ root }/css/style.css" rel="stylesheet">
<link href="${ root }/css/form.css" rel="stylesheet">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<form action="/Lyrics/pesme/dodajPesmu" method="post">
				<jsp:include page="/jsp/delovi/unos/DeoUnosPesme.jsp" />
			</form>
			<br>
			<c:choose>
				<c:when test="${!empty novaPesma}">
					<p>Song saved successfully. Song ID: ${ novaPesma.idPesme }</p>
				</c:when>
				<c:otherwise>
					<p>${ greska }</p>
				</c:otherwise>
			</c:choose>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>