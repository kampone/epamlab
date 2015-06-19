
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>

<c:import url="../common/layout.jsp"/>
<html>
<head>
</head>
<body>
<table align="right">
    <tr>
        <td>
            <a href="controller?command=logout">
                <input type="button" value="<fmt:message key="message.button.logout"  bundle="${rb}"/>">
            </a>
        </td>
    </tr>
</table>
</body>
</html>
