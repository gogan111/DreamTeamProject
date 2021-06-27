package dreamteam.validator;
import dreamteam.dto.User;
import dreamteam.global_exception.IncorrectDataException;

public interface Validator {

    void validate(User user) throws IncorrectDataException, IncorrectDataException;

}
