package dreamTeam.execute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletOperation {
    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;

    public ServletOperation(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
    }
}
