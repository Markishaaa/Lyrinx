<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="centerTable">
	<tr>
		<td>Song name:</td>
		<td><input type="text" name="ime" required></td>
	</tr>
	<tr>
		<td>Artist:</td>
		<td>
			<select name="idIzvodjaca">
				<c:forEach items="${ izvodjaci }" var="i">
					<option value="${ i.idIzvodjaca }">${ i.imeIzvodjaca }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Album:</td>
		<td>
			<select name="idAlbuma">
				<option value="-1">Single</option>
				<c:forEach items="${ albumi }" var="a">
					<option value="${ a.idAlbuma }">${ a.nazivAlbuma }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Number of verses:</td>
		<td>
			<select name="strofa">
				<c:forEach var="i" begin="1" end="10" varStatus="loop">
					<option value="${ i }">${ i }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Lyrics:</td>
		<td>
			<textarea name="tekst" rows="20" cols="100" 
				placeholder="separate verses with one empty line" required></textarea>
		</td>
	</tr>
	<tr>
		<td><input type="submit" value="Save"></td>
	</tr>
</table>