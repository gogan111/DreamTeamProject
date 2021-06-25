package dreamTeam.validator;

import dreamTeam.domain.User;
import dreamTeam.global_exception.IncorrectDataException;

public class UserValidator implements Validator {

    private Validator nextValidator = new EmptyFieldsValidator();

    @Override
    public void validate(User user) throws IncorrectDataException {
        if(user != null){
            if(nextValidator != null){
                nextValidator.validate(user);
            }
        }else{
            throw new IncorrectDataException("User can't be null");
        }
    }
}
