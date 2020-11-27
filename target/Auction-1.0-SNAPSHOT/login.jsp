<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 27.11.2020
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <link href="styles/LogFormStyles.css" rel="stylesheet">
    <link href="styles/StyledFramedLink.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<div class="formContainer">
    <form action="login" method="post">
        <h2>Авторизация</h2>
        <input type="hidden" name="action" value="sign_in">
        <div class="formField inputRight">
            <label for="email">E-mail</label>
            <input type="email" name="email" id="email" placeholder="Youremail@gmail.com" value="" required>
        </div>
        <div class="formField inputRight">
            <label for="pass">Password</label>
            <input type="password" name="pass" id="pass" placeholder="password" value="" required>
        </div>
        <div class="formField inputRight submitField">
            <input type="submit" value="Войти">
        </div>
        <a href="registration.jsp" class="cl-effect-1">Регистрация</a>
    </form>
</div>
</body>
</html>
