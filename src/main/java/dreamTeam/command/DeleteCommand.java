package dreamTeam.command;

public class DeleteCommand extends Command{
    @Override
    public void execute(Receiver receiver) {
        receiver.deleteUser();
    }
}
