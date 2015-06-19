<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>

<c:import url="layout.jsp"/>
<html>
<head>
    <link rel="stylesheet" href="../../css/style.css">
    <title><fmt:message key="message.login" bundle="${rb}"/></title>
</head>
<body>
<c:set var="pattern" value="[\S]{4,10}"/>

<form action="controller" method="post">
    <input type="hidden" name="command" value="login"/>
    <table cellpadding="0" class="general_table">
        <tr>
            <td><fmt:message key="message.login" bundle="${rb}"/>:</td>
            <td><input type="text" name="login" required value="" pattern="${pattern}"/> <h6>*<fmt:message key="message.format.info" bundle="${rb}"/></h6></td>
        </tr>
        <tr>
            <td><fmt:message key="message.password" bundle="${rb}"/>:</td>
            <td><input type="password" name="password" required value="" pattern="${pattern}"/><h6>*<fmt:message key="message.format.info" bundle="${rb}"/></h6></td>
        </tr>
        <tr>
            <td><input id="button" type="submit" value="<fmt:message key="message.button.login" bundle="${rb}"/>"/></td>
            <td class="wrong">
                ${errorLoginPassMessage}
            </td>
        </tr>
    </table>
    <script src="/js/test.js "></script>


</form>
</body>
</html>