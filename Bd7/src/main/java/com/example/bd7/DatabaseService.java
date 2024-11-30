package com.example.bd7;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private final String url = "jdbc:mysql://localhost:3306/demo";
    private final String user = "Ulyana";
    private final String password = "111";
    private static User currentUser;

    public DatabaseService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        String insertPersonQuery = "INSERT INTO Person (name, surname) VALUES (?, ?)";
        String insertUserQuery = "INSERT INTO User (login, password, person_id) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, this.user, password)) {
            connection.setAutoCommit(false);

            try (PreparedStatement insertPersonStmt = connection.prepareStatement(insertPersonQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertPersonStmt.setString(1, user.getPerson().getName());
                insertPersonStmt.setString(2, user.getPerson().getSurname());
                insertPersonStmt.executeUpdate();

                try (ResultSet generatedKeys = insertPersonStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int personId = generatedKeys.getInt(1);

                        try (PreparedStatement insertUserStmt = connection.prepareStatement(insertUserQuery)) {
                            insertUserStmt.setString(1, user.getLogin());
                            insertUserStmt.setString(2, user.getPassword());
                            insertUserStmt.setInt(3, personId);
                            insertUserStmt.executeUpdate();
                        }
                    }
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User user) {
        String deleteUserQuery = "DELETE FROM User WHERE user_id = ?";
        String deletePersonQuery = "DELETE FROM Person WHERE person_id = ?";

        try (Connection connection = DriverManager.getConnection(url, this.user, password)) {
            connection.setAutoCommit(false);
            try (PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserQuery))
            {
                deleteUserStmt.setInt(1, user.getUserId());
                deleteUserStmt.executeUpdate();
            }
            try (PreparedStatement deletePersonStmt = connection.prepareStatement(deletePersonQuery))
            {
                deletePersonStmt.setInt(1, user.getPerson().getPersonId());
                deletePersonStmt.executeUpdate();
            }
            connection.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                int personId = resultSet.getInt("person_id");

                Person person = getPersonById(personId);
                user.setPerson(person);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByLogin(String login) {
        String query = "SELECT * FROM User WHERE login = ?";
        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    int personId = resultSet.getInt("person_id");

                    Person person = getPersonById(personId);
                    user.setPerson(person);

                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Person getPersonById(int personId) {
        String query = "SELECT * FROM Person WHERE person_id = ?";
        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Person person = new Person();
                    person.setPersonId(resultSet.getInt("person_id"));
                    person.setName(resultSet.getString("name"));
                    person.setSurname(resultSet.getString("surname"));
                    return person;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String updatePersonQuery = "UPDATE Person SET name = ?, surname = ? WHERE person_id = ?";
        String updateUserQuery = "UPDATE User SET login = ?, password = ? WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(url, this.user, password)) {
            connection.setAutoCommit(false);

            try (PreparedStatement updatePersonStmt = connection.prepareStatement(updatePersonQuery)) {
                updatePersonStmt.setString(1, user.getPerson().getName());
                updatePersonStmt.setString(2, user.getPerson().getSurname());
                updatePersonStmt.setInt(3, user.getPerson().getPersonId());
                updatePersonStmt.executeUpdate();
            }

            try (PreparedStatement updateUserStmt = connection.prepareStatement(updateUserQuery)) {
                updateUserStmt.setString(1, user.getLogin());
                updateUserStmt.setString(2, user.getPassword());
                updateUserStmt.setInt(3, user.getUserId());
                updateUserStmt.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        DatabaseService.currentUser = currentUser;
    }

}
