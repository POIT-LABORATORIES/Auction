<%--
  Created by IntelliJ IDEA.
  User: Kira
  Date: 09.11.2020
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="styles/MainStyles.css" rel="stylesheet">
    <title>Main</title>
</head>
<body>
<div class="mainContainer">
    <header class="midStyle header">
        <a href="#"><div class="logoNav"></div></a>
        <nav class="menu">
            <ul>
                <li><a href="main.jsp">Новости</a></li>
                <li><a href="main.jsp">Технологии</a></li>
                <li><a href="main.jsp">Индустрия</a></li>
                <li><a href="login">Регистрация</a></li>
            </ul>
        </nav>
    </header>
    <div class="midStyle"><h1>Новости</h1></div>
    <div class="midContainer">
        <div class="newsContainer">
            <div class="newsBlock">
                <a href="HTML/FFVII.html">
                    <div class="newsImgBlock"><img src="pictures/FFB.jpg"></div>
                    <div class="newsTitleBlock"><h3>Всё, что нужно знать о Final Fantasy VII Remake</h3></div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/silent_hill.html">
                    <div class="newsImgBlock"><img src="pictures/silenthill.jpg" alt="Silent Hill"></div>
                    <div class="newsTitleBlock"><h3>СМИ: Sony работает над перезапуском Silent Hill и недовольна Death Stranding</h3></div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="pictures/gdc2020.jpg" alt="The Last of Us"></div>
                    <div class="newsTitleBlock"><h3>Организаторы GDC 2020 опубликовали расписание презентаций</h3></div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/E3_cancelled.html">
                    <div class="newsImgBlock"><img src="pictures/e3.jpg" alt="The Last of Us"></div>
                    <div class="newsTitleBlock"><h3>Официально: E3 2020 отменили</h3></div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="pictures/witcher.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>Нет, CD Projekt не заявила, что следующей игрой после Cyberpunk 2077 будет новый «Ведьмак»</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="pictures/cp2077fm.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>К 8 марта создатели Cyberpunk 2077 показали постер с женской версией Ви</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="pictures/codw.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>Вышла Call of Duty: Warzone — бесплатная королевская битва на 150 человек</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="pictures/DOOMEternal.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>Bethesda обновила системные требования DOOM Eternal</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="pictures/RE3.jpg" alt="RE:3"></div>
                    <div class="newsTitleBlock">
                        <h3>В российской рознице подорожают ремейки Resident Evil 3 и Final Fantasy VII</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
            <div class="newsBlock">
                <a href="HTML/Empty.html">
                    <div class="newsImgBlock"><img src="pictures/TLOU2.jpg" alt="Цитадель, HF:Alyx"></div>
                    <div class="newsTitleBlock">
                        <h3>Сегодня должна была выйти The Last of Us: Part II</h3>
                    </div>
                </a>
                <div class="newsBlockBottom"><p>07.03.2020</p></div>
            </div>
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
</div>
</body>
</html>
