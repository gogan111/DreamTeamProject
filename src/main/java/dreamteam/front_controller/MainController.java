package dreamteam.front_controller;


import dreamteam.command.Command;
import dreamteam.command.Save;
import dreamteam.command.Delete;
import dreamteam.command.Read;
import dreamteam.command.Update;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * {ip_address:port}/rest/persons
 * Post - adding new user
 * Get - receiving a user or users
 * Delete - deleting a user
 * Put - changing a user
 */

@WebServlet("/rest/persons")
public class MainController extends HttpServlet {
    Map<String, Command> controller;
    @Inject @Named("saveUser")
    Command saveUser;
    @Inject @Named("readUsers")
    Command readUser;
    @Inject @Named("deleteUser")
    Command deleteUser;
    @Inject @Named("updateUser")
    Command updateUser;
    
    
    @Override
    public void init() {
        controller = new HashMap<>();
        this.controller.put("getUsers", readUser);
        this.controller.put("saveUser", saveUser);
        this.controller.put("deleteUser", deleteUser);
        this.controller.put("updateUser", updateUser);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        this.controller.get("getUsers").execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.controller.get("saveUser").execute(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.controller.get("updateUser").execute(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.controller.get("deleteUser").execute(req, resp);
    }

    @Override
    public void destroy() {
        controller = null;
    }
}
