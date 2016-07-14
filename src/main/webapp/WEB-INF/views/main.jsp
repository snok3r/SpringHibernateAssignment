<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <spring:url value="/resources/js/jquery-2.2.4.min.js" var="jqueryJs"/>
    <spring:url value="resources/css/bootstrap.min.css" var="bootstrapcss"/>
    <spring:url value="resources/js/bootstrap.min.js" var="bootstrapjs"/>
    <script src="${jqueryJs}"></script>
    <link href="${bootstrapcss}" rel="stylesheet"/>
    <script src=${bootstrapjs}></script>

    <title>Main - Spring Hibernate Assignment</title>
</head>


<body>
<div class="container">
    <h2>List of Articles</h2>

    <c:forEach items="${articles}" var="article">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">${article.title}</h3>
            </div>
            <div class="panel-body">
                    ${article.content}
            </div>
            <div class="panel-footer">
                    ${article.publicationDate}, ${article.category},
                <a href="<c:url value='/edit-${article.id}-article' />">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true" aria-label="edit"></span>
                </a>,
                <a href="<c:url value='/delete-${article.id}-article' />">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" aria-label="delete"></span>
                </a>
            </div>
        </div>
    </c:forEach>
    <br/>
    <a href="<c:url value='/add' />">Add New Article</a>
</div>
</body>
</html>