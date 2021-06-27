package dreamteam.command;

import dreamteam.coverter.ConvertToJson;
import dreamteam.service.UserService;
import org.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named("delete")
@RequestScoped
public class Delete implements Command {
    @Inject
    UserService userService;

    /*
     * пример json: {
     * "email": "ss@gmail.com"
     * }
     */

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jObj = new JSONObject(ConvertToJson.convertBody(req));
        String email = jObj.getString("email");
        if (userService.deleteUser(email)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
