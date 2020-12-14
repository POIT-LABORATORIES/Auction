<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 29.11.2020
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="<c:url value="/styles/LotListForm.css"/>" rel="stylesheet">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>User lots</title>
</head>
<body>
<h2 class="horizontal-centered-text">Лоты</h2>
<div class="main">
    <table class="table_col">
        <tr>
            <th>Название</th>
            <th>Цена</th>
            <th>Состояние</th>
            <th>Статус</th>
            <th>Дата добавления</th>
            <th>Дата окончания</th>
        </tr>
        <c:forEach var="lot" items="${lots}">
            <tr>
                <td>
                    <a class="lotTitle" href="<c:url value="/profile/lots/view/${lot.getLotId()}"/>">
                            ${lot.getLotName()}
                    </a>
                </td>
                <td>${lot.getBid()}</td>
                <td>
                    <c:if test="${lot.getCondition() == 0}">
                        NEW
                    </c:if>
                    <c:if test="${lot.getCondition() == 1}">
                        USED
                    </c:if>
                </td>
                <td>${lot.getStatus()}</td>
                <td>${lot.getStartTime()}</td>
                <td>${lot.getFinishTime()}</td>
                <td><a href="<c:url value="/profile/lots/update/${lot.getLotId()}"/>">Изменить</a></td>
                <td><a href="<c:url value="/profile/lots/remove/${lot.getLotId()}"/>">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
