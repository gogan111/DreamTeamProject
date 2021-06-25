package dreamTeam.command;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;
import dreamTeam.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class GetCommand implements Command {
    @Override
    public void  execute(HttpServletRequest req, HttpServletResponse resp, Connection connection) throws IOException {
        User user;
        JSONObject jsonObject = new Converter().conversionToJsonObj(req);
        if (!jsonObject.get("id").toString().equals("0")) {
            int id = Integer.parseInt(jsonObject.getString("id"));
            user = new UserService(new UserDAO()).getUser(id);
            if (user != null) {
                String str = new JSONObject(user).toString();
                SettingsResponseServlet.setResponse(resp, str);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            }
        } else {
            List<User> userList = new UserService(new UserDAO()).getAllUsers();
            String str = new JSONArray(userList).toString();
            SettingsResponseServlet.setResponse(resp, str);
        }
    }
}