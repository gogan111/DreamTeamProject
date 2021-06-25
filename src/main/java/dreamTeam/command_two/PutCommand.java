package dreamTeam.command_two;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;
import dreamTeam.service.UserService;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PutCommand implements Command {
    @Override
    public void execute (HttpServletRequest req, HttpServletResponse resp, Connection connection) throws IOException {
        User user = new User();
        JSONObject jObj = new Converter().conversionToJsonObj(req);
        user.setId(jObj.getString("id"));
        user.setName((String) jObj.get("name"));
        user.setSurname((String) jObj.get("surname"));
        user.setAge((String) jObj.get("age"));
        user.setEmail((String) jObj.get("email"));
        boolean updateUserField = updateUser(user, connection);
        if (updateUserField) {
            String str = new JSONObject(user).toString();
            SettingsResponseServlet.setResponse(resp,str);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    public static boolean updateUser(User user, Connection connection) {
        String updateUsr = "UPDATE andersen SET (name, surname, age, mail) = (?, ?, ?, ?) WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateUsr)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, Integer.parseInt(user.getAge()));
            statement.setInt(5, Integer.parseInt(user.getId()));
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("User does not exist.");
            e.printStackTrace();
        }
        return false;
    }
}
