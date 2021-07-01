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

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    List<User> userList;
    User user;
    User negativeUser;
    @InjectMocks
    UserService userService;

    @Mock
    UserDAO userDAO;

    @BeforeEach
    public void init() {
        userList = new ArrayList<>();
        userList.add(new User(1, "Aleksey", "Ivanov", 22, "asda@mail.com"));
        userList.add(new User(2, "Ivan", "Andreev", 33, "asdas@mail.com"));
        userList.add(new User(3, "Mary", "Brown", 44, "mary@mail.com"));
        userList.add(new User(4, "Jason", "Black", 55, "asdas@mail.com"));
        user = new User(5, "John", "Smith", 11, "john@smith.com");
        userList.add(user);
        negativeUser = new User(6, "Johnyyy", "Smithyyyy", 11, "johnyyyyy@smithyyyy.com");
    }

    @Test
    public void getAllPersonPositiveTest() {
        Mockito.when(userDAO.getAllUsers()).thenReturn(userList);
        int a = userService.getAllUsers().size();
        verify(userDAO).getAllUsers();
        Assertions.assertEquals(5, a);
    }

    @Test
    public void updateUserPositiveTest() {
        Mockito.when(userDAO.updateUser(user)).thenReturn(true);
        boolean trueUpdate = userService.updateUser(user);
        verify(userDAO).updateUser(user);
        Assertions.assertTrue(trueUpdate);
    }

    @Test
    public void deleteUserPositiveTest() {
        Mockito.when(userDAO.deleteUser(1)).thenReturn(true);
        boolean deleteUser = userService.deleteUser(1);
        Assertions.assertTrue(deleteUser);
    }


    @Test
    public void getAllPersonNegativeTest() {
        Mockito.when(userDAO.getAllUsers()).thenReturn(userList);
        int a = userService.getAllUsers().size();
        verify(userDAO).getAllUsers();
        Assertions.assertNotEquals(10, a);
    }

    @Test
    public void updateUserNegativeTest() {
        Mockito.when(userDAO.updateUser(negativeUser)).thenReturn(false);
        boolean trueUpdate = userService.updateUser(negativeUser);
        verify(userDAO).updateUser(negativeUser);
        Assertions.assertFalse(trueUpdate);
    }

    @Test
    public void deleteUserNegativeTest() {
        Mockito.when(userDAO.deleteUser(7)).thenReturn(false);
        boolean deleteUser = userService.deleteUser(7);
        Assertions.assertFalse(deleteUser);
    }
}