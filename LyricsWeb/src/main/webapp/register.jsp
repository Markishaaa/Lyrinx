<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Register</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
<link href="${ root }/css/form.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<sf:form modelAttribute="user" action="register" method="post">
				<c:if test="${ !empty greska }">Greska!</c:if>
				<table class="centerTable">
					<tr>
						<td>Enter username:</td>
						<td><sf:input path="username" name="korisnik" /></td>
					</tr>
					<tr>
						<td>Enter password:</td>
						<td><sf:password path="password" /></td>
					</tr>
					<tr>
						<td><sf:select path="uloga" items="${ roles }" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Register" /></td>
					</tr>
				</table>
			</sf:form>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>