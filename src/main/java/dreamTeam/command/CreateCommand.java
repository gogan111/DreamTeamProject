package dreamTeam.command;

import java.io.IOException;

public class CreateCommand extends Command{
    @Override
    public void execute(Receiver receiver) {
        receiver.createUser();
    }
}
