<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="<c:url value="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>">
<script src="<c:url value="http://code.jquery.com/jquery-1.10.2.js"/>"></script>
<script
	src="<c:url value="http://code.jquery.com/ui/1.11.4/jquery-ui.js"/>"></script>

<script>
	$(function() {
		$("#accordion").accordion({
			header : "h3",
			collapsible : true,
			active : false
		});
	});
</script>

<div id="accordion">
	<c:forEach var="author" items="${authorList}">
		<h3>${author.name}<c:if test="${author.expired != null}">
				<b style="color: red">(<spring:message
						code="label.message.expired" />)
				</b>
			</c:if>
		</h3>
		<div>
			<form:form commandName="author" action="/news-admin/authors/update">
				<form:errors class="alert alert-danger" path="name" />
				<input type="hidden" name="id" value="${author.id}">

				<p>
					<input type="text" name="name" value="${author.name}">
				</p>
				<p>
					<input type="submit" class="btn btn-default"
						value="<spring:message code="label.button.update"/>">
				</p>
			</form:form>
			<p>
				<a href="/news-admin/authors/delete/${author.id}"><button
						class="btn btn-default">
						<spring:message code="label.button.delete" />
					</button></a>
			</p>


		</div>
	</c:forEach>
</div>
<br>
<br>
<form:form commandName="author" class="form-inline"
	action="/news-admin/authors/add">
	<form:errors class="alert alert-danger" path="name" />
	<div class="form-group">
		<label for="add-author"><spring:message
				code="label.text.add_author" />:</label> <input name="name"
			value="${author.name}" type="text" class="form-control"
			id="add-author">
	</div>
	<button type="submit" class="btn btn-default">
		<spring:message code="label.button.save" />
	</button>
</form:form>



