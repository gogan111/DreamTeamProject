package dreamteam.commandTest;

import dreamteam.command.Save;
import dreamteam.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveTest {
    @InjectMocks
    Save save;
    @Mock
    UserService userService;
    String resp;
    String req;

    @BeforeEach
    private void init() {
        req ="{id:0, name:\"aaa\", surname:\"bbb\", age:33, email:\"aaaaaa@mai.ru\"}";
    }


    @Test
    public void executeSaveTest() {

    }
}
