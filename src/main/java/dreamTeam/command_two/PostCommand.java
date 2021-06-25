package dreamTeam.command_two;

import dreamTeam.domain.User;
import dreamTeam.user_validation.UserValidation;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class PostCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, Connection connection) throws IOException {
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
        int id = createUser(new User(), connection);
        user.setId(String.valueOf(id));
        SettingsResponseServlet.setResponse(resp);
    }

    public static int createUser(User user, Connection connection) {
        String insert = "INSERT INTO andersen (name, surname, age, mail) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            int age = Integer.parseInt(user.getAge());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, user.getEmail());
            if (preparedStatement.executeUpdate()!=0) {
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    while (resultSet.next()){
                        int id = resultSet.getInt("id");
                        return id;
                    }
                }catch (SQLException e){
                    System.err.println("Could not create a user");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
