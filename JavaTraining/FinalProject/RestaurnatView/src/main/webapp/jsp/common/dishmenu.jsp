<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>

<c:import url="../common/menu.jsp"/>
<html>
<head>
    <link rel="stylesheet" href="../../css/style.css">

</head>
<body>
<table cellspacing="0" class="general_table">
    <caption><fmt:message key="message.headtable.dishmenu" bundle="${rb}"/></caption>
    <tr align="center">
        <td><fmt:message key="message.menu.name" bundle="${rb}"/></td>
        <td><fmt:message key="message.menu.description" bundle="${rb}"/></td>
        <td><fmt:message key="message.menu.price" bundle="${rb}"/></td>
    </tr>
    <c:forEach var="dish" items="${menu}">
        <tr>
            <td>${dish.name}</td>
            <td>${dish.description}</td>
            <td id="price">${dish.price}$</td>
        </tr>
    </c:forEach>
</table>

<a href="../../index.jsp">
    <input type="submit" id="back_button" value="<fmt:message key="message.button.back" bundle="${rb}"/>">
</a>
</body>
</html>
