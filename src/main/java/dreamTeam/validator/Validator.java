package dreamTeam.validator;

public class Validator {

    public static boolean errorName(String name){
        String reg = "^[A-Z][a-zA-z]{2,20}|[А-ЯҐЄІЇЎ][\\p{IsCyrillic}]{2,20}+$";
        return !name.matches(reg);
    }

    public static boolean errorEmail(String email){
        String reg = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return !email.matches(reg);
    }

    public static boolean errorAge(String age){
        String reg = "^[1-9][0-9]?$";
        return !age.matches(reg);
    }
}
