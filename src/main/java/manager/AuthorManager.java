package manager;

import com.mysql.cj.protocol.Protocol;
import db.DBConnectionProvider;
import model.Author;


import java.sql.*;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class AuthorManager {

    private final Connection connection;

    public AuthorManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void add(Author author) {
        try {
            String query = "insert into author(name,surname,email,age) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setInt(4, author.getAge());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = 0;
                id = resultSet.getInt(1);
                author.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getAll() throws SQLException, ParseException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM author");
        List<Author> authors = new LinkedList<>();
        while (resultSet.next()) {
            authors.add(getAuthorFromResultSet(resultSet));
        }
        return authors;
    }

    public Author getById(int id) throws SQLException, ParseException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id);
        Author author = null;
        if (resultSet.next()) {
            author = getAuthorFromResultSet(resultSet);
        }
        return author;
    }

    public void deleteAuthorById(int id) {
        String sql = "delete from author where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) {
        Author author = null;
        try {
            author = new Author();
            author.setId(resultSet.getInt("id"));
            author.setName(resultSet.getString("name"));
            author.setSurname(resultSet.getString("surname"));
            author.setEmail(resultSet.getString("email"));
            author.setAge(resultSet.getInt("age"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    public void edit(Author author) {
        String sql = "update author set name = ?,surname = ?,email = ?,age = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setInt(4, author.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
