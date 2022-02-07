<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Add Song to Album</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
<link href="${ root }/css/form.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<form action="/Lyrics/albumi/dodajPesmuUAlbum" method="post">
				<table class="centerTable">
					<tr>
						<td>Album</td>
						<td>
							<select name="idAlbuma">
								<c:forEach var="a" items="${ albumi }">
									<option value="${ a.idAlbuma }">${ a.nazivAlbuma }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Song</td>
						<td>
							<select name="idPesme">
								<c:forEach var="p" items="${ pesme }">
									<option value="${ p.idPesme }">${ p.imePesme }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td><input type="submit" value="Save"></td>
					</tr>
				</table>
			</form>
			
			<c:choose>
				<c:when test="${ !empty pesmaAlbum }">
					<p>${ pesmaAlbum }</p>
				</c:when>
				<c:when test="${ !empty greska }">
					<p>${ greska }</p>
				</c:when>
			</c:choose>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>