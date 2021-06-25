package dreamteam.command;

import dreamteam.service.UserService;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named("deleteUser")
public class Delete implements Command {
    @Inject
    UserService userService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jObj = new JSONObject(req);
        int id = Integer.parseInt(jObj.getString("id"));
        if (userService.deleteUser(id)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
