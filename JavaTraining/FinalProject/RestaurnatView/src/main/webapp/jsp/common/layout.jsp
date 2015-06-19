
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctg" uri="customtags" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>

<html>
<head>
    <link rel="stylesheet" href="../../css/style.css">
    <title>Restaurant Helper</title>
</head>
<body>
<%@include file="../../jspf/header.jspf"%>
<form id="change_language" action="controller" method="post">
    <input type="hidden" name="command" value="change_language">
    <select name="locale_select">
        <option value="ru_RU">Русский</option>
        <option value="en_US" selected>English</option>
    </select>
    <input type="submit" id="change_language_button" value="<fmt:message key="message.button.choose" bundle="${rb}"/>"/>
</form>
<div align="right">
<ctg:userinfo/>
</div>


<%@include file="../../jspf/footer.jspf"%>

</body>
</html>
