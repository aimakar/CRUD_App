package ru.makarov.springcourse.dao;


import org.springframework.stereotype.Component;
import ru.makarov.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Makarov on 20.12.2022
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    public static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "ardI06_20";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();;
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM PERSON";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id) {
        Person person = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM PERSON WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    public void save(Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatePerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE PERSON SET NAME=?, AGE=?, EMAIL=? WHERE ID = ?");
            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM PERSON WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}