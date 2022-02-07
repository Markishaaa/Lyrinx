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
			<form action="/Lyrics/korisnici/banujKorisnika" method="post">
				<table class="centerTable">
					<tr>	
						<td>
							<select name="idKorisnika">
								<c:forEach items="${ korisnici }" var="k">
									<option value="${ k.idKorisnika }">${ k.username }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td><input type="submit" value="BAN"></td>
					</tr>
				</table>
				
				<br><br>
				<c:if test="${ !empty ban }">
					<p>${ ban }</p>
				</c:if>
			</form>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>