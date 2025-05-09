<%--
  Created by IntelliJ IDEA.
  User: jiyoon
  Date: 2/11/25
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>등록 기능</title>
</head>
<body>
<form action = "/todo/register" method = "post">
    <div>
        Title: <input type="text" name="title">
    </div>
    <div>
        DueDate: <input type="date" name="dueDate" value="2025-05-09">
    </div>
    <div>
        Writer: <input type="text" name="writer">
    </div>
    <div>
        Finished: <input type="checkbox" name="finished">
    </div>
    <div>
        <button type="reset">RESET</button>
        <button type="submit">REGISTER</button>
    </div>
</form>
</body>
</html>
