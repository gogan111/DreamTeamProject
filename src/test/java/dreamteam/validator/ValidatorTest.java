package dreamteam.validator;

import dreamteam.dto.User;
import dreamteam.exception.IncorrectDataException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTest {
    EmptyFieldsValidator validator = new EmptyFieldsValidator();

    @Test
    public void checkIncorrectNames() {
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(1, "Jo1n", "Smith", 30, "john@smith.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(2, "Ma%ry", "Brown", 30, "mary@brown.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(3, "J!", "Black", 30, "jason@black.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(4, "8Nancy", "White", 30, "nancy@white.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(5, "Sama*)ntha", "Davis", 30, "samantha@davis.com")));
    }

    @Test
    public void checkIncorrectSurnames() {
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(1, "John", "Smi1th", 30, "john@smith.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(2, "Mary", "Bro&wn", 30, "mary@brown.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(3, "Jason", "999", 30, "jason@black.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(4, "Nancy", "%White", 30, "nancy@white.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(5, "Samantha", "Da*vis", 30, "samantha@davis.com")));
    }

    @Test
    public void checkIncorrectAges() {
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(1, "John", "Smith", -5, "john@smith.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(2, "Mary", "Brown", 300, "mary@brown.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(3, "Jason", "Black", 230, "jason@black.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(4, "Nancy", "White", 101, "nancy@white.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(5, "Samantha", "Davis", -1, "samantha@davis.com")));
    }

    @Test
    public void checkIncorrectEmails() {
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(1, "John", "Smith", 30, "johnsmith.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(2, "Mary", "Brown", 300, "mary@brown.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(3, "Jason", "Black", 30, "jason@blackcom")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(4, "Nancy", "White", 30, "nan@cy@white.com")));
        assertThrows(IncorrectDataException.class, () -> validator.validate(new User(5, "Samantha", "Davis", 30, "samantha@dav!is.com")));
    }

    @Test
    public void allCorrect() {
        assertDoesNotThrow(() -> validator.validate(new User(1, "John", "Smith", 30, "john@smith.com")));
        assertDoesNotThrow(() -> validator.validate(new User(2, "Mary", "Brown", 30, "mary@brown.com")));
        assertDoesNotThrow(() -> validator.validate(new User(3, "Jason", "Black", 30, "jason@black.com")));
        assertDoesNotThrow(() -> validator.validate(new User(4, "Nancy", "White", 30, "nancy@white.com")));
        assertDoesNotThrow(() -> validator.validate(new User(5, "Samantha", "Davis", 30, "samantha@davis.com")));
    }
}






