package dreamTeam.user_validation;


import dreamTeam.domain.User;
import dreamTeam.validator.Validator;

import java.io.Serializable;

public class UserValidation implements Serializable {

    public String ageError = "";
    public String nameError = "";
    public String surnameError = "";
    public String emailError = "";
    public boolean validation(User user) {
        if(Validator.ageError(user.getAge())){
            ageError = "age is not valid";
        }
        if(Validator.nameError(user.getName())){
            nameError = "name is not valid";
        }
        if(Validator.nameError(user.getSurname())){
            surnameError = "surname is not valid";
        }
        if(Validator.emailError(user.getEmail())){
            emailError = "email is not valid";
        }
        return ageError.equals("") && nameError.equals("") && surnameError.equals("") && emailError.equals("");
    }

    public String getAgeError() {
        return ageError;
    }

    public String getNameError() {
        return nameError;
    }

    public String getSurnameError() {
        return surnameError;
    }

    public String getEmailError() {
        return emailError;
    }
}
