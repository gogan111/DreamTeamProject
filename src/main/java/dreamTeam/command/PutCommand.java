package dreamTeam.command;

import java.io.IOException;

public class PutCommand extends Command {
    @Override
    public void execute(Receiver receiver) throws IOException {
        receiver.updateUser();
    }
}
