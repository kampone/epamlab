<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title><tiles:getAsString name="title" /></title>
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-theme.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/css/style.css" />" rel="stylesheet">

</head>
<body>
	<tiles:insertAttribute name="navMenu" />
	<tiles:insertAttribute name="backButton" />
	<div align="center" class="news_current_body">
		<div align="center">
			<tiles:insertAttribute name="body" />
		</div>

	</div>
	<div>
	<br>
	<br>
	<tiles:insertAttribute name="prevAndNext" />
	</div>
	<!-- Footer -->
	<tiles:insertAttribute name="footer" />
</body>
</html>