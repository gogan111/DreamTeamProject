package dreamteam.command;

import dreamteam.coverter.ConvertToJson;
import dreamteam.dto.User;
import dreamteam.global_exception.IncorrectDataException;
import dreamteam.service.UserService;
import dreamteam.validator.EmailValidator;
import org.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Named("save")
@RequestScoped
public class Save implements Command {
    @Inject
    User user;
    @Inject
    UserService userService;
    @Inject
    EmailValidator validator;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jObj = new JSONObject(ConvertToJson.convertBody(req));
//        орпеделиться что будут присылать при создании? "",0, null
        user.setName(jObj.getString("name"));
        user.setSurname(jObj.getString("surname"));
        user.setAge(jObj.getInt("age"));
        user.setEmail(jObj.getString("email"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            validator.validate(user);
            int id = userService.saveUser(user);
            user.setId(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(new JSONObject(user).toString());

        } catch (IOException | IncorrectDataException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String errorValidation = e.getMessage();
            String json = new JSONObject(errorValidation).toString();
            try {
                resp.getWriter().write(json);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}

