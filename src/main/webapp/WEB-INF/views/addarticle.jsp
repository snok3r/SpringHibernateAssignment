<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <spring:url value="resources/js/jquery-2.2.4.min.js" var="jqueryJs"/>
    <spring:url value="resources/css/bootstrap.min.css" var="bootstrapcss"/>
    <spring:url value="resources/js/bootstrap.min.js" var="bootstrapjs"/>
    <spring:url value="/search/titleandcontent=" var="search_link"/>

    <link href="${bootstrapcss}" rel="stylesheet"/>
    <script src="${jqueryJs}"></script>
    <script src=${bootstrapjs}></script>

    <style>
        .error {
            color: #ff0000;
        }
    </style>

    <script>
        function search() {
            document.location.href = "${search_link}" + $("#searchBox").val();
        }
    </script>

    <title>Add Article - Spring Hibernate Assignment</title>
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
                    <li><a href="<c:url value='/main' />">Домой</a></li>
                    <li class="active"><a href="#">Добавить новость<span class="sr-only">(текущая)</span></a></li>
                </ul>
                <form class="navbar-form navbar-right" onsubmit="search();" action="javascript:void(0);">
                    <div class="form-group">
                        <input type="text" class="form-control" id="searchBox" placeholder="Название или содержание"/>
                    </div>
                    <input type="button" class="btn btn-default" onclick="search();" value="Поиск" id="searchBtn"/>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <%-- форма добавления --%>
    <form:form class="form-horizontal" role="form" method="POST" modelAttribute="article">
        <form:input type="hidden" path="id" id="id"/>
        <div class="form-group">
            <label class="control-label col-sm-2" for="title">Название:</label>
            <div class="col-sm-6">
                <form:input path="title" type="text" class="form-control" id="title"
                            placeholder="Введите название новости"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="title" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="category">Категория:</label>
            <div class="col-sm-6">
                <form:input path="category" type="text" class="form-control" id="category"
                            placeholder="Введите категорию новости"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="category" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="content">Текст:</label>
            <div class="col-sm-6">
                <form:textarea path="content" rows="10" class="form-control" id="content"
                               placeholder="Введите текст новости"/>
            </div>
            <div class="col-sm-4">
                <form:errors path="content" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${edit}">
                        <button type="submit" class="btn btn-default" value="Update">Обновить</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-default" value="Add">Добавить</button>
                    </c:otherwise>
                </c:choose>
                <a href="<c:url value='/main' />" class="btn btn-default">
                    Отмена
                </a>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>