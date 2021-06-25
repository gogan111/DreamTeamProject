package dreamTeam.validator;

import dreamTeam.domain.User;
import dreamTeam.global_exception.IncorrectDataException;

public interface Validator {

    void validate(User user) throws IncorrectDataException;

}
