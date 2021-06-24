package dreamTeam.command;

import java.io.IOException;

public class GetCommand extends Command{
    
    @Override
    public void execute(Receiver receiver) {
        try {
            receiver.getAllUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
