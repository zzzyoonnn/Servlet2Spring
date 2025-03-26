<%--
  Created by IntelliJ IDEA.
  User: jiyoon
  Date: 3/25/25
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Modify</title>
</head>
<body>

<form id="form1" action="/todo/modify" method="post">
    <div>
        <input type="text" name="no" value="${todoDTO.no}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${todoDTO.title}">
    </div>
    <div>
        <input type="date" name="dueDate" value="${todoDTO.dueDate}">
    </div>
    <div>
        <input type="checkbox" name="finished" ${todoDTO.finished ? "checked": ""}>
    </div>

    <div>
        <button type="submit">Modify</button>
    </div>
</form>

<form id="form2" action="/todo/remove" method="post">
    <input type="hidden" name="no" value="${todoDTO.no}" readonly>
    <div>
        <button type="submit">Remove</button>
    </div>
</form>
</body>
</html>
