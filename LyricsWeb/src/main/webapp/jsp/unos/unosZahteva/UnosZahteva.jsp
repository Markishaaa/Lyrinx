<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | ${ tipZahteva.tip } Request</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
<link href="${ root }/css/form.css" rel="stylesheet">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			
			<c:if test="${ tipZahteva.tip eq 'Artist' }">
				<form action="/Lyrics/zahtevi/zahtevIzvodjac" method="post">
					<jsp:include page="/jsp/delovi/unos/DeoUnosIzvodjaca.jsp" />
				</form>
			</c:if>
			
			<c:if test="${ tipZahteva.tip eq 'Album' }">
				<form action="/Lyrics/zahtevi/zahtevAlbum" method="post">
					<jsp:include page="/jsp/delovi/unos/DeoUnosAlbuma.jsp" />
				</form>
			</c:if>
			
			<c:if test="${ tipZahteva.tip eq 'Song' }">
				<form action="/Lyrics/zahtevi/zahtevPesma" method="post">
					<jsp:include page="/jsp/delovi/unos/DeoUnosPesme.jsp" />
				</form>
			</c:if>
			
			<br>
			<c:if test="${ !empty uspesnoZahtev }">
				<p>Request sent successfully.</p>
			</c:if>
			<c:if test="${ !empty greskaZahtev }">
				<p>${ greskaZahtev }</p>
			</c:if>
			
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>