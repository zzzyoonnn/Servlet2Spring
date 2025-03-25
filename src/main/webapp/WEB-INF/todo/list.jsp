<%--
  Created by IntelliJ IDEA.
  User: jiyoon
  Date: 2/11/25
  Time: 5:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Todo List</title>
</head>
<body>
<h1>Todo List</h1>

<ul>
    <c:forEach var="dto" items="${dtoList}">
        <li>${dto}</li>
    </c:forEach>
</ul>

${todoList}

</body>
</html>
