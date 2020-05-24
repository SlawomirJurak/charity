<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

        <ul class="nav--actions">
        <sec:authorize access="!isAuthenticated()">
            <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="<c:url value="/user/register"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li class="logged-user">
            Witaj <span id="logged-user-name">${loggedUserName}</span>
            <ul class="dropdown">
            <c:if test="${loggedUserType == 'admin' || loggedUserType == 'user'}">
                <li><a href="<c:url value="/user/${loggedUserId}"/>" >Profil</a></li>
                <li><a href="${pageContext.request.contextPath}/donation/${loggedUserId}">Moje zbiórki</a></li>
            </c:if>
            <c:if test="${loggedUserType=='admin' || loggedUserType=='siteAdmin'}">
                <li><a href="${pageContext.request.contextPath}/user/users">Użytkownicy</a> </li>
                <li><a href="${pageContext.request.contextPath}/user/administrators">Administratorzy</a> </li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/logout">Wyloguj</a></li>
            </ul>
            </li>
        </sec:authorize>
        </ul>
