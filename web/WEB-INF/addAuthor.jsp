<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.09.2022
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Add author
    </title>
</head>
<body>
Please input Author's data
<form action="/authors/add" method="post">
    <input type="text" name="name" placeholder="please input name"/> <br>
    <input type="text" name="surname" placeholder="please input surname"/> <br>
<input type="email" name="email" placeholder="please input email"> <br>
    <input type="number" name="age" placeholder="Please input age">
    <br>

    </select>
    <br>
    <input type="submit" value="Add">
</form>
</body>
</html>
