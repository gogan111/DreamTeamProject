package dreamTeam.user_validation;


import dreamTeam.GlobalException.GlobalException;
import dreamTeam.GlobalException.IncorrectMailException;
import dreamTeam.domain.User;
import dreamTeam.validator.Validator;

import javax.json.bind.annotation.JsonbTypeSerializer;
import java.beans.Transient;
import java.io.Serializable;

public class UserValidation implements Serializable {

    public String errorAge;
    public String errorName;
    public String errorSurname;
    public String errorEmail;

    private boolean boolErrorAge;
    private boolean boolErrorName;
    private boolean boolErrorSurname;
    private boolean boolErrorEmail;

    public int validation(User user) {
        initBooleanError();
        int count = 0;

        if (!Validator.isAgeValid(user.getAge())) {
            errorAge = "Incorrect age";
            ++count;
        }
        if (!Validator.isNameValid(user.getName())) {
            errorName = "Incorrect name";
            ++count;
        }
        if (!Validator.isNameValid(user.getSurname())) {
            errorSurname = "Incorrect surname";
            ++count;
        }
        if (!Validator.isEmailValid(user.getEmail())) {
            System.out.println(Validator.isEmailValid(user.getEmail()));
            errorEmail = "Incorrect email";
            ++count;
            throw new IncorrectMailException("Некорректный mail");

        }
        return count;
    }

    public void initBooleanError() {
        boolErrorAge = true;
        boolErrorName = true;
        boolErrorSurname = true;
        boolErrorEmail = true;
    }

    public String getErrorAge() {
        return errorAge;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorSurname() {
        return errorSurname;
    }

    public String getErrorEmail() {
        return errorEmail;
    }
}
