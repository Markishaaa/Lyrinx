<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${ empty pesma }">
		<h4><a href="/Lyrics/jsp/pregled/PregledAlbuma.jsp">${ album.nazivAlbuma }</a></h4>
		<p>No songs.</p>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${ empty pesma.album }">
				<p>Single - <a href="/Lyrics/komentari/nadjiKomentare?pesma=${ pesma.imePesme }">${ pesma.imePesme }</a></p>
			</c:when>
			<c:otherwise>
				<h4><a href="/Lyrics/jsp/pregled/PregledAlbuma.jsp">${ pesma.album.nazivAlbuma }</a></h4>
				<ol>
					<c:forEach var="pesma" items="${ pesma.album.pesmas }">
						<li><a href="/Lyrics/komentari/nadjiKomentare?pesma=${ pesma.imePesme }">${ pesma.imePesme }</a></li>
					</c:forEach>
				</ol>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
