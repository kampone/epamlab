<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.pagecontent" var="rb"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <fmt:setLocale value= "${locale}" scope="session"/>
  <jsp:forward page="/controller?command=auth"/>
    </body>
</html>
