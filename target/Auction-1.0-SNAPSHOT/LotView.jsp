<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 13.12.2020
  Time: 5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${lot.getLotName()}</title>
</head>
<body>
<form action="<c:url value="/lot/${lot.getLotId()}"/>" method="post">
    <input type="hidden" name="action" value="place_bid">
    <input type="hidden" name="lot_id" value="${lot.getLotId()}">

    <div>
    <img src="data:image/jpg;base64,${lot.getImageContent()}" alt="${lot.getImageName()}" width="500">
    </div>

    <div>
    <label><b>Название:</b> ${lot.getLotName()}</label>
    </div>

    <div>
    <label><b>Текущая ставка:</b> ${lot.getBid()}</label>
    </div>

    <div>
    <c:if test="${lot.getCondition() == 0}">
        <label><b>Состояние:</b> NEW</label>
    </c:if>
    <c:if test="${lot.getCondition() == 1}">
        <label><b>Состояние:</b> USED</label>
    </c:if>
    </div>

    <div>
    <label><b>Статус:</b> ${lot.getStatus()}</label>
    </div>

    <div>
    <label><b>Количество ставок:</b> ${lot.getBidQuantity()}</label>
    </div>

    <div>
    <label><b>Владелец:</b> ${seller.getName()}</label>
    </div>

    <div>
    <c:if test="${buyer.getEmail() != seller.getEmail()}">
        <label><b>Покупатель:</b> ${buyer.getName()}</label>
    </c:if>
    <c:if test="${buyer.getEmail() == seller.getEmail()}">
        <label><b>Покупатель:</b> - </label>
    </c:if>
    </div>

    <div>
    <label><b>Дата добавления:</b> ${lot.getStartTime()}</label>
    </div>

    <div>
    <label><b>Дата окончания:</b> ${lot.getFinishTime()}</label>
    </div>

    <div>
        <label for="bid">Новая ставка</label>
        <input type="number" name="bid" id="bid" value="${lot.getBid() + 1}">
    </div>

    <div>
        <input type="submit" value="Разместить ставку">
    </div>
</form>
</body>
</html>
