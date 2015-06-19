<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<c:import url="../common/menu.jsp"/>
<html>
<head>
    <title><fmt:message key="message.error.message" bundle="${rb}"/> ${pageContext.errorData.statusCode}</title>
</head>
<body>
<table class="general_table">
    <tr>
        <td class="errormessage"><fmt:message key="message.error.message" bundle="${rb}"/> ${pageContext.errorData.statusCode}</td>
    </tr>
    <tr>
        <td class="wrong"><fmt:message key="message.error.exception" bundle="${rb}"/></td>
    </tr>
    <tr>
        <td>${pageContext.errorData.throwable}</td>
    </tr>
</table>
<a href="../../index.jsp">
    <input type="button" id="back_button" value="<fmt:message key="message.button.back" bundle="${rb}"/>">
</a>
</body>
</html>
