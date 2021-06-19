package DAO;

import dreamTeam.domain.User;

import java.util.List;

public interface UserDAO {
    void createUser(User user);
    User getUser(int id);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
}
