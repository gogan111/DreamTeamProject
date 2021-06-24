package dreamTeam.front_controller.command;

import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Converter {

    public JSONObject conversionToJsonObj(HttpServletRequest req) {
        return new JSONObject(getBody(req));
    }

    public String getBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();) {
            if (inputStream != null) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
                    char[] charBuffer = new char[128];
                    int bytesRead = -1;
                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return stringBuilder.toString();
            }
        } catch (IOException ex) {
            System.err.println("Проблемы с символьным потоком");
        }

        return null;
    }
}
