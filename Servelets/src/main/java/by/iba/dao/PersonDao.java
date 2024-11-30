package by.iba.dao;

import by.iba.model.Person;
import by.iba.util.ConnectorDB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonDao {

    private static final String SQL_GET_PERSONS = "SELECT * FROM persons";
    private static final String SQL_INSERT_PERSONS = "INSERT INTO persons(pname, phone, email) VALUES (?, ?, ?)";

    public void insertPerson(Person person) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PERSONS)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getPhone());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Замените на логирование в реальном коде
        }
    }

    public List<Person> getPersons() {
        List<Person> persons = new LinkedList<>();
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_PERSONS)) {
            while (resultSet.next()) {
                Person person = new Person();
                person.setName(resultSet.getString("pname"));
                person.setPhone(resultSet.getString("phone"));
                person.setEmail(resultSet.getString("email"));
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Замените на логирование в реальном коде
        }
        return persons;
    }
}