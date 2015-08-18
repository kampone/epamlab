<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="<c:url value="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>">
<script src="<c:url value="http://code.jquery.com/jquery-1.10.3.js"/>"></script>
<script
	src="<c:url value="http://code.jquery.com/ui/1.11.4/jquery-ui.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/accord.js"/>"></script>


<div align="left" id="accordion">
	<c:forEach var="tag" items="${tagList}">
		<h3>${tag.name}</h3>
		<div>
			<form:form commandName="tag" action="/news-admin/tags/update">
				<form:errors class="alert alert-danger" path="name" />
				
				<input type="hidden" name="id" value="${tag.id}">

				<p>
					<input type="text" name="name" value="${tag.name}">
				</p>
				<p>
					<input type="submit" class="btn btn-default"
						value="<spring:message code="label.button.update"/>">
				</p>
			</form:form>
			<p>
				<a href="/news-admin/tags/delete/${tag.id}"><button
						class="btn btn-default">
						<spring:message code="label.button.delete" />
					</button></a>
			</p>


		</div>
	</c:forEach>
</div>
<br>
<br>
<form:form commandName="tag" class="form-inline"
	action="/news-admin/tags/add">
	<form:errors class="alert alert-danger" path="name" />
	<div class="form-group">
		<label for="add-tag"><spring:message code="label.text.add_tag" />:</label>
		<input name="name" value="${tag.name}" type="text"
			class="form-control" id="add-tag">
	</div>
	<button type="submit" class="btn btn-default">
		<spring:message code="label.button.save" />
	</button>
</form:form>



