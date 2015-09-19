<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table width="800px" align="center">

		<tr>
			<!-- Search -->
			<td width="80%" align="left"><tiles:insertAttribute name="backButton" /></td>
		</tr>

		<tr>
			<td align="center"><tiles:insertAttribute name="current" /></td>
		</tr>

		<tr>
			
			<td align="center"><br><br><tiles:insertAttribute name="prevAndNext" /></td>
		</tr>

	</table>