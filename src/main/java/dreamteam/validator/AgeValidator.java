package dreamteam.validator;

import dreamteam.dto.User;
import dreamteam.exception.IncorrectDataException;

public class AgeValidator implements Validator{

    private static final String REGEX_FOR_AGE = "^[1-9][0-9]?$";
    private Validator nextValidator = new EmailValidator();

    @Override
    public void validate(User user) throws IncorrectDataException {
        if(user.getAge() > 0 && user.getAge() < 100){
            if(nextValidator != null){
                nextValidator.validate(user);
            }
        }else{
            throw new IncorrectDataException("Something wrong with your age " + user.getAge());
        }
    }


}
