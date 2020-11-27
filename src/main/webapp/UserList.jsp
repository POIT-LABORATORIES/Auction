<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 23.11.2020
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="user" items="${users}">
        <ul>
            <li><c:out value="${user.getName()}" /></li>
            <li><c:out value="${user.getEmail()}" /></li>
            <li><c:out value="${user.getPassword()}" /></li>
        </ul>
    </c:forEach>
</ul>
</body>
</html>
