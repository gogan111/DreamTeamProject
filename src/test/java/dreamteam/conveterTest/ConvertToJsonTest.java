package dreamteam.conveterTest;


import dreamteam.command.Save;
import dreamteam.coverter.ConvertJsonToString;
import dreamteam.dto.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class ConvertToJsonTest {
    User user;
    @Mock
    public static HttpServletRequest request;
    @InjectMocks
    Save save;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;


    @BeforeEach
    public void init() throws IOException {
        user = new User(1, "Aleksey", "Ivanov", 22, "aaaa@mail.ru");
        String json = String.valueOf(new JSONObject(user)).toString();
        File file = new File("json.txt");
        bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(json);
        bufferedWriter.close();
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    @Test
    public void convereterJsonToStringTest() throws IOException {
        Mockito.when(request.getReader()).thenReturn(bufferedReader);
        String user = ConvertJsonToString.convertBody(request);
        Assertions.assertEquals(new JSONObject(user).toString(), user);
    }
}
