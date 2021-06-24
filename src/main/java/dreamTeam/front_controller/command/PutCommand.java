package dreamTeam.front_controller.command;

import dreamTeam.garbege.Receiver;

import java.io.IOException;

public class PutCommand extends Command {
    @Override
    public void execute(Receiver receiver) throws IOException {
        receiver.updateUser();
    }
}
