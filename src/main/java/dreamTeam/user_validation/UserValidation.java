package dreamTeam.user_validation;

import dreamTeam.domain.User;
import dreamTeam.validator.Validatorr;

import java.io.Serializable;

public class UserValidation implements Serializable {

    private String ageError = "";
    private String nameError = "";
    private String surnameError = "";
    private String emailError = "";
    private boolean error = false;

    public boolean validation(User user) {
        error = false;

        if (Validatorr.ageError(user.getAge())) {
            ageError = "age is not valid";
            error = true;
        }
        if (Validatorr.nameError(user.getName())) {
            nameError = "name is not valid";
            error = true;
        }
        if (Validatorr.nameError(user.getSurname())) {
            surnameError = "surname is not valid";
            error = true;
        }
        if (Validatorr.emailError(user.getEmail())) {
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
