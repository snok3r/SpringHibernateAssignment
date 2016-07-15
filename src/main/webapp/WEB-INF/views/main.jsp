<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <spring:url value="/resources/js/jquery-2.2.4.min.js" var="jqueryJs"/>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss"/>
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs"/>
    <script src="${jqueryJs}"></script>
    <link href="${bootstrapcss}" rel="stylesheet"/>
    <script src=${bootstrapjs}></script>

    <title>Main - Spring Hibernate Assignment</title>
</head>


<body>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Навигация</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value='/main' />"><span
                        class="glyphicon glyphicon-home"></span></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<c:url value='/main' />">Домой<span class="sr-only">(текущая)</span></a>
                    </li>
                    <li><a href="<c:url value='/add' />">Добавить новость</a></li>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Название или содержание">
                    </div>
                    <button type="submit" class="btn btn-default">Поиск</button>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <c:if test="${not empty message}">
        <c:choose>
            <c:when test="${delete}">
                <c:set var="clazz" scope="session" value="warning"/>
            </c:when>
            <c:otherwise>
                <c:set var="clazz" scope="session" value="success"/>
            </c:otherwise>
        </c:choose>
        <div class="alert alert-${clazz}">
                ${message}
        </div>
    </c:if>

    <c:choose>
        <c:when test="${not empty articles}">
            <c:forEach items="${articles}" var="article">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">${article.title}
                            <div class="panel-title pull-right">
                                <a href="<c:url value='/edit-${article.id}-article' />">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"
                                          aria-label="edit"></span>
                                </a>
                                <a href="<c:url value='/delete-${article.id}-article' />">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"
                                          aria-label="delete"></span>
                                </a>
                            </div>
                        </h3>
                    </div>
                    <div class="panel-body">
                            ${article.content}
                    </div>
                    <div class="panel-footer">
                            ${article.publicationDate},
                        <a href="<c:url value='/search/category=${article.category}' />">${article.category}</a>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">
                Не найдено новостей по соответствующему поиску
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>