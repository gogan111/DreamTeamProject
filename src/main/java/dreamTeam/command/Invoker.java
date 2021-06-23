package dreamTeam.command;

import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private Map<String, Command> commands;
    Receiver receiver;

    public Invoker(Receiver receiver) {
        this.receiver = receiver;
        commands = new HashMap<>();
        commands.put("get", new GetUsersCommands());
        commands.put("post", new CreateCommand());
        commands.put("put", new PutCommand());
        commands.put("delete", new DeleteCommand());
    }

    public  void selectCommand (String command){
        commands.get(command).execute(receiver);
    }

}
