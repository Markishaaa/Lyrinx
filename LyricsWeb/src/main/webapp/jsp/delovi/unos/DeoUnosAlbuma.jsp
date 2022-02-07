<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="centerTable">
	<tr>
		<td>Album name:</td>
		<td><input type="text" name="ime" required></td>
	</tr>
	<tr>
		<td>Number of songs:</td>
		<td><input type="text" name="brPesama" required></td>
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
		<td></td>
		<td><input type="submit" value="Save"></td>
	</tr>
</table>