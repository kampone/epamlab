<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../common/menu.jsp"/>
<html>
<head>
    <title><fmt:message key="message.report" bundle="${rb}"/></title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="pay_order">
    <input type="hidden" name="idOrder" value="${order.id}">
    <table class="general_table">
        <caption></caption>
        <tr>
            <td><fmt:message key="message.order.idorder" bundle="${rb}"/>: ${order.id}</td>
            <td></td>
        </tr>
        <c:forEach var="order_detail" items="${order.orderDetails}">
            <tr>
                <td>${order_detail.dish.name} (${order_detail.number})</td>
                <td>${order_detail.dish.price}$</td>
            </tr>
        </c:forEach>
        <tr>
            <td><fmt:message key="message.order.total" bundle="${rb}"/></td>

            <td>${sum}$</td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="<fmt:message key="message.button.pay" bundle="${rb}"/>"/></td>
        </tr>
    </table>
</form>
<a href="../../index.jsp">
    <input type="button" id="back_button" value="<fmt:message key="message.button.back" bundle="${rb}"/>">
</a>
</body>
</html>
