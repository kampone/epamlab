<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
	href="<c:url value="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>">
<script src="<c:url value="http://code.jquery.com/jquery-1.10.2.js"/>"></script>
<script
	src="<c:url value="http://code.jquery.com/ui/1.11.4/jquery-ui.js"/>"></script>



<form:form commandName="newsPageVO" action="/news-admin/edit_news/save"
	role="form">

	<form:errors cssStyle="color: red" path="news.title" />
	<br>
	<form:errors cssStyle="color: red" path="news.shortText" />
	<br>
	<form:errors cssStyle="color: red" path="news.fullText" />
	<br>
	<form:errors cssStyle="color: red" path="authorId" />
	<br>

	<input type="hidden" name="news.id" value="${newsPageVO.news.id}" />
	<div class="form-group">
		<label for="title">Title:</label> <input type="text"
			class="form-control" name="news.title"
			value="${newsPageVO.news.title}" id="title">
	</div>
	<script>
		$(function date() {
			$("#datepicker").datepicker();
		});
	</script>
	<div class="form-group">
		<label for="date">Date:</label><p> <input id="datepicker" type="text"
			class="form-control"
			value='<fmt:formatDate value="${newsPageVO.news.creationDate}"/>'
			id="date"></p>
	</div>
	<div class="form-group">
		<label for="short">Brief:</label>
		<textarea rows="3" name="news.shortText" class="form-control"
			id="short">${newsPageVO.news.shortText}</textarea>
	</div>
	<div class="form-group">
		<label for="full">Content:</label>
		<textarea rows="6" name="news.fullText" class="form-control" id="full">${newsPageVO.news.fullText}</textarea>
	</div>
	<script type="text/javascript">
		$(document).ready(function  multisel() {
			$('#select-author').multiselect();
			$('#select-tag').multiselect();
		});
	</script>
	<select id="select-author" name="authorId">
		<option value=""><spring:message code="label.select.author" /></option>
		<c:forEach var="author" items="${authors}">
			<option
				<c:if test="${author.id eq newsPageVO.authorId }">selected="selected"</c:if>
				value="${author.id}">${author.name}</option>
		</c:forEach>

	</select>

	<select id="select-tag" multiple="multiple" name="tagIdList">
		<c:forEach var="tag" items="${tags}">
			<c:set var="contains" value="false" />
			<c:forEach var="sc_tagId" items="${newsPageVO.tagIdList}">
				<c:if test="${tag.id eq sc_tagId }">
					<c:set var="contains" value="true" />
				</c:if>
			</c:forEach>
			<option <c:if test="${contains}">selected</c:if> value="${tag.id}">${tag.name}</option>
		</c:forEach>
	</select>
	<input type="submit" class="btn btn-default" value="Submit">
</form:form>
