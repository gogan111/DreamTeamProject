package dreamTeam.front_controller;


import dreamTeam.DAO.UserDAOImpl;
import dreamTeam.command.Converter;
import dreamTeam.command.Invoker;
import dreamTeam.domain.User;
import dreamTeam.service.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * {ip_address:port}/rest/persons
 * Post - adding new user
 * Get - receiving a user or users
 * Delete - deleting a user
 * Put - changing a user
 */

@WebServlet("/rest/persons")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        Invoker invoker = new Invoker(req,resp);
        invoker.selectCommand("get");

    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        Invoker invoker = new Invoker(req,resp);
        invoker.selectCommand("post");
    }

    @Override
    protected void doPut(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        Invoker invoker = new Invoker(req,resp);
        invoker.selectCommand("put");
    }

    @Override
    protected void doDelete(HttpServletRequest req,
                            HttpServletResponse resp) throws ServletException, IOException {
        Invoker invoker = new Invoker(req,resp);
        invoker.selectCommand("delete");
    }

}
