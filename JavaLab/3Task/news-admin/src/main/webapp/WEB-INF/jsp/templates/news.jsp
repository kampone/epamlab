<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table width="1000px" align="center">

		<tr>
			<!-- Search -->
			<td width="80%" align="center"><tiles:insertAttribute
					name="search" /></td>
		</tr>

		<tr>
			<td align="center"><tiles:insertAttribute name="list" /></td>
		</tr>

		<tr>
			<td align="center"><tiles:insertAttribute name="pagination" /></td>
		</tr>

	</table>

