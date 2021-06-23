package dreamTeam.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ServletService {
    void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
