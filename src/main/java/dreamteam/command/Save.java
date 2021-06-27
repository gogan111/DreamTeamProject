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
import java.io.PrintWriter;

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
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            this.validator.validate(user);
            int id = userService.saveUser(user);
            if (id==0){throw new IncorrectDataException("email already exists "+user.getEmail());}
            this.user.setId(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(new JSONObject(user));
        } catch (IOException | IncorrectDataException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String errorValidation = e.getMessage();
            out.write("{" + "error" + ": " + errorValidation + " }");
        }
    }

}

