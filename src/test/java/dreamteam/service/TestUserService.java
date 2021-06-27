package dreamteam.service;

import dreamteam.dao.UserDAO;
import dreamteam.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestUserService {
    List<User> userList;
    User user;
    User user1;
    @InjectMocks
    UserService userService;

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
    public void getAllPersonTest() {
        Mockito.when(userDAO.getAllUsers()).thenReturn(userList);
        int a = userService.getAllUsers().size();
        Assertions.assertEquals(2, a);
    }

    @Test
    public void updateUserTest() {
        Mockito.when(userDAO.updateUser(user)).thenReturn(true);
        boolean trueUpdate = userService.updateUser(user);
        Assertions.assertTrue(trueUpdate);
    }

    @Test
    public void deleteUserTest() {
        Mockito.when(userDAO.deleteUser("asdas@mail.com")).thenReturn(true);
        boolean deleteUser = userService.deleteUser("asdas@mail.com");
        Assertions.assertTrue(deleteUser);
    }

}
