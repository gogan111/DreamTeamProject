package dreamTeam.command;

import dreamTeam.DAO.UserDAOImpl;
import dreamTeam.service.UserService;
import dreamTeam.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private Map<String, Command> commands;
    private Receiver receiver;

    public Invoker(HttpServletRequest req, HttpServletResponse resp) {
        receiver = new Receiver(req, resp);
        commands = new HashMap<>();
        commands.put("get", new GetCommand());
        commands.put("post", new CreateCommand());
        commands.put("put", new PutCommand());
        commands.put("delete", new DeleteCommand());
    }

    public void selectCommand(String command) throws IOException {
        commands.get(command).execute(receiver);
    }

}
