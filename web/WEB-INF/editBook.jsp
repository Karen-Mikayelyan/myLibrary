<%@ page import="java.util.List" %>
<%@ page import="model.Author" %>
<%@ page import="model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Edit book
    </title>
</head>
<body>
<%
    Book book = (Book) request.getAttribute("book");

    List<Author> authors = (List<Author>) request.getAttribute("authors");

%>
Please update Book's data
<form action="/books/edit" method="post">
    <input type="hidden" name="bookId" value="<%=book.getId()%>">
    <input type="text" name="title" value="<%=book.getTitle()%>"/> <br>
    <input type="text" name="description" value="<%=book.getDescription()%>"/> <br>
    <input type="number" name="price" value="<%=book.getPrice()%>"/> <br>
    <select name="authorId">
        <% for (Author author : authors) {
            if (author.equals(book.getAuthor())) {
        %>
        <option selected value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%>
        </option>
        <%} else {%>
        <option value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%>

                <%} }%>
    </select>

    <input type="submit" value="Save">
</form>
</body>
</html>
