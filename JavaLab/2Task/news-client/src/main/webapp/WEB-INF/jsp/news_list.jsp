<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:forEach var="newsVO" items="${newsVOList}">
	<form action="/news-client/current_news" method="post">
		<input type="hidden" name="newsId" value="${newsVO.news.id}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 align="left" class="panel-title news_title">${newsVO.news.title}</h3>
				<h5 align="left" class="news_author">(${newsVO.author.name})</h5>
			</div>
			<div class="panel-body news_short_text">
				<div align="left">${newsVO.news.shortText}</div>
				<hr>
				<div align="left">
					<c:forEach var="tag" items="${newsVO.tagList}">
						<span class="label label-primary">${tag.name}</span>
					</c:forEach>
				</div>
				<div align="right" class="news_comment_size"><spring:message code="label.message.comments"/>(${fn:length(newsVO.commentList)})</div>
				<div align="left">
					<h5>${newsVO.news.modificationDate}</h5>
				</div>
				<div align="right">
					<input type="submit" name="read"
						value=<spring:message code="label.button.read"/>
						class="btn btn-default news_button" />
				</div>
			</div>
		</div>
	</form>
</c:forEach>
