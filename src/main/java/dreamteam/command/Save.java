package dreamteam.command;

import dreamteam.dto.User;
import dreamteam.service.UserService;
import dreamteam.user_validation.UserValidation;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Named("save")
public class Save implements Command {
    @Inject
    User user;
    @Inject
    UserService userService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jObj = new JSONObject(req);
        UserValidation userValidation = new UserValidation();
        user.setName(jObj.getString("name"));
        user.setSurname(jObj.getString("surname"));
        user.setAge(jObj.getInt("age"));
        user.setEmail(jObj.getString("email"));
        int id = userService.saveUser(user);
        user.setId(id);
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(userValidation));
        out.flush();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
