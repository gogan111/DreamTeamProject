package dreamTeam.validator;

public class Validator {
    private static String error = "";

    public static boolean isNameValid(String name){
        String reg = "^[A-Z][a-zA-z]+$";
        if(!name.matches(reg)){
            error = "The name is not valid. Use only letters";
        }
        return name.matches(reg);
    }

    public static boolean isEmailValid(String email){
        String reg = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        if(!email.matches(reg)){
            error = "The email is not valid. Enter correct email";
        }
        return email.matches(reg);
    }

    public static boolean isAgeValid(String age){
        String reg = "^[1-9][0-9]?$";
        if(!age.matches(reg)){
            error = "The age is not valid. Please enter a number from 1 to 99";
        }
        return age.matches(reg);
    }

    public static String getError(){
        return error;
    }

    public static void clear(){
        error = "";
    }
}
