package dreamTeam.front_controller.command;

import dreamTeam.garbege.Receiver;

public class DeleteCommand implements Command{
    @Override
    public void execute(Receiver receiver) {
        receiver.deleteUser();
    }
}
