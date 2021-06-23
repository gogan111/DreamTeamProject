package dreamTeam.command;

import dreamTeam.DAO.UserDAOImpl;
import dreamTeam.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class Invoker {

    private Map<String, Command> commands;


    public Invoker() {
        commands = new HashMap<>();
        commands.put("get", new GetAllUsersCommands(new UserServiceImpl(new UserDAOImpl())));
    }

    public static void selectCommand (String command){

    }

}
