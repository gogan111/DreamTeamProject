package dreamTeam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletOperation {
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;

    public ServletOperation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public void responseData(){

    }

}
