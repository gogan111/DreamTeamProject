package dreamTeam.command;

import dreamTeam.service.UserService;
import org.json.JSONArray;

public class GetAllUsersCommands extends Command{


    public GetAllUsersCommands(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {

    }
}
