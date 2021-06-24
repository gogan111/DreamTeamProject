package dreamTeam.command;

import java.io.IOException;

public abstract class Command {

     abstract void execute (Receiver receiver) throws IOException;
}
