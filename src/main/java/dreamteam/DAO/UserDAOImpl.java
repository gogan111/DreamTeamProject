package dreamteam.DAO;

import dreamteam.config.DatabaseConfig;
import dreamteam.domain.User;

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


public class UserDAOImpl implements UserDAO {
    DatabaseConfig databaseConfig = new DatabaseConfig();

    public int createUser(User user) {

        String insert = "INSERT INTO andersen (name, surname, age, mail) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConfig
                .getConnection()
                .prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int id = 0;
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
                return id;
            } catch (SQLException e) {
                System.err.println("Create user problems");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public User getUser(int id) {
        String setUserId = "SELECT * FROM andersen WHERE id = ?";
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

    public boolean updateUser(User user) {
        String updateUsr = "UPDATE andersen SET (name, surname, age, mail) = (?, ?, ?, ?) WHERE id = ?";

        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(updateUsr)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.setInt(5, user.getId());
            statement.setString(4, user.getEmail());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Update user problems");
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        String delete = "DELETE FROM andersen WHERE id = ?";

        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(delete)) {

            statement.setInt(1, userId);
            statement.execute();

            return true;
        } catch (SQLException e) {
            System.err.println("Create delete problems");
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUsers() {

        String query = "SELECT * FROM andersen";

        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            List<User> usersList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setEmail(resultSet.getString(5));
                usersList.add(user);
            }

            return usersList;

        } catch (SQLException e) {
            System.err.println("Get all user problems");
            e.printStackTrace();
        }
        return null;
    }
}
