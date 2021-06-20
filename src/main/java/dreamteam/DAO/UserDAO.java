package dreamteam.DAO;

import dreamteam.domain.User;

import java.util.List;

public interface UserDAO {
    int createUser(User user);

    User getUser(int id);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    List<User> getAllUsers();
}
