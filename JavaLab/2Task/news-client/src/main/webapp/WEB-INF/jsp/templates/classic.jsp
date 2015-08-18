<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-select.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-multiselect.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-multiselect.js"/>"></script>

</head>
<body>
	<tiles:insertAttribute name="navMenu" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
	<script type="text/javascript" src="<c:url value="/resources/js/style.js"/>"></script>
	
</body>
</html>