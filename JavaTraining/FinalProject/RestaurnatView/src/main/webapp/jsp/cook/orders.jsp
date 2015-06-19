
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<c:import url="../common/menu.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="message.headtable.orders" bundle="${rb}"/></title>
</head>
<body>
<table class="general_table">
    <caption><fmt:message key="message.headtable.orders" bundle="${rb}"/></caption>
    <!-- orders это список Order, где лист OrderDetails состоит из элементов со статусом утвержден-->
    <c:forEach var="order" items="${orders}">

        <tr>
            <td class="line_with_id">
                <div >
                    №${order.id}
                </div>
            </td>
        </tr>
        <c:forEach var="order_detail" items="${order.orderDetails}">
            <form action="controller" method="post">
                <input type="hidden" name="idOrderDetail" value="${order_detail.id}">
                <input type="hidden" name="command" value="dish_ready">
                <tr>
                    <td align="center">
                        <div>${order_detail.dish.name}</div>
                        <div>${order_detail.number}</div>
                    </td>
                    <td align="center">
                        <input type="submit" value="<fmt:message key="message.button.ready" bundle="${rb}"/>"/>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </c:forEach>
</table>
<a href="../../index.jsp">
    <input type="button" id="back_button" value="<fmt:message key="message.button.back" bundle="${rb}"/>">
</a>
</body>
</html>
