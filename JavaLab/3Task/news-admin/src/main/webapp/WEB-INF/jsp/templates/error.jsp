<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link href="<c:url value="/resources/css/button.css" />" rel="stylesheet">

</head>
<body>
	<!-- Header -->
	<div class="panel panel-danger">
      <div class="news-title panel-heading">Error 404</div>
      <div class="panel-body">Sorry, this page is not available</div>
    </div>
	<!-- Footer -->
	<tiles:insertAttribute name="footer" />
</body>
</html>