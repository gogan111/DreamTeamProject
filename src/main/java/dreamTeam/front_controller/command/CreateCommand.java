package dreamTeam.front_controller.command;

import dreamTeam.garbage.Receiver;

import java.io.IOException;

public class CreateCommand implements Command{
    @Override
    public void execute(Receiver receiver) throws IOException {
        receiver.createUser();
    }
}
