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

@WebServlet(urlPatterns = "/authors/edit")
public class EditAuthorServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Author author = authorManager.getById(authorId);
        req.setAttribute("authors", authorManager.getAll());
        req.getRequestDispatcher("/WEB-INF/editAuthor.jsp").forward(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        Author author = Author.builder()
                .id(authorId)
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .build();

        authorManager.edit(author);
        resp.sendRedirect("/authors");
    }
}
