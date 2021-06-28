package dreamteam.command;

import dreamteam.constants.Constants;
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
        JSONObject jObj = (JSONObject) new JSONObject(ConvertJsonToString.convertBody(req)).get(Constants.USER);
        this.user.setId(jObj.getInt(Constants.ID));
        this.user.setName(jObj.getString(Constants.NAME));
        this.user.setSurname(jObj.getString(Constants.SURNAME));
        this.user.setAge(jObj.getInt(Constants.AGE));
        this.user.setEmail(jObj.getString(Constants.EMAIL));
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
