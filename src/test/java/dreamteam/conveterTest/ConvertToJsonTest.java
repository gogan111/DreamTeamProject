package dreamteam.conveterTest;


import dreamteam.coverter.ConvertJsonToString;
import dreamteam.dto.User;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class ConvertToJsonTest {
    @Mock
    HttpServletRequest request;

    private static JSONObject jsonObject;

    @BeforeAll
    private void init() {
        jsonObject = new JSONObject(new User(1, "Aleksey", "Ivanov", 22, "aaaa@mail.ru"));
    }

    @Test
    public void convereterJsonToStringTest() {
        Mockito.doNothing().when(request).setAttribute("1", jsonObject);
        JSONObject jsonObject1 = new JSONObject(ConvertJsonToString.convertBody(request));
        System.out.println(jsonObject1);
    }
}
