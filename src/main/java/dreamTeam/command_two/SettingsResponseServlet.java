package dreamTeam.command_two;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SettingsResponseServlet {

    public static void setResponse(HttpServletResponse resp)  {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    public static void setResponse (HttpServletResponse resp, String str) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        out.print(str);
        out.flush();
    }

}
