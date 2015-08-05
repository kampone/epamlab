<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<form:form commandName="searchCriteria"
	action="/news-client/news/setSearchCriteria">
	<select name="authorId">
		<option value=""><spring:message code="label.select.author" /></option>
		<c:forEach var="author" items="${authors}">
			<option value="${author.id}">${author.name}</option>
		</c:forEach>
	</select>
	<select multiple name="tagIdList">
		<option value=""><spring:message code="label.select.taglist" /></option>
		<c:forEach var="tag" items="${tags}">
			<option value="${tag.id}">${tag.name}</option>
		</c:forEach>
	</select>		
	<script src="<c:url value="/js/jquery.multiple.select.js"/>"></script>
	<script>
		$('select').multipleSelect();
	</script>
	<input type="submit" name="filter"
		value=<spring:message code="label.button.filter"/>
		class="btn btn-default" />
	<a href="/news-client/news/reset"><input type="button" name="reset"
		value=<spring:message code="label.button.reset"/>
		class="btn btn-default" /></a>

</form:form>