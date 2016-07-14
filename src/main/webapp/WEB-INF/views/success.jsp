<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <spring:url value="/resources/js/jquery-2.2.4.min.js" var="jqueryJs"/>
    <spring:url value="resources/css/bootstrap.min.css" var="bootstrapcss"/>
    <spring:url value="resources/js/bootstrap.min.js" var="bootstrapjs"/>
    <script src="${jqueryJs}"></script>
    <link href="${bootstrapcss}" rel="stylesheet"/>
    <script src=${bootstrapjs}></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Add Article - Spring Hibernate Assignment</title>
</head>
<body>
<div class="container">
    <center>
        <h2>${success}</h2>
        <h4>
            Вернуться на <a href="<c:url value='/main' />">главную страницу</a>
        </h4>
    </center>
</div>
</body>

</html>