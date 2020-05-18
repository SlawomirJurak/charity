<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="csrf-token" content="${_csrf.token}">
    <title>Document</title>
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

<section class="login-page">
    <h2>Załóż konto</h2>
    <form>
        <input type="hidden" value="${user.id}" id="user-id">
        <div class="form-group">
            <input type="text" name="first-name" placeholder="Imię" id="first-name" value="${user.firstName}"/>
            <input type="text" name="last-name" placeholder="Nazwisko" id="last-name" class="margin-left-10" value="${user.lastName}"/>
            <input type="email" name="email" placeholder="Email" id="email" class="margin-left-10" value="${user.userName}" disabled/>
            <button class="btn--small" id="btn-update">Zapisz</button>
        </div>
        <div class="form-group">
            <input type="password" name="current-password" placeholder="Obecne hasło" id="current-password"/>
            <input type="password" name="password" placeholder="Nowe hasło" id="new-password"/>
            <input type="password" name="password2" placeholder="Powtórz hasło" class="margin-left-10" id="new-password2"/>
            <button class="btn--small" id="btn-change-password">Zmień hasło</button>
        </div>
    </form>
</section>

<%@include file="../footer.jsp" %>

<script src="<c:url value="/resources/js/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/popper.min.js"/>"></script>
<script src="<c:url value="/resources/js/user.js"/>"></script>

</body>
</html>
