package dreamTeam.service;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TestUserService {
    List<User> users;
    User user;
    User user1;

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDAO userDAO;

    @BeforeEach
    public void init() {
        userService = new UserServiceImpl();
        users = new ArrayList<>();
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
        users.add(user);
        users.add(user1);
    }

    @Test
    public void getAllPersonTest() throws SQLException {
        Mockito.lenient().when(userDAO.getAllUsers()).thenReturn(users);
        int a = userService.getAllUsers().size();
        System.out.println(a);
//        Assertions.assertEquals(2, a);
    }

}
