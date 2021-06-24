package dreamTeam.front_controller.command;

import dreamTeam.garbege.Receiver;

import java.io.IOException;

@FunctionalInterface
public interface Command {
    void execute(Receiver receiver) throws IOException;
}
