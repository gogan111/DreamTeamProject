package dreamTeam.command;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;
import dreamTeam.service.UserService;
import dreamTeam.user_validation.UserValidation;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public class Create implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        JSONObject jObj = new Converter().conversionToJsonObj(req);
        UserValidation userValidation = new UserValidation();
        user.setName((String) jObj.get("name"));
        user.setSurname((String) jObj.get("surname"));
        user.setAge((String) jObj.get("age"));
        user.setEmail((String) jObj.get("email"));
        if (userValidation.validation(user)) {
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(new JSONObject(userValidation));
            out.flush();
        }
        int id = new UserService(new UserDAO()).createUser(user);
        user.setId(String.valueOf(id));
        SettingsResponseServlet.setResponse(resp);
    }

}
