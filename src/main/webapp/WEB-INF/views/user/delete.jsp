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
<header>
    <nav class="container container--70">
        <%@include file="../menu/menu-user.jsp" %>

        <%@include file="../menu/menu-navigation.jsp" %>
    </nav>
</header>

<section class="login-page">
    <h2>Czy usunąć użytkownika ${user.firstName} ${user.lastName}</h2>
    <form method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Usuń</button>
            <c:choose>
                <c:when test="${user.administrator}">
                    <a href="/user/administrators" class="btn btn--without-border">Powrót</a>
                </c:when>
                <c:otherwise>
                    <a href="/user/users" class="btn btn--without-border">Powrót</a>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
</section>

<%@include file="../footer.jsp" %>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
