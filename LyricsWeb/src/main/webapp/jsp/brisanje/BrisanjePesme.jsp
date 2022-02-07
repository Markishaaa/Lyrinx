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
<link href="${ root }/css/form.css" rel="stylesheet">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<form action="/Lyrics/pesme/obrisiPesmu" method="post">
				<table class="centerTable">
					<tr>
						<td>Artist name:</td>
						<td><input type="text" name="izvodjac" required></td>
					</tr>
					<tr>
						<td>Song name:</td>
						<td><input type="text" name="ime" required></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Save"></td>
					</tr>
				</table>
			</form>
			<br>
			<c:if test="${ !empty obrisiPesmu }">
				<p>${ obrisiPesmu }</p>
			</c:if>
			<br>
			<br>
			<a href="/Lyrics/index.jsp">Back to main page.</a>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>