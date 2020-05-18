<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
            <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
                <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            </sec:authorize>
            <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
