package dreamTeam.front_controller.command;

import dreamTeam.garbage.Receiver;

import java.io.IOException;

public class GetCommand implements Command{
    
    @Override
    public void execute(Receiver receiver) {
        try {
            receiver.getAllUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
