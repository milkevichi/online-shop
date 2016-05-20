<%--
  Created by IntelliJ IDEA.
  User: imilkevich
  Date: 15.05.2016
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <spring:url value="/resources/css/jquery-ui.min.css" var="jqUiCss"/>
    <spring:url value="/resources/javascript/jquery-ui.min.js" var="jqUiJs"/>
    <spring:url value="/resources/css/jquery-ui.css" var="jqUiCss"/>

    <%--superfish nav bar--%>
    <spring:url value="/resources/javascript/jquery.js" var="jqJs"/>
    <spring:url value="/resources/javascript/hoverIntent.js" var="hoverIntentJs"/>
    <spring:url value="/resources/javascript/superfish.js" var="superFishJs"/>
    <spring:url value="/resources/css/megafish/superfish.css" var="superfishCss"/>
    <spring:url value="/resources/css/megafish/megafish.css" var="megafishCss"/>
    <spring:url value="/resources/css/megafish/superfish-navbar.css" var="superfishNavbarCss"/>
    <spring:url value="/resources/css/megafish/superfish-vertical.css" var="superfishVerticalCss"/>

    <%--bootstrap--%>
    <spring:url value="/resources/javascript/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/resources/css/bootstrap/bootstrap.min.css" var="bootstrapMinCss"/>

    <link rel="stylesheet" href="${jqUiCss}" media="screen"/>
    <link rel="stylesheet" href="${superfishCss}" media="screen"/>
    <link rel="stylesheet" href="${megafishCss}" media="screen"/>
    <link rel="stylesheet" href="${superfishNavbarCss}" media="screen"/>
    <link rel="stylesheet" href="${superfishVerticalCss}" media="screen"/>
    <link rel="stylesheet" href="${bootstrapMinCss}" media="screen">

    <script src="${jqJs}"></script>
    <script src="${jqUiJs}"></script>
    <script src="${hoverIntentJs}"></script>
    <script src="${superFishJs}"></script>
    <script src="${bootstrapJs}"></script>
</head>
<body>

</body>
</html>


