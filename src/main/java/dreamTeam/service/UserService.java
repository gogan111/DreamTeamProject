package dreamTeam.service;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int createUser(User user) {
        return userDAO.createUser(user);
    }

    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
