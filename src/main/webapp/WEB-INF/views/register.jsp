<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>" />
    <link rel="stylesheet" href="<c:url value="resources/css/charity.css"/>" />
</head>
<body>
<header>
    <nav class="container container--70">
        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form method="post">
        <div class="form-group">
            <input type="text" name="first-name" placeholder="Imię" id="first-name">
            <input type="text" name="last-name" placeholder="Nazwisko" id="last-name" class="margin-left-10">
            <input type="email" name="email" placeholder="Email" id="email" class="margin-left-10"/>
        </div>

        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" id="password"/>
            <input type="password" name="password2" placeholder="Powtórz hasło" class="margin-left-10" id="password2"/>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit" id="btn-register">Załóż konto</button>
        </div>
    </form>
</section>

<%@include file="footer.jsp"%>

<script src="<c:url value="resources/js/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="resources/js/register.js"/>"></script>

</body>
</html>
