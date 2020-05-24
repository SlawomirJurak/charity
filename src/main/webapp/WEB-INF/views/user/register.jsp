<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <%@include file="../jspf/head_meta.jspf"%>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/charity.css"/>"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <%@include file="../menu/menu-user.jsp" %>

        <%@include file="../menu/menu-navigation.jsp" %>
    </nav>
</header>

<c:choose>
    <c:when test="${empty user}">
        <c:set var="firstName" value=""/>
        <c:set var="lastName" value=""/>
        <c:set var="email" value=""/>
        <c:set var="title" value="Załóż konto"/>
        <c:set var="buttonCaption" value="Załóż konto"/>
    </c:when>
    <c:otherwise>
        <c:set var="firstName" value="${user.firstName}"/>
        <c:set var="lastName" value="${user.lastName}"/>
        <c:set var="email" value="${user.userName}"/>
        <c:set var="title" value="Edycja profilu"/>
        <c:set var="buttonCaption" value="Aktualizuj profil"/>
    </c:otherwise>
</c:choose>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form method="post">
        <div class="form-group">
            <input type="text" name="first-name" placeholder="Imię" id="first-name"/>
            <input type="text" name="last-name" placeholder="Nazwisko" id="last-name" class="margin-left-10"/>
            <input type="email" name="email" placeholder="Email" id="email" class="margin-left-10"/>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" id="password"/>
            <input type="password" name="password2" placeholder="Powtórz hasło" class="margin-left-10" id="password2"/>
        </div>
        <c:if test="${administrator}">
            <input type="hidden" name="administrator" id="administrator" value="administrator">
        </c:if>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit" id="btn-register">Załóż konto</button>
        </div>
    </form>
</section>

<%@include file="../footer.jsp" %>

<script src="<c:url value="/resources/js/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/popper.min.js"/>"></script>
<script src="<c:url value="/resources/js/user.js"/>"></script>

</body>
</html>
