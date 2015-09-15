<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<form:form commandName="newsPage" action="/news-admin/edit_news/save"
	role="form">
	<form:errors cssStyle="color: red" path="title" />
	<br>
	<form:errors cssStyle="color: red" path="modificationDate" />
	<br>
	<form:errors cssStyle="color: red" path="shortText" />
	<br>
	<form:errors cssStyle="color: red" path="fullText" />
	<br>
	<form:errors cssStyle="color: red" path="authorId" />
	<br>
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<c:if test="${newsPage.modificationDate!=null}"><c:set var="now" value="${newsPage.modificationDate}" /></c:if>
	<input type="hidden" name="newsId"
		value="<c:out value="${newsPage.newsId}"/>" />
	<div class="form-group">
		<label for="title"><spring:message code="lavel.text.title" />:</label>
		<input type="text" class="form-control" name="title"
			value="<c:out value="${newsPage.title}"/>" id="title">
	</div>
	<div class="form-group">
		<label for="date"><spring:message code="lavel.text.date" />:</label>
		<p>
			<input id="datepicker" name="modificationDate" type="text"
				class="form-control"
				value="<fmt:formatDate value="${now}"/>"
				id="date" />
		</p>
	</div>
	<div class="form-group">
		<label for="short"><spring:message code="lavel.text.brief" />:</label>
		<textarea rows="3" name="shortText" class="form-control"
			id="short"><c:out value="${newsPage.shortText}" /></textarea>
	</div>
	<div class="form-group">
		<label for="full"><spring:message code="lavel.text.content" />:</label>
		<textarea rows="6" name="fullText" class="form-control" id="full"><c:out
				value="${newsPage.fullText}" /></textarea>
	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/js/style.js" 	/>"></script>
	<select id="select-author" name="authorId">
		<option value=""><spring:message code="label.select.author" /></option>
		<c:forEach var="author" items="${authors}">
			<option
				<c:if test="${author.authorId eq newsPage.authorId}">selected="selected"</c:if>
				value="${author.id}"><c:out value="${author.name}" /></option>
		</c:forEach>

	</select>

	<select id="select-tag" multiple="multiple" name="tagIdList">
		<c:forEach var="tag" items="${tags}">
			<c:set var="contains" value="false" />
			<c:forEach var="sc_tag_id" items="${newsPage.tagIdList}">
				<c:if test="${tag.id eq sc_tag_id }">
					<c:set var="contains" value="true" />
				</c:if>
			</c:forEach>
			<option <c:if test="${contains}">selected</c:if> value="${tag.id}">${tag.name}</option>
		</c:forEach>
	</select>
	<input type="submit" class="btn btn-default"
		value="<spring:message code="label.button.save"/>">
</form:form>
