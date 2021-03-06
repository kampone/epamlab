<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div align="left" class="alert alert-info">
	<strong><spring:message code="label.message.find" />
		${numberOfNews} <spring:message code="label.message.news" /></strong>
</div>
<c:forEach var="news" items="${newsList}">
	<input type="hidden" name="index" value="${index}">
	<c:set var="index" value="${index+1}"/>

	<div class="panel panel-default">

		<div class="panel-heading">
			<h3 align="left" class="panel-title news_title"><c:out value="${news.title}"/></h3>
			<h5 align="left" class="news_author">(by <c:out value="${news.author.name}"/>)</h5>
		</div>

		<div class="panel-body news_short_text">
		
			<div align="left"><c:out value="${news.shortText}"/></div>
			<hr>
			<div align="left">
				<c:forEach var="tag" items="${news.tagList}">
					<span class="label label-primary"><c:out value="${tag.name}"/></span>
				</c:forEach>
			</div>
			<div align="right" class="news_comment_size">
				<spring:message code="label.message.comments" />
				<span class="badge"><c:out value="${fn:length(news.commentList)}"/> </span>
			</div>
			<h6 align="left" class="news_current_date">
				<fmt:formatDate type="date" value="${news.modificationDate}" />
			</h6>

			<div align="right">
				<a href="/news-client/current/news/${index-1}"><input
					type="button" name="read"
					value=<spring:message code="label.button.read"/>
					class="btn btn-default news_button" /></a>
			</div>
		</div>
	</div>
</c:forEach>
