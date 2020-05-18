<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <%@include file="menu/menu-user.jsp" %>

        <%@include file="menu/menu-navigation.jsp" %>
    </nav>

<%--    <div class="slogan container container--90">--%>
<%--        <div class="slogan--item">--%>
<%--            <h2>Wylogowanie z systemu.</h2>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="slogan container container--90">--%>
<%--        <div class="slogan--item">--%>
<%--            <form action="<c:url value="/logout"/>" method="post">--%>
<%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--                <button class="btn" type="submit">Wyloguj się</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
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

<%--<%@include file="footer.jsp" %>--%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
