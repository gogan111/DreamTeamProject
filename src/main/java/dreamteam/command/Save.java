package dreamteam.command;

import dreamteam.constants.Constants;
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
        String bodyRequires = ConvertJsonToString.convertBody(req);
        JSONObject jObj = (JSONObject) new JSONObject(bodyRequires).get(Constants.USER);
        this.user.setName(jObj.getString(Constants.NAME));
        this.user.setSurname(jObj.getString(Constants.SURNAME));
        this.user.setAge(jObj.getInt(Constants.AGE));
        this.user.setId(jObj.getInt(Constants.ID));
        this.user.setEmail(jObj.getString(Constants.EMAIL));
        resp.setContentType(Constants.CONTENT_TYPE);
        resp.setCharacterEncoding(Constants.CHARACTER_ENCODING);
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            this.validator.validate(user);
            int id = userService.saveUser(user);
            if (id == 0) {
                throw new IncorrectDataException("email already exists " + user.getEmail());
            }
            this.user.setId(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(new JSONObject(user));
            out.flush();
        } catch (IOException | IncorrectDataException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String errorValidation = e.getMessage();
            out.print(new JSONObject(String.format("{\"error\": \"%s\" }", errorValidation)));
            out.flush();
        }
    }
}

