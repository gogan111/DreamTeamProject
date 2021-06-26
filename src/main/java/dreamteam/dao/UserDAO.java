package dreamteam.dao;


import dreamteam.config.DatabaseConfig;
import dreamteam.dto.User;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * Table - andersen
 * Database - alex
 * User - alex
 * password - 1234
 */

public class UserDAO {
    @Inject
    private DatabaseConfig databaseConfig;
    @Inject
    private User user;

    public int saveUser(User user) {
        String insert = "INSERT INTO users (name, surname, age, email) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConfig.getConnection()
                .prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            } catch (SQLException e) {
                throw new SQLException("Could not receive id of a user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateUser(User user) {
        String updateUsr = "UPDATE users SET (name, surname, age, email) = (?, ?, ?, ?) WHERE email = ?";
        try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(updateUsr)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Could not find a user");
            e.printStackTrace();
        }
        return false;
    }

    /*
     * Пользователь удаляется по email
     * statement.setString (номер аттрибута в таблице, название аттрибута)
     */

    public boolean deleteUser(String email) {
        String delete = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(delete)) {
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Could not find a user");
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUsers() {
        String query = "SELECT id, name, surname, age, email FROM users ORDER BY email";
        try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<User> usersList = new ArrayList<>();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setEmail(resultSet.getString(5));
                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
            System.err.println("Could not get the list of the users");
            e.printStackTrace();
        }
        return null;
    }
}

