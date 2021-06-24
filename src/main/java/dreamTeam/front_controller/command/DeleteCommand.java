package dreamTeam.front_controller.command;

import dreamTeam.garbage.Receiver;

public class DeleteCommand implements Command{
    @Override
    public void execute(Receiver receiver) {
        receiver.deleteUser();
    }
}
