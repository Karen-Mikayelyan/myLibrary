<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users page</title>
</head>
<body>

<%

    List<Book> books = (List<Book>) request.getAttribute("books");


%>
<table border="1">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author</th>
        <th>action</th>
    </tr>
    <% for (Book book : books) {
    %>
    <tr>
        <td><%=book.getId()%>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td>
            <% if (book.getAuthor() != null) {
                %>
            <%=book.getAuthor().getName()%> <%=book.getAuthor().getSurname()%>
            <%} else {%>
            <span style="color: red">there is no author</span>
            <%} %>
        </td>

        <td>
            <a href="/books/delete?bookId=<%=book.getId()%>">Delete</a> |
            <a href="/books/edit?bookId=<%=book.getId()%>">Edit</a>
        </td>
    </tr>
    <%
        } %>
</table>
</body>
</html>
