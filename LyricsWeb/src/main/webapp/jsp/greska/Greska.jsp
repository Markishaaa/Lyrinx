<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | Error</title>
<link href="${ root }/css/style.css" rel="stylesheet">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<p>A database error has been encountered.</p>
			<br><br>
			<a href="/Lyrics/index.jsp">Go Back</a>
		</div>
	
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>