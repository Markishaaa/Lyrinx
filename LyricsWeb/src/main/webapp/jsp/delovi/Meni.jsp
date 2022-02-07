<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="navbar">
	<form action="/Lyrics/pesme/nadjiPesme" method="get">
		<input type="text" name="pretraga" placeholder="search"> 
		<input type="submit" value="🔍">
	</form>

	<c:choose>
		<c:when test="${ empty username }">
			<a href="/Lyrics/auth/loginPage">Login</a>
		</c:when>
		<c:otherwise>
			<a href="/Lyrics/auth/logout">Logout</a>
			<a href="/Lyrics/korisnici/nadjiKorisnika">${ username }</a>
		</c:otherwise>
	</c:choose>

	<div class="center">
		<div class="filter-invert">
			<a href="/Lyrics/pesme/nadjiNovePesme"><div class="logo"></div></a>
		</div>
	</div>

</div>