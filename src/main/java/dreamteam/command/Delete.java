package dreamteam.command;

import dreamteam.constants.Constants;
import dreamteam.coverter.ConvertJsonToString;
import dreamteam.exception.IncorrectDataException;
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
        String bodyRequires = ConvertJsonToString.convertBody(req);
        try {
            JSONObject jObj = (JSONObject) new JSONObject(bodyRequires).get(Constants.USER);
            String email = jObj.getString(Constants.EMAIL);
            if(!this.userService.deleteUser(email)) {
                throw new IncorrectDataException("Incorrect delete");
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
