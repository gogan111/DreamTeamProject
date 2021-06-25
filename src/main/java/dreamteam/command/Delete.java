package dreamteam.command;

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

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jObj = new JSONObject(GetBody.getBody(req));
        String email= jObj.getString("email");
        if (userService.deleteUser(email)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
