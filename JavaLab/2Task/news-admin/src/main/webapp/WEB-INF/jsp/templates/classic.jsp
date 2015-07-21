<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-theme.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/css/bootstrap-select.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/css/buttons.css" />" rel="stylesheet">

</head>
<body>
	<!-- Header -->
	<tiles:insertAttribute name="navMenu" />
	<table width="1200px" align="center">
		<tr>
			<!-- Personal Menu -->
			<td width="20%" align="center" rowspan="3"><tiles:insertAttribute
					name="menu" /></td>
			<!-- Current Profile -->
			
			<td width="80%" align="center"><tiles:insertAttribute
					name="search" /></td>
		</tr>
		<tr>
			<!-- The Body of Page -->
			<td align="center"><tiles:insertAttribute name="body" /></td>
		</tr>
	</table>

	<!-- Footer -->
	<tiles:insertAttribute name="footer" />
</body>
</html>