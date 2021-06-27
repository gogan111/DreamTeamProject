package dreamteam.validator;

import dreamteam.dto.User;
import dreamteam.global_exception.IncorrectDataException;

public class SurnameValidator implements Validator {

    private Validator nextValidator = new AgeValidator();
    private final String REGEX_FOR_SURNAME = "^[a-zA-Z '.-]*$";

    @Override
    public void validate(User user) throws IncorrectDataException, IncorrectDataException {
        if(user.getSurname().matches(REGEX_FOR_SURNAME)){
            if(nextValidator != null){
                nextValidator.validate(user);
            }
        }else{
            throw new IncorrectDataException("Something wrong with your surname " + user.getSurname());
        }
    }
}
