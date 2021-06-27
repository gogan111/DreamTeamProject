package dreamteam.command;

import dreamteam.dto.User;
import dreamteam.service.UserService;
import org.json.JSONArray;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Named("read")
@RequestScoped
public class Read implements Command {
    @Inject
    UserService userService;

    @Override
    public void  execute(HttpServletRequest req, HttpServletResponse resp)  {
        List<User> userList = userService.getAllUsers();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(new JSONArray(userList).toString());
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}