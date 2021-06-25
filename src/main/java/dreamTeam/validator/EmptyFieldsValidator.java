package dreamTeam.validator;

import dreamTeam.domain.User;
import dreamTeam.global_exception.IncorrectDataException;

public class EmptyFieldsValidator implements Validator{

    private static final String EMPTY_STRING = "";
    private static Validator nextValidator = new NameValidator();

    @Override
    public void validate(User user) throws IncorrectDataException {
        if (!user.getName().equals(EMPTY_STRING) && !user.getSurname().equals(EMPTY_STRING)
                && !user.getEmail().equals(EMPTY_STRING) && !user.getAge().equals(EMPTY_STRING)) {
            if (nextValidator != null) {
                nextValidator.validate(user);
            }
        } else {
            throw new IncorrectDataException("You should fill all fields");
        }
    }
}
