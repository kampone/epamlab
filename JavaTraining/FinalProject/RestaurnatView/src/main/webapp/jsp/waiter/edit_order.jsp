<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../common/menu.jsp"/>
<html>
<head>
    <title><fmt:message key="message.edit" bundle="${rb}"/> </title>
</head>
<body>

<form action="controller" method="post">
    <input type="hidden" name="order" value="${order}">
    <table class="minor_table">
        <c:forEach var="detail" items="${order.orderDetails}">
            <tr>
                <td>${detail.dish.name}</td>
                <td>${detail.number}</td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <input type="hidden" name="command" value="ready_edit_order">
                <input type="submit" value="<fmt:message key="message.button.ready" bundle="${rb}"/>"/>

            </td>
        </tr>
    </table>
</form>
<table class="general_table">

    <c:forEach var="dish" items="${dishes}">
        <tr>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="add_dish">
                <input type="hidden" name="dish_id" value="${dish.id}">

                <td>${dish.name}</td>
                <td>
                    <input type="submit" value="<fmt:message key="message.button.choose" bundle="${rb}"/>"/>
                </td>
            </form>

        </tr>
    </c:forEach>

</table>
<a href="../../index.jsp">
    <input type="button" id="back_button" value="<fmt:message key="message.button.back" bundle="${rb}"/>">
</a>
</body>
</html>
