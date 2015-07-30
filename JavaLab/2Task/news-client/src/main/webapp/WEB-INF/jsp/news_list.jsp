<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<table align="center" border="4 " width="900px">
	<c:forEach var="news" items="${newsList}">
		<tr>
			<td>
				<h3 align="center">${news.title}</h3>
				<h4 align="left">${news.shortText}...</h4>
				<h6>${news.modificationDate}</h6> <input type="button" name="read"
				value=<spring:message code="label.button.read"/>
				class="btn btn-default" />
			</td>
		</tr>
	</c:forEach>
</table>
${searchCriteria}
