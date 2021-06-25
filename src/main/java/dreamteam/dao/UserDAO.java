package dreamteam.dao;

import dreamteam.config.DatabaseConfig;
import dreamteam.dto.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

@ApplicationScoped
public class UserDAO {
    @Inject
    DatabaseConfig databaseConfig;

    public int saveUser(User user) {
        String insert = "INSERT INTO andersen (name, surname, age, mail) values (?, ?, ?, ?)";
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
                System.err.println("Could not receive id of a user");
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.err.println("Could not create the user");
        }
        return 0;
    }

    public User getUser(int id) {
        String setUserId = "SELECT id,name,surname,age,mail FROM andersen WHERE id = ?";
        User user = new User();
        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(setUserId)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setSurname(resultSet.getString(3));
                    user.setAge(resultSet.getInt(4));
                    user.setEmail(resultSet.getString(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public int updateUser(User user) {
        String updateUsr = "UPDATE andersen SET (name, surname, age, mail) = (?, ?, ?, ?) WHERE id = ?";
        try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(updateUsr)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            } catch (SQLException e) {
                System.err.println("Could not to update user");
            }
        } catch (SQLException e) {
            System.err.println("Could not find a user");
            e.printStackTrace();
        }
        return 0;
    }

    public boolean deleteUser(int userId) {
        String delete = "DELETE FROM andersen WHERE id = ?";
        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(delete)) {
            statement.setInt(1, userId);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Could not find a user");
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUsers() {
        String query = "SELECT id,name,surname,age,mail FROM andersen ORDER BY id";
        try (PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<User> usersList = new ArrayList<>();
            User user = new User();
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

