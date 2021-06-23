package dreamTeam.front_controller;


import dreamTeam.domain.Invoker;
import dreamTeam.commands.*;
import dreamTeam.service.ServletService;
import dreamTeam.service.ServletServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * {ip_address:port}/rest/persons
 * Post - adding new user
 * Get - receiving a user or users
 * Delete - deleting a user
 * Put - changing a user
 */

@WebServlet("/rest/persons")
public class MainController extends HttpServlet {
    Invoker invoker = null;

    @Override
    public void init() throws ServletException {
        ServletService servletService = new ServletServiceImpl();
        invoker = new Invoker(new AddUserCommand(servletService),
                new GetUserCommand(servletService),
                new UpdateUserCommand(servletService),
                new DeleteUserCommand(servletService));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        invoker.getUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        invoker.addUser(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        invoker.updateUser(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        invoker.deleteUser(req, resp);
    }

}
