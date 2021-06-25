package dreamTeam.command_two;


import dreamTeam.command.Command;
import dreamTeam.command.DeleteCommand;
import dreamTeam.command.GetCommand;
import dreamTeam.command.PostCommand;
import dreamTeam.command.PutCommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * {ip_address:port}/rest/persons
 * Post - adding new user
 * Get - receiving a user or users
 * Delete - deleting a user
 * Put - changing a user
 */

@WebServlet("/rest/persons")
public class MainController extends HttpServlet {
    Command command;
    Connection connection;

    public void init(ServletConfig config){
        try{
            ServletContext context = config.getServletContext();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    context.getInitParameter("dbUrl"),
                    context.getInitParameter("dbUser"),
                    context.getInitParameter("dbPassword") );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        this.command = new GetCommand();
        this.command.execute(req, resp, this.connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.command = new PostCommand();
        this.command.execute(req, resp, this.connection);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.command = new PutCommand();
        this.command.execute(req, resp, this.connection);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.command = new DeleteCommand();
        command.execute(req, resp, this.connection);
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
