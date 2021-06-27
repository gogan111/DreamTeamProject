package dreamteam.validator;
import dreamteam.dto.User;
import dreamteam.exception.IncorrectDataException;

public interface Validator {

    void validate(User user) throws IncorrectDataException, IncorrectDataException;

}
