<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Login</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
<link href="${ root }/css/form.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />

		<div id="center">
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post">
				<table class="centerTable">
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username"
							placeholder="enter your username" required></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password"
							placeholder="enter your password" required></td>
					</tr>
					<tr>
						<td><input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /></td>
						<td><input type="submit" value="Login"></td>
					</tr>
				</table>
				<br><br> 
				<p>Don't have an account? <a href="/Lyrics/auth/registerUser">Register now!</a></p>
			</form>
			<c:if test="${ !empty param.error }">
				<p>Incorrect username or password.</p>
			</c:if>
		</div>

		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>