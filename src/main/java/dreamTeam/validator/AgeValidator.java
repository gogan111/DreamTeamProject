package dreamTeam.validator;

import dreamTeam.domain.User;
import dreamTeam.global_exception.IncorrectDataException;

public class AgeValidator implements Validator{

    private static final String REGEX_FOR_AGE = "^[1-9][0-9]?$";
    private Validator nextValidator = new EmailValidator();

    @Override
    public void validate(User user) throws IncorrectDataException {
        if(user.getAge().matches(REGEX_FOR_AGE)){
            if(nextValidator != null){
                nextValidator.validate(user);
            }
        }else{
            throw new IncorrectDataException("Something wrong with your age " + user.getAge());
        }
    }


}
