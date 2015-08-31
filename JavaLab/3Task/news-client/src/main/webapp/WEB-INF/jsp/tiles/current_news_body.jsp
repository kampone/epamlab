<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container" id="rightcol">
	<form:form commandName="comment" action="/news-client/current/add-comment" method="post">
		<input type="hidden" name="newsId" value="${newsVO.news.id}" />
		<h3 align="left" class="news_current_title">
			<c:out value="${newsVO.news.title}" />
		</h3>
		<h5 align="left" class="news_author">
			(by
			<c:out value="${newsVO.author.name}" />
			)
		</h5>
		<h6 align="right" class="news_current_date">
			<fmt:formatDate type="date" value="${newsVO.news.modificationDate}" />
		</h6>
		<c:if test="${errorMessage!=null}">
			<div align="left" class="alert alert-danger	">	
				<strong><c:out value="${errorMessage}" /></strong>
			</div>
		</c:if>
		<div class="panel panel-primary">
			<div class="panel-body" align="left">
				<c:out value="${newsVO.news.fullText}" />
			</div>

		</div>
		<c:forEach var="current_comment" items="${newsVO.commentList}">
			<div align="left">
				<fmt:formatDate type="date" value="${current_comment.creationDate}" />
			</div>
			<p align="left" class="bg-info">
				<c:out value="${current_comment.text}" />
			</p>
		</c:forEach>
		<div align="left" class="form-group">
			<label for="comment"><spring:message
					code="label.message.comment" />:</label>
			<textarea name="text" class="form-control" rows="5">${comment.text}</textarea>
		</div>
		<form:errors class="alert alert-danger" path="text" />

		<div align="right">
			<input type="submit"
				value="<spring:message code="label.button.post_comment"/>"
				class="btn btn-info">
		</div>
	</form:form>
</div>
