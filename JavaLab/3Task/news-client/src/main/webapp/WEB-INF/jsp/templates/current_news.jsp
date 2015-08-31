<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<tiles:insertAttribute name="backButton" />
	<div align="center" class="news_current_body">
		<div align="center">
			<tiles:insertAttribute name="current" />
		</div>

	</div>
	<div>
	<br>
	<br>
	<tiles:insertAttribute name="prevAndNext" />
	</div>
