<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 29.11.2020
  Time: 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value="/AddLot"/>" method="post" enctype="multipart/form-data">
    <div>
        <label for="lot_name">Название/Title</label>
        <input type="text" name="lot_name" id="lot_name" value="" required>
    </div>
    <div>
        <label for="bid">Начальная ставка/Starting bid</label>
        <input type="number" name="bid" id="bid" value="0" required>
    </div>
    <div>
        <label>Состояние/Condition</label>
        <p>Новый/New</p>
        <input type="radio" name="condition" value="New" checked>
        <p>Использованный/Used</p>
        <input type="radio" name="condition" value="Used">
    </div>
    <div>
        <label for="finish_time">Окончание/Finish</label>
        <input type="datetime-local" name="finish_time" id="finish_time" required>
    </div>
    <div>
        <label for="file">Картинка/Image</label>
        <input type="file" accept="image/*" name="img_file" id="file" value="" required>
    </div>
    <div>
        <input type="submit" value="Создать">
    </div>
</form>
</body>
</html>
