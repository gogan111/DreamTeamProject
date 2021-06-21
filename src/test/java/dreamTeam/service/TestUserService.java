package dreamTeam.service;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestUserService {
    List<User> userList;
    User user;
    User user1;

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDAO userDAO;

    @BeforeEach
    public void init() {
        userList = new ArrayList<>();
        user = new User();
        user1 = new User();
        user.setName("Aleksey");
        user.setSurname("Ivanov");
        user.setEmail("asda@mail.com");
        user.setAge(22);
        user1.setName("Ivan");
        user1.setSurname("Andreev");
        user1.setEmail("asdas@mail.com");
        user1.setAge(33);
        userList.add(user);
        userList.add(user1);
    }

    @Test
    public void getAllPersonTest() throws SQLException {
        Mockito.when(userDAO.getAllUsers()).thenReturn(userList);
        userService = new UserServiceImpl(userDAO);
        int a = userService.getAllUsers().size();
        Assertions.assertEquals(2, a);
    }
    @Test
    public void getPerson() throws SQLException {
        Mockito.when(userDAO.getUser(1)).thenReturn(userList.get(0));
        userService = new UserServiceImpl(userDAO);
        User testUser = userService.getUser(1);
        Assertions.assertEquals(user, testUser);
    }
    @Test
    public void updateUserTest() throws SQLException {
        Mockito.when(userDAO.updateUser(user)).thenReturn(true);
        userService = new UserServiceImpl(userDAO);
        boolean trueUpdate = userService.updateUser(user);
        Assertions.assertTrue(trueUpdate);
    }
    @Test
    public void deleteUserTest() throws SQLException {
        Mockito.when(userDAO.deleteUser(1)).thenReturn(true);
        userService = new UserServiceImpl(userDAO);
        boolean deleteUser = userService.deleteUser(1);
        Assertions.assertTrue(deleteUser);
    }

}
