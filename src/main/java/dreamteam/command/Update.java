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

    /*
     * пример json: {
     * "surname": "Zateevvv",
     * "name": "Andrey",
     * "id":1,
     * "email": "ss@gmail.com",
     * "age": 44
     * }
     */

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jObj = new JSONObject(ConvertToJson.convertBody(req));
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
