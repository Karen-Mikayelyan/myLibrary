<%@ page import="java.util.List" %>
<%@ page import="model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Edit author
    </title>
</head>
<body>
<%
    Author author = (Author) request.getAttribute("author");
    List<Author> authors = (List<Author>) request.getAttribute("authors");

%>
Please update Author's data
<form action="/authors/edit" method="post">
    <input type="hidden" name="authorId" value="<%=author.getId()%>">
    <input type="text" name="name" value="<%=author.getName()%>"/> <br>
    <input type="text" name="surname" value="<%=author.getSurname()%>"/> <br>
    <input type="email" name="email" value="<%=author.getEmail()%>"/> <br>
    <input type="number" name="age" value="<%=author.getAge()%>"/> <br>


    <input type="submit" value="Save">
</form>
</body>
</html>
