package dreamteam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


public interface Command {

     void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException;

     default String getBody(HttpServletRequest req){
          StringBuilder jb = new StringBuilder();
          String line = null;
          try {
               BufferedReader reader = req.getReader();
               while ((line = reader.readLine()) != null) {
                    jb.append(line);
               }
               return jb.toString();
          } catch (Exception e) { /*report an error*/ }
          return null;
     }

}
