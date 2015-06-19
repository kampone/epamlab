<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<c:import url="../common/layout.jsp"/>
<html>
<head>
    <title><fmt:message key="message.headtable.deluser" bundle="${rb}"/></title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="del_current_user">
    <table class="general_table">
        <caption>
            <fmt:message key="message.headtable.deluser" bundle="${rb}"/>
        </caption>
        <tr>
            <td>
                <fmt:message key="message.chooselogin" bundle="${rb}"/>
                <select name="del_user_login">
                    <c:forEach var="user" items="${users}">
                        <option>${user.login}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="<fmt:message key="message.button.delete" bundle="${rb}"/>"/>
            </td>
        </tr>
    </table>
</form>
<a href="../../index.jsp">
    <input type="button" id="back_button" value="<fmt:message key="message.button.back" bundle="${rb}"/>">
</a>

</body>

</html>
