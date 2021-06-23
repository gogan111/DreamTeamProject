package dreamTeam.user_validation;


import dreamTeam.GlobalException.GlobalException;
import dreamTeam.GlobalException.IncorrectMailException;
import dreamTeam.domain.User;
import dreamTeam.validator.Validator;

import javax.json.bind.annotation.JsonbTypeSerializer;
import java.beans.Transient;
import java.io.Serializable;

public class UserValidation implements Serializable {

    public String errorAge = "";
    public String errorName = "";
    public String errorSurname = "";
    public String errorEmail = "";
    public boolean validation(User user) {
        if(Validator.errorAge(user.getAge())){
            errorAge = "age is not valid";
        }
        if(Validator.errorName(user.getName())){
            errorName = "name is not valid";
        }
        if(Validator.errorName(user.getSurname())){
            errorSurname = "surname is not valid";
        }
        if(Validator.errorEmail(user.getEmail())){
            errorEmail = "email is not valid";
        }
        return errorAge.equals("") && errorName.equals("") && errorSurname.equals("") && errorEmail.equals("");
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
