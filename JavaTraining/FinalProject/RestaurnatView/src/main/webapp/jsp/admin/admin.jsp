
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>

<c:import url="../common/menu.jsp"/>
<html>
<head>
    <title><fmt:message key="message.headtable.adminmenu" bundle="${rb}"/>A</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<table class="general_table">
    <caption>
        <fmt:message key="message.headtable.adminmenu" bundle="${rb}"/>
    </caption>
    <tr>
        <td><fmt:message key="message.welcome" bundle="${rb}"/>, ${user}</td>
    </tr>
    <tr>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="admin_orders">
            <td align="center"><input type="submit" value="<fmt:message key="message.menu.confirmeorders" bundle="${rb}"/>"/></td>
        </form>
    </tr>
    <tr>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="create_user">
            <td align="center"><input type="submit" value="<fmt:message key="message.menu.newuser" bundle="${rb}"/>"/></td>
        </form>
    </tr>
    <tr>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="del_user">
            <td align="center"><input type="submit" value="<fmt:message key="message.menu.deluser" bundle="${rb}"/>"/></td>
        </form>
    </tr>
    <tr>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="dishmenu">
            <td align="center"><input type="submit" value="<fmt:message key="message.menu.dishmenu" bundle="${rb}"/>"/></td>
        </form>
    </tr>
</table>
<div class="info_message">
    ${infoMessage}
</div>
</body>
</html>
