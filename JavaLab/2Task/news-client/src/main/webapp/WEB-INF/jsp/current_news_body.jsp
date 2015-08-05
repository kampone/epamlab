<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<form:form commandName="comment"
		action="/news-client/current/add-comment">
		<input type="hidden" name="newsId" value="${newsVO.news.id}" />
		<h3 align="left" class="news_current_title">${newsVO.news.title}</h3>
		<h5 align="left" class="news_author">(by ${newsVO.author.name})</h5>
		<h6 align="right" class="news_current_date">
			<fmt:formatDate type="date" value="${newsVO.news.modificationDate}" />
		</h6>
		<c:if test="${errorMessage!=null}">
			<div align="left" class="alert alert-info">
				<strong>${errorMessage}</strong>
			</div>
		</c:if>
		<div class="panel panel-primary">
			<div class="panel-body" align="left">${newsVO.news.fullText}</div>

		</div>
		<c:forEach var="comment" items="${newsVO.commentList}">
			<div align="left">
				<fmt:formatDate type="date" value="${comment.creationDate}" />
			</div>
			<p align="left" class="bg-info">${comment.text}</p>
		</c:forEach>
		<div align="left" class="form-group">
			<label for="comment"><spring:message
					code="label.message.comment" />:</label>
			<textarea name="text" class="form-control" rows="5"></textarea>
		</div>
		<form:errors class="alert alert-danger" path="text" />

		<div align="right">
			<input type="submit"
				value="<spring:message code="label.button.post_comment"/>"
				class="btn btn-info">
		</div>
	</form:form>
</div>
