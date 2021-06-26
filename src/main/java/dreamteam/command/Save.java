package dreamteam.command;

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

@Named("save")
@RequestScoped
public class Save implements Command {
    @Inject
    User user;
    @Inject
    UserService userService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jObj = new JSONObject(GetBody.getBody(req));
        user.setId(jObj.getInt("id"));
        user.setName(jObj.getString("name"));
        user.setSurname(jObj.getString("surname"));
        user.setAge(jObj.getInt("age"));
        user.setEmail(jObj.getString("email"));
        System.out.println(user);
        int id = userService.saveUser(user);
        user.setId(id);
        String json = new JSONObject(user).toString();
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
