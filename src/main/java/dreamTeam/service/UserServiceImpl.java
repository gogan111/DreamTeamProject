package dreamTeam.service;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int createUser(User user) {
        try {
            return userDAO.createUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
