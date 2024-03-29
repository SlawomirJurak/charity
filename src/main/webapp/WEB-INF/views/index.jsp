<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <%@include file="jspf/head_meta.jspf"%>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>

<header class="header--main-page">
    <nav class="container container--70">

        <%@include file="menu/menu-user.jsp" %>

        <%@include file="menu/menu-navigation.jsp" %>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>

<section class="stats">
    <h2>Trochę statystyki</h2>
    <div class="container container--85">
        <div class="stats--item">
            <em>${sumQuantity}</em>
            <h3>Oddanych worków</h3>
            <p></p>
        </div>

        <div class="stats--item">
            <em>${donationsCount}</em>
            <h3>Przekazanych darów</h3>
            <p></p>
        </div>

    </div>
</section>

<section class="steps" id="steps">
    <h2>Wystarczą 4 proste kroki</h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3>Wybierz rzeczy</h3>
            <p>ubrania, zabawki, sprzęt i inne</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3>Spakuj je</h3>
            <p>skorzystaj z worków na śmieci</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3>Zdecyduj komu chcesz pomóc</h3>
            <p>wybierz zaufane miejsce</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3>Zamów kuriera</h3>
            <p>kurier przyjedzie w dogodnym terminie</p>
        </div>
    </div>

    <a href="<c:url value="/user/register"/>" class="btn btn--large">Załóż konto</a>
</section>

<section class="about-us" id="about-us">
    <div class="about-us--text">
        <h2>O nas</h2>
        <p>Pomagamy potrzebującym</p>
        <img src="<c:url value="resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="resources/images/about-us.jpg"/>" alt="People in circle"/>
    </div>
</section>

<section class="help" id="help">
    <h2>Komu pomagamy?</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.
            Możesz sprawdzić czym się zajmują.</p>

        <ul class="help--slides-items">
            <c:forEach items="${allInstitution}" var="institution" varStatus="counter">
                <c:if test="${counter.index mod 2 == 0}">
                    <li>
                </c:if>
                <div class="col">
                    <div class="title">Fundacja "${institution.name}"</div>
                    <div class="subtitle">Cel i misja: ${institution.description}.</div>
                </div>
                <c:if test="${counter.index mod 2 == 1 or counter.last}">
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</section>

<%@include file="footer.jsp"%>

<script src="<c:url value="resources/js/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
