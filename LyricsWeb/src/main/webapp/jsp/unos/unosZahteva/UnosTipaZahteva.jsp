<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Request </title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<form action="/Lyrics/zahtevi/getTipZahteva" method="get">
				<h3>Type of request: </h3>
				<select name="tipZahteva">
					<c:forEach var="z" items="${ zahtevi }">
						<option value="${ z }">${ z.tip }</option>
					</c:forEach>
				</select>
				<input type="submit" value="Proceed" />
			</form>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
