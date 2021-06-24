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



    public static void selectCommand(String command,
                                     HttpServletRequest request,
                                     HttpServletResponse resp) throws IOException {
       Receiver receiver = new Receiver(request,resp);
        switch (command) {
            case ("get"):
                new GetCommand().execute(receiver);
                break;
            case ("put"):
                new PutCommand().execute(receiver);
                break;
            case ("post"):
                new CreateCommand().execute(receiver);
                break;
            case ("delete"):
                new DeleteCommand().execute(receiver);
                break;
        }

    }

}
