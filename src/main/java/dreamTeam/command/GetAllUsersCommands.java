package dreamTeam.command;

import dreamTeam.service.UserService;
import org.json.JSONArray;

import java.io.IOException;

public class GetAllUsersCommands extends Command{
    
    @Override
    public void execute(Receiver receiver) {
        try {
            receiver.executeGetAllUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
