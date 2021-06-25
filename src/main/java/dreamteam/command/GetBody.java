package dreamteam.command;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class GetBody {
    public static String getBody(HttpServletRequest request) {
        StringBuilder jb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            return jb.toString();
        } catch (Exception e) { /*report an error*/ }

    return null;
    }
}