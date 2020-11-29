<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 29.11.2020
  Time: 2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <link href="<c:url value="/styles/GameInfoStyle.css"/>" rel="stylesheet">
    <link href="<c:url value="/styles/MainNav.css"/>" rel="stylesheet">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8">
    <title>Главная</title>
</head>
<body>
<div class="mainContainer">
    <header class="midStyle header">
        <a href="#">
            <div class="logoNav"></div>
        </a>
        <nav>
            <ul class="topmenu">
                <li><a href="<c:url value="/main"/>">Главная</a></li>
                <li><a href="<c:url value="/main"/>">Магазин</a></li>
                <li><a href="<c:url value="/main"/>">Блог</a>
                <c:if test="${user == null}">
                    <li><a href="<c:url value="/login"/>">Регистрация</a></li>
                </c:if>
                <c:if test="${user != null}">
                    <li><a href="" class="down">${user.getName()}</a>
                        <ul class="submenu">
                            <li><a href="">Профиль</a></li>
                            <li><a href="">Слоты</a></li>
                            <li><a href="">Archive</a></li>
                            <li><a href="">Выйти</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </nav>
    </header>
    <div class="midContainer">
        <div class="newsContainer">
            <div class="ImgTitleBLock"><img src="<c:url value="/Pictures/game.png"/>" alt="E3"></div>
            <div class="mainParagraph">
                <h2>Создание нового лота</h2>
            </div>
            <form action="login" method="post" enctype="multipart/form-data">
                <div>
                    <label for="file">Картинка</label>
                    <input type="file" accept="image/*" name="file" id="file">
                </div>
                <div>
                    <input type="submit" value="Войти">
                </div>
            </form>
        </div>
        <div class="sidebarContainer">
            <div class="sidebarTitle"><h2>Активные лоты</h2></div>
            <div class="bestListBar">
                <ol class="bestList">
                    <li>Super Mario Galaxy 2</li>
                    <li>The Legend of Zelda: Breath of the Wild</li>
                    <li>Red Dead Redemption 2</li>
                    <li>Grand Theft Auto V</li>
                    <li>Super Mario Odyssey</li>
                    <li>Mass Effect 2</li>
                    <li>The Elder Scrolls V: Skyrim</li>
                    <li>The Last of Us </li>
                    <li>God of War</li>
                    <li>Batman: Arkham City</li>
                </ol>
            </div>
        </div>
    </div>
    <jsp:include page="../WEB-INF/general-content/footer.html" />
</div>
</body>
</html>
