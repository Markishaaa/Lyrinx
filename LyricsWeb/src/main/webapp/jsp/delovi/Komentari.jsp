<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div id="centerKomentar">
	<h3>Comments</h3>
	<br><br>
	<c:if test="${ !empty greskaKomentar }]">
		<p>${ greskaKomentar }</p>
	</c:if>
	
	<c:if test="${ (trenutniKorisnik.uloga eq 'ADMIN') or (trenutniKorisnik.uloga eq 'MODERATOR') or (trenutniKorisnik.uloga eq 'KORISNIK') }">
	<form action="/Lyrics/komentari/dodajKomentar" method="post">
		<input type="text" name="sadrzaj" placeholder="Write a comment" />
		<input type="submit" value="Post" />
	</form>
	</c:if>

	<c:choose>
		<c:when test="${ !empty komentari }">
			<c:forEach var="k" items="${ komentari }">
				<hr>
				<p><a href="/Lyrics/korisnici/getKorisnik?kor=${ k.korisnik.idKorisnika }">${ k.korisnik.username }</a>   ${ k.datumObjavljivanja }</p>
				<p>${ k.sadrzajKomentara }</p>
			    <a href="/Lyrics/komentari/dodajUpvote?kom=${ k.idKomentara }">⮝</a>  ${ k.upvote - k.downvote }  <a href="/Lyrics/komentari/dodajDownvote?kom=${ k.idKomentara }">⮟</a>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<hr>
			<p>Nothing here. No comments.</p>
		</c:otherwise>
	</c:choose>
</div>
