package dreamteam.command;

import dreamteam.dto.User;
import dreamteam.service.UserService;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named("updateUser")
public class Update implements Command {
    @Inject
    UserService userService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        JSONObject jObj = new JSONObject(req);
        user.setId(jObj.getInt("id"));
        user.setName(jObj.getString("name"));
        user.setSurname(jObj.getString("surname"));
        user.setAge(jObj.getInt("age"));
        user.setEmail(jObj.getString("email"));
//            boolean updateUserField =
        userService.updateUser(user);
//            if (updateUserField) {
//                String str = new JSONObject(user).toString();
//                SettingsResponseServlet.setResponse(resp,str);
//            } else {
//                resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
//            }
    }
}
