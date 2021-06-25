package dreamteam.command;

import dreamteam.dto.User;
import dreamteam.service.UserService;
import dreamteam.user_validation.UserValidation;
import org.json.JSONObject;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Named("saveUser")
public class Save implements Command {
    @Inject
    User user;
    @Inject
    UserService userService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jObj = new JSONObject(req);
        UserValidation userValidation = new UserValidation();
        user.setName((String) jObj.get("name"));
        user.setSurname((String) jObj.get("surname"));
        user.setAge((Integer) jObj.get("age"));
        user.setEmail((String) jObj.get("email"));

//            PrintWriter out = resp.getWriter();
//            resp.setContentType(req.getContentType());
//            resp.setCharacterEncoding(req.getCharacterEncoding());
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            out.print(new JSONObject(userValidation));
//            out.flush();

        int id = userService.saveUser(user);
        user.setId(id);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
    public  void setOkResponse(HttpServletResponse resp)  {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    public  void setOkResponse (HttpServletResponse resp, String str) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        out.print(str);
        out.flush();
    }
}
