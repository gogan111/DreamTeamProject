package dreamTeam.command;

import dreamTeam.DAO.UserDAOImpl;
import dreamTeam.service.UserService;
import dreamTeam.service.UserServiceImpl;

public abstract class Command {
    UserService userService;
    Converter converter;

    public Command() {
        this.userService = new UserServiceImpl(new UserDAOImpl());
        this.converter = new Converter();
    }

    abstract void execute ();


}
