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
        if (jObj.getString("id") == null) {
            user.setId(0);
        }
        user.setName(jObj.getString("name"));
        user.setSurname(jObj.getString("surname"));
        user.setAge(jObj.getInt("age"));
        user.setEmail(jObj.getString("email"));
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            validator.validate(user);
            int id = userService.saveUser(user);
            user.setId(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(new JSONObject(user));
            out.flush();
        } catch (IOException | IncorrectDataException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String errorValidation = e.getMessage();
            jObj = new JSONObject(errorValidation);
            out.print(jObj);
            out.flush();
        }
    }

}

