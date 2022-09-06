package servlet;

import lombok.SneakyThrows;
import manager.BookManager;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books")
public class BooksServlet extends HttpServlet {

    private BookManager bookManager = new BookManager();


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = bookManager.getAll();
        req.setAttribute("books",bookList);
        req.getRequestDispatcher("/WEB-INF/books.jsp").forward(req, resp);


    }
}
