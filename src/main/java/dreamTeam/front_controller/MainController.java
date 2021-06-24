package dreamTeam.front_controller;

import dreamTeam.front_controller.command.Command;
import dreamTeam.front_controller.command.CreateCommand;
import dreamTeam.front_controller.command.DeleteCommand;
import dreamTeam.front_controller.command.GetCommand;
import dreamTeam.front_controller.command.PutCommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * {ip_address:port}/rest/persons
 * Post - adding new user
 * Get - receiving a user or users
 * Delete - deleting a user
 * Put - changing a user
 */

@WebServlet("/rest/persons")
public class MainController extends HttpServlet {
    private final Map<String, Command> controller = new HashMap<>();

    @Override
    public void init() {
        this.controller.put("get", new GetCommand());
        this.controller.put("put", new PutCommand());
        this.controller.put("post", new CreateCommand());
        this.controller.put("delete", new DeleteCommand());
    }

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) {
        this.controller.getOrDefault(req.getMethod().toLowerCase(), receiver -> resp.setStatus(HttpServletResponse.SC_CONFLICT));
    }
}
