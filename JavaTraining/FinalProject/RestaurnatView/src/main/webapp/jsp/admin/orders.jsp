<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../common/menu.jsp"/>
<html>
<head>
    <title><fmt:message key="message.headtable.orders" bundle="${rb}"/></title>
</head>
<body>
<table class="general_table">
    <caption><fmt:message key="message.headtable.orders" bundle="${rb}"/></caption>
    <!-- orders это список Order, со статусом "Не подтвержден"-->
    <c:forEach var="order" items="${orders}">
        <form action="controller" method="post">
            <input type="hidden" name="idOrder" value="${order.id}">
            <tr>
                <td class="line_with_id">
                    <div>
                        №${order.id}
                    </div>
                </td>
            </tr>
            <c:forEach var="order_detail" items="${order.orderDetails}">

            <tr>
                <td align="center">
                    <div>${order_detail.dish.name}</div>
                    <div>${order_detail.number}</div>
                </td>
                </c:forEach>
                <td align="center">
                    <div>
                        <button type="submit" name="command"
                               value="confirm_order"><fmt:message key="message.button.confirmorder" bundle="${rb}"/></button>
                    </div>
                    <div>
                        <button type="submit" name="command"
                               value="not_confirm_order"><fmt:message key="message.button.cancelorder" bundle="${rb}"/></button>
                    </div>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<a href="../../index.jsp">
    <input type="button" id="back_button" value="<fmt:message key="message.button.back" bundle="${rb}"/>">
</a>
</body>
</html>
