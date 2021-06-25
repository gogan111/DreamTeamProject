package dreamteam.user_validation;

import dreamteam.dto.User;
import dreamteam.validator.Validator;

import java.io.Serializable;

public class UserValidation implements Serializable {

    private String ageError = "";
    private String nameError = "";
    private String surnameError = "";
    private String emailError = "";
    private boolean error = false;

    public boolean validation(User user) {
        error = false;

        if (Validator.ageError(String.valueOf(user.getAge()))) {
            ageError = "age is not valid";
            error = true;
        }
        if (Validator.nameError(user.getName())) {
            nameError = "name is not valid";
            error = true;
        }
        if (Validator.nameError(user.getSurname())) {
            surnameError = "surname is not valid";
            error = true;
        }
        if (Validator.emailError(user.getEmail())) {
            emailError = "email is not valid";
            error = true;
        }
        return error;
    }

    public boolean isError() {
        return error;
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
