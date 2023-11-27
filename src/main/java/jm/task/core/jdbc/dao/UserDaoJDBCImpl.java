package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS db_user("
                + "id SERIAL PRIMARY KEY, "
                + "name VARCHAR(20) NOT NULL, "
                + "lastName VARCHAR(20) NOT NULL, "
                + "age INTEGER NOT NULL"
                + ")";
        try (Connection dbConnection = util.connectToDB("example", "postgres", "8786909564");
            Statement statement = dbConnection.createStatement()){
            statement.execute(createTableSQL);
            System.out.println("Таблица пользователей создана успешно!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void dropUsersTable() {
        String createTableSQL = "DROP TABLE db_user";
        try (Connection dbConnection = util.connectToDB("example", "postgres", "8786909564");
             Statement statement = dbConnection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Таблица удалена успешно!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertUserSQL = "INSERT INTO db_user (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection dbConnection = util.connectToDB("example", "postgres", "8786909564");
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertUserSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь добавлен успешно!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String deleteUserSQL = "DELETE FROM db_user WHERE id = ?";
        try (Connection dbConnection = util.connectToDB("example", "postgres", "8786909564");
             PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteUserSQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь удален успешно!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String selectAllUsersSQL = "SELECT * FROM db_user";
        try (Connection dbConnection = util.connectToDB("example", "postgres", "8786909564");
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAllUsersSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String truncateTableSQL = "TRUNCATE TABLE db_user";
        try (Connection dbConnection = util.connectToDB("example", "postgres", "8786909564");
             Statement statement = dbConnection.createStatement()) {
            statement.execute(truncateTableSQL);
            System.out.println("Таблица очищена успешно!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
