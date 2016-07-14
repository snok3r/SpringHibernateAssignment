<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Main - Spring Hibernate Assignment</title>

    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
</head>


<body>
<h2>List of Articles</h2>
<table>
    <tr>
        <td>Title</td>
        <td>Publication Date</td>
        <td>Category</td>
        <td>Content</td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items="${articles}" var="article">
        <tr>
            <td>${article.title}</td>
            <td>${article.publicationDate}</td>
            <td>${article.category}</td>
            <td>${article.content}</td>
            <td><a href="<c:url value='/edit-${article.id}-article' />">Edit</a></td>
            <td><a href="<c:url value='/delete-${article.id}-article' />">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<c:url value='/add' />">Add New Article</a>
</body>
</html>