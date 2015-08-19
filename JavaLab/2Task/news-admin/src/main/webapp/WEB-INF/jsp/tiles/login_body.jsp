<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:if test="${errorMessage!=null}">
	<div align="left" class="alert alert-danger	">
		<strong><c:out value="${errorMessage}" /></strong>
	</div>
</c:if>
<form method="post" style="margin-bottom: 0em;"
	action="<c:url value='/news-admin/j_spring_security_check' />">
	<table class="login-form">
		<tr>
			<td colspan="2" style="color: red">${message}</td>
		</tr>
		<tr>
			<td><spring:message code="label.text.username" /></td>
			<td><input type="text" name="username" /></td>
		</tr>
		<tr>
			<td><spring:message code="label.text.password" /></td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit"
				value="<spring:message code="label.button.login"/>" /></td>
		</tr>
	</table>
</form>
