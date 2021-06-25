package dreamTeam.command;

import dreamTeam.DAO.UserDAO;
import dreamTeam.service.UserService;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class Delete implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp){
        JSONObject jObj = new Converter().conversionToJsonObj(req);
        int id = Integer.parseInt(jObj.getString("id"));
        if (new UserService(new UserDAO()).deleteUser(id)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
}
