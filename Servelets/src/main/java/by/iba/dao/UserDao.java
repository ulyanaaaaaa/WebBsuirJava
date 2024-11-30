package by.iba.dao;

import by.iba.model.User;
import by.iba.util.ConnectorDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private final static String SQL_GET_USER = "SELECT login, passw FROM users WHERE login=? AND passw=?";
    private final static String SQL_CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
    private final static String SQL_INSERT_USER = "INSERT INTO users(login, passw) VALUES (?, ?)";

    private Connection connection;

    public UserDao() {
        try {
            this.connection = ConnectorDB.getConnection();
            if (this.connection == null) {
                throw new SQLException("Unable to establish a connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection.", e);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidUser(final String login, final byte[] password) {
        if (this.connection == null) {
            throw new IllegalStateException("Connection is not initialized.");
        }

        try (PreparedStatement ps = connection.prepareStatement(SQL_GET_USER)) {
            ps.setString(1, login);
            ps.setBytes(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertUser(User user) {
        if (this.connection == null) {
            throw new IllegalStateException("Connection is not initialized.");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_LOGIN)) {
            preparedStatement.setString(1, user.getLogin());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return false;
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT_USER)) {
                    insertStatement.setString(1, user.getLogin());
                    insertStatement.setBytes(2, user.getPassw());
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
