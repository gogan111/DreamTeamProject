package dreamteam.command;

import dreamteam.dto.User;
import dreamteam.service.UserService;
import org.json.JSONObject;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Named("update")
public class Update implements Command {
    @Inject
    private UserService userService;
    @Inject
    private User user;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jObj = new JSONObject(req);
        user.setId(jObj.getInt("id"));
        user.setName(jObj.getString("name"));
        user.setSurname(jObj.getString("surname"));
        user.setAge(jObj.getInt("age"));
        user.setEmail(jObj.getString("email"));
        int updateUserField = userService.updateUser(user);
        PrintWriter out = resp.getWriter();
        out.print(user);
        out.flush();
        if (updateUserField != 0) {
            String str = new JSONObject(user).toString();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(user);
            out.flush();
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
