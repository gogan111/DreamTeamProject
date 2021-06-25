package dreamteam.service;


import dreamteam.dao.UserDAO;
import dreamteam.dto.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;


@ApplicationScoped
public class UserService {
    @Inject
    private UserDAO userDAO;

    public int saveUser(User user) {
        if (user.getId() != 0) {
            return updateUser(user);
        }
        return userDAO.saveUser(user);
    }

    public int updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(String email) {
        return userDAO.deleteUser(email);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
