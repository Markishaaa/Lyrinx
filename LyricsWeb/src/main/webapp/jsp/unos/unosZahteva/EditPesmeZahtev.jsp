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
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<h2>${ pesma.imePesme }</h2>
			<form action="/Lyrics/zahtevi/zahtevEditPesme" method="post">
				<textarea name="tekst" rows="20" cols="100" style="white-space: pre-line">${ pesma.tekst }</textarea>
				<input type="submit" value="Send">
			</form>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>