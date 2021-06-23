package dreamTeam.service;


import dreamTeam.DAO.UserDAO;
import dreamTeam.DAO.UserDAOImpl;
import dreamTeam.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int createUser(User user) {
        return userDAO.createUser(user);
    }

    @Override
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {

        return userDAO.getAllUsers();
    }
}
