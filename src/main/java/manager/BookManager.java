package manager;

import db.DBConnectionProvider;
import model.Author;
import model.Book;

import java.sql.*;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class BookManager {

    private Connection connection;

    public BookManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    private AuthorManager eventManager = new AuthorManager();

    public void add(Book user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into user(name,surname,email,event_id) Values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getEvent().getId());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            user.setId(id);
        }
    }

    public List<Book> getAll() throws SQLException, ParseException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        List<Book> users = new LinkedList<>();
        while (resultSet.next()) {
            users.add(getUserFromResultSet(resultSet));
        }
        return users;
    }

    public Book getById(int id) throws SQLException, ParseException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id = " + id);
        List<Book> users = new LinkedList<>();
        if (resultSet.next()) {
            return getUserFromResultSet(resultSet);
        }
        return null;
    }

    private Book getUserFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        Book user = new Book();
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        int eventId = resultSet.getInt("event_id");
        Author event = eventManager.getById(eventId);
        user.setEvent(event);

        return user;
    }

    public void removeUserById(int userId) {
        String sql = "delete from user where id = " + userId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Book user) {
        String sql = "update user set name = ?,surname = ?,email = ?,event_id = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getEvent().getId());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
