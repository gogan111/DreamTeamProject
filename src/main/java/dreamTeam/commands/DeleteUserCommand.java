package dreamTeam.commands;

import dreamTeam.service.ServletService;
import dreamTeam.service.ServletServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand implements Command {
    ServletService servletService;

    public DeleteUserCommand(ServletService servletService) {
        this.servletService = servletService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletService.doDelete(req, resp);
    }
}