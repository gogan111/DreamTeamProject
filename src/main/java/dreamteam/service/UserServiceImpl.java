package dreamteam.service;


import dreamteam.DAO.UserDAOImpl;
import dreamteam.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public int createUser(User user) {
        return new UserDAOImpl().createUser(user);
    }

    @Override
    public User getUser(int id) {
        return new UserDAOImpl().getUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return new UserDAOImpl().updateUser(user);
    }

    @Override
    public boolean deleteUser(int id) {

        return new UserDAOImpl().deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new UserDAOImpl().getAllUsers();
    }
}
