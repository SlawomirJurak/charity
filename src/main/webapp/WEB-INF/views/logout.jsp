<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <%@include file="jspf/head_meta.jspf"%>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <%@include file="menu/menu-user.jsp" %>

        <%@include file="menu/menu-navigation.jsp" %>
    </nav>
    <section class="login-page">
        <h2>Wylogowanie z systemu</h2>
        <form action="<c:url value="/logout"/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group form-group--buttons">
                <button class="btn" type="submit">Wyloguj się</button>
            </div>
        </form>
    </section>
</header>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
