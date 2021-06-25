package dreamTeam.validator;

import dreamTeam.domain.User;
import dreamTeam.global_exception.IncorrectDataException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NewValidatorTest {
    private final UserValidator validator = new UserValidator();
    private User user;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        user = new User();
        user.setName("Nikolai");
        user.setSurname("Uteshev");
        user.setAge("21");
        user.setEmail("nik@mail.ru");
    }

    @Test
    public void testUserFieldsValidationCorrectUserSuccess() throws IncorrectDataException {
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithIncorrectEmail() throws IncorrectDataException {
        thrown.expect(IncorrectDataException.class);
        user.setEmail("somemail.koljollok");
        thrown.expectMessage("Something wrong with your email " + user.getEmail());
        validator.validate(user);
    }


    @Test
    public void testUserFieldsValidationFailsWithIncorrectName() throws IncorrectDataException {
        thrown.expect(IncorrectDataException.class);
        user.setName("koli12koli");
        thrown.expectMessage("Something wrong with your name " + user.getName());
        validator.validate(user);
    }



    @Test
    public void testUserFieldsValidationFailsWithIncorrectLastName() throws IncorrectDataException {
        thrown.expect(IncorrectDataException.class);
        user.setSurname("123");
        thrown.expectMessage("Something wrong with your surname " + user.getSurname());
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithEmptyLastName() throws IncorrectDataException {
        thrown.expect(IncorrectDataException.class);
        user.setSurname("");
        thrown.expectMessage("You should fill all fields");
        validator.validate(user);
    }

}
