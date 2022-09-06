package servlet;

import lombok.SneakyThrows;
import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books/add")
public class AddBookServlet extends HttpServlet {

    private AuthorManager authorManager = new AuthorManager();
    private BookManager bookManager = new BookManager();


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> all = authorManager.getAll();
        request.setAttribute("authors", all);
        request.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int authorId = Integer.parseInt(request.getParameter("author_id"));
        Book book = Book.builder()
                .title(title)
                .description(description)
                .price(price)
                .author(authorManager.getById(authorId))
                .build();

        bookManager.add(book);
        response.sendRedirect("/books");
    }
}
