package dreamTeam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public interface Command {

     void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
