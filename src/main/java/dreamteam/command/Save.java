package dreamteam.command;

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
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jObj = new JSONObject(Objects.requireNonNull(GetBody.getBody(req)));
        user.setName(jObj.getString("name"));
        user.setSurname(jObj.getString("surname"));
        user.setAge(jObj.getInt("age"));
        user.setEmail(jObj.getString("email"));
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            validator.validate(user);
        } catch (IncorrectDataException e) {
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject jsonObject = new JSONObject(e.getMessage());
            out.print(jsonObject);
            out.flush();
        }
       int id = userService.saveUser(user);
            user.setId(id);
           out.print(new JSONObject(user));
           out.flush();
           resp.setStatus(HttpServletResponse.SC_OK);
    }

}

