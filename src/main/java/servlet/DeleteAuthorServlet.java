package servlet;

import manager.AuthorManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/authors/delete")
public class DeleteAuthorServlet extends HttpServlet {

    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));

        authorManager.deleteAuthorById(authorId);

        resp.sendRedirect("/authors");
    }
}
