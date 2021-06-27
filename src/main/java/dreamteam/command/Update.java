package dreamteam.command;

import dreamteam.coverter.ConvertJsonToString;
import dreamteam.dto.User;
import dreamteam.service.UserService;
import org.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named("update")
@RequestScoped
public class Update implements Command {
    @Inject
    private UserService userService;
    @Inject
    private User user;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jObj = (JSONObject) new JSONObject(ConvertJsonToString.convertBody(req)).get("user");
        this.user.setId(jObj.getInt("id"));
        this.user.setName(jObj.getString("name"));
        this.user.setSurname(jObj.getString("surname"));
        this.user.setAge(jObj.getInt("age"));
        this.user.setEmail(jObj.getString("email"));
        try {
            this.userService.updateUser(user);
            String str = new JSONObject(user).toString();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(str);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
