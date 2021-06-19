package DAO;

import config.DatabaseConfig;
import dreamTeam.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    DatabaseConfig databaseConfig = new DatabaseConfig();

    public void createUser(User user) {
        String insert = "INSERT into users (name, surname, age) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(insert)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUser(int id) {
        String idSelect = "SELECT * FROM users WHERE id = ?";
        User user = new User();
        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(idSelect)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) {
        String delete = "UPDATE users set (name, surname, age) = (?, ?, ?) WHERE id = ?";
        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(delete);
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.setInt(4, user.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        String delete = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(delete)) {
            statement.setInt(1, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String query = "select * from users";
        try {
            PreparedStatement statement = databaseConfig.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
}
