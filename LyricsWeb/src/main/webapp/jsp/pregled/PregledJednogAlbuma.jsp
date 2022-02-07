<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<title>Lyrinx | ${ album.nazivAlbuma }</title>
<link href="${ root }/css/style.css" rel="stylesheet" type="text/css">
<link href="${ root }/css/list.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="filter">
		<jsp:include page="/jsp/delovi/Meni.jsp" />
		
		<div id="center">
			<c:if test="${ !empty album.slika }">
				<img style="float: left; margin-left: 10%" SRC="${ album.slika }" width="100" height="100">
			</c:if>
			<br>
			<c:if test="${ (trenutniKorisnik.uloga eq 'ADMIN') or (trenutniKorisnik eq 'MODERATOR') }">
				<a href="/Lyrics/jsp/unos/UnosSlikeAlbuma.jsp">Upload an image</a>
			</c:if>
		
			<div class="album">
				<jsp:include page="/jsp/delovi/JedanAlbum.jsp" />
			</div>
		</div>
		
		<jsp:include page="/jsp/delovi/Footer.jsp" />
	</div>
</body>
</html>