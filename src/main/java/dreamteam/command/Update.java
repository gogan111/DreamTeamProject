package dreamteam.command;

import dreamteam.coverter.ConvertToJson;
import dreamteam.dto.User;
import dreamteam.service.UserService;
import org.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Named("update")
@RequestScoped
public class Update implements Command {
    @Inject
    private UserService userService;
    @Inject
    private User user;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jObj = new JSONObject(ConvertToJson.convertBody(req));
        user.setId(jObj.getInt("id"));
        user.setName(jObj.getString("name"));
        user.setSurname(jObj.getString("surname"));
        user.setAge(jObj.getInt("age"));
        user.setEmail(jObj.getString("email"));
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            userService.updateUser(user);
            String str = new JSONObject(user).toString();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(str);
            out.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
