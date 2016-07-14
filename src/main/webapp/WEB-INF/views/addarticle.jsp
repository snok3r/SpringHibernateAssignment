<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Add Article - Spring Hibernate Assignment</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2>Add Article Form</h2>

<form:form method="POST" modelAttribute="article">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="title">Title: </label></td>
            <td><form:input path="title" id="title"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="content">Content: </label></td>
            <td><form:input path="content" id="content"/></td>
            <td><form:errors path="content" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="category">Category: </label></td>
            <td><form:input path="category" id="category"/></td>
            <td><form:errors path="category" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="3">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Add"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Go back to <a href="<c:url value='/main' />">List of All Articles</a>
</body>
</html>