<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 09.11.2020
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="<c:url value="/styles/MainStyles.css"/>" rel="stylesheet">
    <link href="<c:url value="/styles/MainNav.css"/>" rel="stylesheet">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Main</title>
</head>
<body>
<div class="mainContainer">
    <header class="midStyle header">
        <a href="#"><div class="logoNav"></div></a>
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
        <!--
        <nav class="menu">
            <ul>
                <li><a href="<c:url value="/main"/>">Новости</a></li>
                <li><a href="<c:url value="/main"/>">Технологии</a></li>
                <li><a href="<c:url value="/main"/>">Индустрия</a></li>
                <li><a href="<c:url value="/login"/>">Регистрация</a></li>
                <li><a href="#">${user.getName()}</a></li>
            </ul>
        </nav>
        -->
    </header>
    <!--
    <div class="midStyle"><h1>Новости</h1></div>
    -->
    <div class="midStyle"><h1>${sess}</h1></div>
    <div class="midContainer">
        <div class="newsContainer">
            <c:forEach var="lot" items="${lots}">
                <div class="newsBlock">
                    <a href="${lot.getLotId()}">
                        <div class="newsImgBlock">
                            <img src="data:image/jpg;base64,${lot.getImageContent()}" alt="${lot.getImageName()}">
                        </div>
                        <div class="newsTitleBlock"><h3>${lot.getLotName()}</h3></div>
                        <div class="newsPriceBlock"><h2>US $${lot.getBid()}</h2></div>
                    </a>
                    <div class="newsBlockBottom"><p>${lot.getFinishTime()}</p></div>
                </div>
            </c:forEach>
        </div>
        <div class="sidebarContainer">
            <div class="sidebarTitle"><h2>Лучшие игры декады</h2></div>
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
    <jsp:include page="WEB-INF/general-content/footer.html" />
    <!--
    <footer class="midStyle footerContainer">
        <div class="footerText">
            <p>Ждете Cyberpunk 2077 или PlayStation 5? Интересуетесь новой Call of Duty
                или ремейком Resident Evil? Ищите альтернативу Civiliztion или Warcraft?
                Смотрите “Ведьмака”, обожаете “Звездные войны”? Обсуждаете теории “Мстители:
                Война бесконечности” и мечтаете о новой “Дюне”? Подбираете новый процессор или
                видеокарту? Интересуетесь черными дырами, достижениями в медицине, SpaceX,
                Tesla и другими проектами Илона Маска?</p>
        </div>
    </footer>
    -->
</div>
</body>
</html>
