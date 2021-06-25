package dreamTeam.command_two;

import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, Connection connection) {
        JSONObject jObj = new Converter().conversionToJsonObj(req);
        int id = Integer.parseInt(jObj.getString("id"));
        if (deleteUser(id, connection)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    public static boolean deleteUser(int userId, Connection connection) {
        String delete = "DELETE FROM andersen WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setInt(1, userId);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("User did not exist.");
            e.printStackTrace();
        }
        return false;
    }
}
