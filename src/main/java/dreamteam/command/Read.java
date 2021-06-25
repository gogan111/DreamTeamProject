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
    public void  execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> userList = userService.getAllUsers();
        String arrayUser = new JSONArray(userList).toString();
        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_OK);
        out.print(arrayUser);
        out.flush();
    }
}