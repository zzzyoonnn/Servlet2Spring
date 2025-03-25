<%--
  Created by IntelliJ IDEA.
  User: jiyoon
  Date: 2/24/25
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Read</title>
</head>
<body>
<div>
    <input type="text" name="no" value="${todoDTO.no}" readonly>
</div>
<div>
    <input type="text" name="title" value="${todoDTO.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${todoDTO.dueDate}">
</div>
<div>
    <a href="/todo/modify?no=${todoDTO.no}">Modify/Remove</a>
    <a href="/todo/list">List</a>
</div>
</body>
</html>
