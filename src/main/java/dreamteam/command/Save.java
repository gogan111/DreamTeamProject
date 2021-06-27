package dreamteam.command;

import dreamteam.coverter.ConvertJsonToString;
import dreamteam.dto.User;
import dreamteam.exception.IncorrectDataException;
import dreamteam.service.UserService;
import dreamteam.validator.EmptyFieldsValidator;
import org.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named("save")
@RequestScoped
public class Save implements Command {
    @Inject
    User user;
    @Inject
    UserService userService;
    @Inject
    EmptyFieldsValidator validator;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jObj = (JSONObject) new JSONObject(ConvertJsonToString.convertBody(req)).get("user");
        this.user.setName(jObj.getString("name"));
        this.user.setSurname(jObj.getString("surname"));
        this.user.setAge(jObj.getInt("age"));
        this.user.setId(jObj.getInt("id"));
        this.user.setEmail(jObj.getString("email"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            this.validator.validate(user);
            int id = userService.saveUser(user);
            System.out.println(id);
            if (id == 0) {
                throw new IncorrectDataException("Email already exists");
            }
            this.user.setId(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(new JSONObject(user).toString());
        } catch (IOException | IncorrectDataException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String errorValidation = e.getMessage();
            try {
                resp.getWriter().write("{" + "error" + ": " + errorValidation + " }");
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

}

