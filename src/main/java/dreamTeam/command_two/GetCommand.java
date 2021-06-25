package dreamTeam.command_two;

import dreamTeam.DAO.UserDAO;
import dreamTeam.domain.User;
import dreamTeam.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, Connection connection) throws IOException {
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
            List<User> userList = getAllUsers(connection);
            String str = new JSONArray(userList).toString();
            SettingsResponseServlet.setResponse(resp, str);
        }
    }

    public static List<User> getAllUsers(Connection connection) {
        String query = "SELECT id,name,surname,age,mail FROM andersen ORDER BY id";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            List<User> usersList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(String.valueOf(resultSet.getInt(1)));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setAge(String.valueOf(resultSet.getInt(4)));
                user.setEmail(resultSet.getString(5));
                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
            System.err.println("Could not get a list of the users.");
            e.printStackTrace();
        }
        return null;
    }
}