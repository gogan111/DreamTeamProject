package dreamTeam.command;

import dreamTeam.DAO.UserDAOImpl;
import dreamTeam.domain.User;
import dreamTeam.service.UserService;
import dreamTeam.service.UserServiceImpl;
import dreamTeam.user_validation.UserValidation;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class Receiver {
    private UserService userService;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private User user;

    public Receiver(HttpServletRequest req, HttpServletResponse resp) {
        userService = new UserServiceImpl(new UserDAOImpl());
        this.req = req;
        this.resp = resp;
        user = new User();
    }

    public void getAllUsers() throws IOException {
        JSONObject jsonObject = new Converter().conversionToJsonObj(req);
        if (!jsonObject.get("id").toString().equals("0")) {
            int id = Integer.parseInt(jsonObject.getString("id"));
            user = new UserServiceImpl(new UserDAOImpl()).getUser(id);
            if (user != null) {
                String str = new JSONObject(user).toString();
                 SettingsResponseServlet.setResponse(resp,str);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            }
        } else {
            List<User> userList = new UserServiceImpl(new UserDAOImpl()).getAllUsers();
            String str = new JSONArray(userList).toString();
            SettingsResponseServlet.setResponse(resp,str);
        }

    }

    public void createUser() throws IOException {
        JSONObject jObj = new Converter().conversionToJsonObj(req);
        UserValidation userValidation = new UserValidation();
        user.setName((String) jObj.get("name"));
        user.setSurname((String) jObj.get("surname"));
        user.setAge((String) jObj.get("age"));
        user.setEmail((String) jObj.get("email"));
        if(userValidation.validation(user)) {
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(new JSONObject(userValidation));
            out.flush();
        }
        int id = new UserServiceImpl(new UserDAOImpl()).createUser(user);

        user.setId(String.valueOf(id));
        SettingsResponseServlet.setResponse(resp);
    }

    public void updateUser() throws IOException {
        JSONObject jObj = new Converter().conversionToJsonObj(req);
        user.setId(jObj.getString("id"));
        user.setName((String) jObj.get("name"));
        user.setSurname((String) jObj.get("surname"));
        user.setAge((String) jObj.get("age"));
        user.setEmail((String) jObj.get("email"));

        boolean updateUserField = new UserServiceImpl(new UserDAOImpl()).updateUser(user);

        if (updateUserField) {
            String str = new JSONObject(user).toString();
            SettingsResponseServlet.setResponse(resp,str);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    public void deleteUser() {
        JSONObject jObj = new Converter().conversionToJsonObj(req);
        int id = Integer.parseInt(jObj.getString("id"));
        boolean delete = new UserServiceImpl(new UserDAOImpl()).deleteUser(id);
        if (delete) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

}
