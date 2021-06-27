package dreamteam.coverter;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class ConvertToJson {

    public static String convertBody(HttpServletRequest request) {
        StringBuilder jb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            return jb.toString();
        } catch (Exception e) {
            System.err.println("Error receive body from json " + e.getMessage());
        }
        return null;
    }
}
