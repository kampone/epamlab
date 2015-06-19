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
    <!-- orders это список Order, официанта-->
    <c:forEach var="order" items="${orders}">


            <tr>
                <td class="line_with_id">
                    <div>
                        №${order.id}
                    </div>
                </td>
            </tr>
            <c:forEach var="order_detail" items="${order.orderDetails}">
            <form action="controller" method="post">
            <input type="hidden" name="orderDetailId" value="${order_detail.id}"/>

                <tr>
                    <td align="center">
                        <div>${order_detail.dish.name}</div>
                        <div>${order_detail.number}</div>
                    </td>
                    <td align="center">
                        <div>${order_detail.status}</div>
                    </td>
                    <td align="center">
                        <button type="submit" name="command" value="cancel_dish"><fmt:message
                                key="message.button.canceldish"
                                bundle="${rb}"/></button>
                        </div>
                    </td>
                </tr>
                </form>
            </c:forEach>
            <form action="controller" method="post">
                <input type="hidden" name="orderId" value="${order.id}"/>
            <tr>
                <td align="center">
                    <div>
                        <button type="submit" name="command" value="edit_order"><fmt:message key="message.button.edit"
                                                                                             bundle="${rb}"/></button>

                        <button type="submit" name="command" value="order_report"><fmt:message key="message.button.pay"
                                                                                               bundle="${rb}"/></button>

                        <button type="submit" name="command" value="cancel_order"><fmt:message
                                key="message.button.cancelorder"
                                bundle="${rb}"/></button>
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
