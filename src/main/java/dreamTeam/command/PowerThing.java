package dreamTeam.command;

import dreamTeam.service.UserService;
import org.json.JSONArray;

public class PowerThing {
    UserService userService;
    Converter converter;


    public PowerThing(UserService userService) {
        this.userService = userService;
    }

    public String executeGetAllUsers() {
        String allUsers = new JSONArray(userService.getAllUsers()).toString();
        System.out.println("Все users получены");
        return allUsers;
    }


}
