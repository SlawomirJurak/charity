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
    <link rel="stylesheet" href="<c:url value="/resources/css/users.css"/>"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <%@include file="../menu/menu-user.jsp" %>

        <%@include file="../menu/menu-navigation.jsp" %>
    </nav>

</header>
<div class="users">
    <h2>${userListTitle}</h2>
    <c:if test="${administrators}">
        <a href="${pageContext.request.contextPath}/user/administrator/new">
            <button class="btn btn-new-admin">Dodaj administratora</button>
        </a>
    </c:if>
    <div class="users-container users-container-head">
        <div class="users-item">Imię</div>
        <div class="users-item">Nazwisko</div>
        <div class="users-item">Email</div>
        <div class="users-item">Aktywny</div>
        <div class="users-item">Akcje</div>
    </div>
    <c:forEach items="${userAll}" var="user">
        <div class="users-container" data-id="${user.id}">
            <div class="users-item">${user.firstName}</div>
            <div class="users-item">${user.lastName}</div>
            <div class="users-item">${user.userName}</div>
            <div class="users-item active-user">${user.active}</div>
            <div class="users-item">
                <c:if test="${not (user.deleted || user.id==loggedUserId)}">
                    <c:choose>
                        <c:when test="${user.active}">
                            <div class="user-action btn-deactivate">Deaktywuj</div>
                        </c:when>
                        <c:otherwise>
                            <div class="user-action btn-activate">Aktywuj</div>
                        </c:otherwise>
                    </c:choose>
                    <div class="user-action"><a href="<c:url value="/user/delete/${user.id}"/>" class="user-action">Usuń</a></div>
                </c:if>
            </div>
        </div>
    </c:forEach>
</div>

<script src="<c:url value="/resources/js/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/user.js"/>"></script>

</body>
</html>
