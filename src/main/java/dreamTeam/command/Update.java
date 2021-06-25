package dreamTeam.command;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;
import dreamTeam.service.UserService;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class Update implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        JSONObject jObj = new Converter().conversionToJsonObj(req);
        user.setId(jObj.getString("id"));
        user.setName((String) jObj.get("name"));
        user.setSurname((String) jObj.get("surname"));
        user.setAge((String) jObj.get("age"));
        user.setEmail((String) jObj.get("email"));
        boolean updateUserField = new UserService(new UserDAO()).updateUser(user);
        if (updateUserField) {
            String str = new JSONObject(user).toString();
            SettingsResponseServlet.setResponse(resp,str);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
