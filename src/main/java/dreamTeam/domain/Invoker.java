package dreamTeam.domain;

import dreamTeam.execute.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Invoker {
    Command addUser;
    Command getUser;
    Command updateUser;
    Command deleteUser;

    public Invoker(Command addUser, Command getUser, Command updateUser, Command deleteUser) {
        this.addUser = addUser;
        this.getUser = getUser;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addUser.execute(req, resp);
    }

    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getUser.execute(req, resp);
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateUser.execute(req, resp);
    }

    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteUser.execute(req, resp);
    }
}
