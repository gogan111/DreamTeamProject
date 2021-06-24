package dreamTeam.DAO;

import dreamTeam.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    int createUser(User user) throws SQLException;

    User getUser(int id);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    List<User> getAllUsers();
}
