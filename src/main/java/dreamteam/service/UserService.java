package dreamteam.service;


import dreamteam.dao.UserDAO;
import dreamteam.dto.User;

import javax.inject.Inject;
import java.util.List;


public class UserService {
    @Inject
    private UserDAO userDAO;

    public int saveUser(User user) {
        System.out.println(user.getId());
        if (user.getId() != 0) {
            if (updateUser(user)) {
                return user.getId();
            }
        }
        return userDAO.saveUser(user);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(String email) {
        return userDAO.deleteUser(email);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
