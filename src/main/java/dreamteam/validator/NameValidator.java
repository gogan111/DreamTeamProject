package dreamteam.validator;

import dreamteam.dto.User;
import dreamteam.global_exception.IncorrectDataException;

public class NameValidator implements Validator{

    private Validator nextValidator = new SurnameValidator();
    private final String REGEX_FOR_NAME = "^[a-zA-Z '.-]*$";

    @Override
    public void validate(User user) throws IncorrectDataException {

        if(user.getName().matches(REGEX_FOR_NAME)){
            if(nextValidator != null){
                nextValidator.validate(user);
            }
        }else{
            throw new IncorrectDataException("Something wrong with your name " + user.getName());
        }
    }
}
