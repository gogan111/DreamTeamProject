package dreamTeam.execute;

import dreamTeam.service.ServletService;
import dreamTeam.service.ServletServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserCommand implements Command {
    ServletServiceImpl servletService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletService.doPost(req, resp);
    }
}
