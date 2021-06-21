package dreamTeam.service;

import dreamTeam.domain.User;
import java.util.List;

public interface UserService {

    int createUser(User user);

    User getUser(int id);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    List<User> getAllUsers();
}
