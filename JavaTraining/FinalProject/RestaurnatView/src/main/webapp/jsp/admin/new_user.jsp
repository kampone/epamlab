<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>

<c:import url="../common/layout.jsp"/>
<html>
<head>
    <title><fmt:message key="message.headtable.newuser" bundle="${rb}"/></title>
</head>
<body>
<c:set var="pattern" value="[\S ]{4,10}"/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="new_user">
    <table class="general_table">
        <caption>
            <fmt:message key="message.headtable.newuser" bundle="${rb}"/>
        </caption>
        <tr>
            <td>
                <fmt:message key="message.login" bundle="${rb}"/>
            </td>
            <td><input type="text" name="login" required value="" pattern="${pattern}"/><h6>*<fmt:message
                    key="message.format.info" bundle="${rb}"/></h6></td>
        </tr>
        <tr>
            <td>
                <fmt:message key="message.password" bundle="${rb}"/>
            </td>
            <td><input type="password" name="password" required value="" pattern="${pattern}"/>
                <h6>*<fmt:message key="message.format.info" bundle="${rb}"/></h6>
            </td>

        </tr>
        <tr>
            <td>
                <fmt:message key="message.confirmpassword" bundle="${rb}"/>
            </td>
            <td><input type="password" name="confirm_password" required value="" pattern="${pattern}"/>
                <h6>*<fmt:message key="message.format.info" bundle="${rb}"/></h6>
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="message.chooserole" bundle="${rb}"/>
            </td>
            <td>
                <select name="user_role">
                    <option value="1"><fmt:message key="message.userrole.admin" bundle="${rb}"/></option>
                    <option value="2"><fmt:message key="message.userrole.waiter" bundle="${rb}"/></option>
                    <option value="3"><fmt:message key="message.userrole.cook" bundle="${rb}"/></option>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="<fmt:message key="message.button.create" bundle="${rb}"/>"/></td>
            <td>${errorMessage}</td>
        </tr>
    </table>
</form>
<a href="../../index.jsp">
    <input type="button" id="back_button" value="<fmt:message key="message.button.back" bundle="${rb}"/>">
</a>

</body>
</html>
