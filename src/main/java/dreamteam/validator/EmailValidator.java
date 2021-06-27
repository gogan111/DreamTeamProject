package dreamteam.validator;

import dreamteam.dto.User;
import dreamteam.exception.IncorrectDataException;

public class EmailValidator implements Validator{
    private Validator nextValidator;
    private static final String REGEX_FOR_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void validate(User user) throws IncorrectDataException, IncorrectDataException {
        if(user.getEmail().matches(REGEX_FOR_EMAIL)){
            if(nextValidator != null){
                nextValidator.validate(user);
            }
        }else{
            throw new IncorrectDataException("Something wrong with your email " + user.getEmail());
        }
    }
}
