<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 28.11.2020
  Time: 1:09
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
    <div class="midStyle"><h1>Новости</h1></div>
    <div class="midContainer">
        <div class="newsContainer">
            <div class="newsBlock">
                <a href="HTML/FFVII.html">
                    <div class="newsImgBlock"><img src="Pictures/FFB.jpg" alt="Final Fantasy VII"></div>
                    <div class="newsTitleBlock"><h3>Всё, что нужно знать о Final Fantasy VII Remake</h3></div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/silent_hill.html">
                    <div class="newsImgBlock"><img src="Pictures/silenthill.jpg" alt="Silent Hill"></div>
                    <div class="newsTitleBlock"><h3>СМИ: Sony работает над перезапуском Silent Hill и недовольна Death Stranding</h3></div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="Pictures/gdc2020.jpg" alt="The Last of Us"></div>
                    <div class="newsTitleBlock"><h3>Организаторы GDC 2020 опубликовали расписание презентаций</h3></div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/E3_cancelled.html">
                    <div class="newsImgBlock"><img src="Pictures/e3.jpg" alt="The Last of Us"></div>
                    <div class="newsTitleBlock"><h3>Официально: E3 2020 отменили</h3></div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="Pictures/witcher.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>Нет, CD Projekt не заявила, что следующей игрой после Cyberpunk 2077 будет новый «Ведьмак»</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="Pictures/cp2077fm.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>К 8 марта создатели Cyberpunk 2077 показали постер с женской версией Ви</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="Pictures/codw.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>Вышла Call of Duty: Warzone — бесплатная королевская битва на 150 человек</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="Pictures/DOOMEternal.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>Bethesda обновила системные требования DOOM Eternal</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="Pictures/RE3.jpg" alt="RE:3"></div>
                    <div class="newsTitleBlock">
                        <h3>В российской рознице подорожают ремейки Resident Evil 3 и Final Fantasy VII</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="Pictures/TLOU2.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>Сегодня должна была выйти The Last of Us: Part II</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
        </div>
        <div class="sidebarContainer">
            <div class="sidebarTitle"><h2>История</h2></div>
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
