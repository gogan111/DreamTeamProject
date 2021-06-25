package dreamTeam.validator;
import dreamTeam.domain.User;
import dreamTeam.global_exception.IncorrectDataException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class NewValidatorTest {
    private final UserValidator validator = new UserValidator();
    private User user;

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

    @Test(expected = IncorrectDataException.class)
    public void testUserFieldsValidationFailsWithIncorrectEmail() throws IncorrectDataException {

        List<String> listOfIncorrectEmails = new ArrayList<String>();
        listOfIncorrectEmails.add("plainaddress");
        listOfIncorrectEmails.add("#@%^%#$@#$@#.com");
        listOfIncorrectEmails.add("@example.com");
        listOfIncorrectEmails.add("Joe Smith <email@example.com>");
        listOfIncorrectEmails.add("email.example.com");
        listOfIncorrectEmails.add("email@example@example.com");
        listOfIncorrectEmails.add("email.@example.com");
        listOfIncorrectEmails.add("email..email@example.com");
        listOfIncorrectEmails.add("email@-example.com");
        listOfIncorrectEmails.add("Abc..123@example.com");
        listOfIncorrectEmails.add("email@111.222.333.44444");
        listOfIncorrectEmails.add("email@example.com (Joe Smith)");
        listOfIncorrectEmails.add("email@example..com");

        for(String s : listOfIncorrectEmails){
            user.setEmail("somemail.koljollok");
            validator.validate(user);
        }

    }


    @Test(expected = IncorrectDataException.class)
    public void testUserFieldsValidationFailsWithIncorrectName() throws IncorrectDataException {
        List<String> listOfIncorrectNameSurname = new ArrayList<String>();
        listOfIncorrectNameSurname.add("12Nikol21ai");
        listOfIncorrectNameSurname.add("_Vlad23imir");
        listOfIncorrectNameSurname.add(" ");
        listOfIncorrectNameSurname.add("1To_ny2");
        listOfIncorrectNameSurname.add("Sm_it_h");
        listOfIncorrectNameSurname.add("Ivanov__");
        listOfIncorrectNameSurname.add("Petrov12");

        for(String s : listOfIncorrectNameSurname){
            user.setName(s);
            validator.validate(user);
        }

    }


    @Test(expected = IncorrectDataException.class)
    public void testUserFieldsValidationFailsWithIncorrectLastName() throws IncorrectDataException {
        List<String> listOfIncorrectNameSurname = new ArrayList<String>();
        listOfIncorrectNameSurname.add("12Nikol21ai");
        listOfIncorrectNameSurname.add("_Vlad23imir");
        listOfIncorrectNameSurname.add(" ");
        listOfIncorrectNameSurname.add("1To_ny2");
        listOfIncorrectNameSurname.add("Sm_it_h");
        listOfIncorrectNameSurname.add("Ivanov__");
        listOfIncorrectNameSurname.add("Petrov12");

        for(String s : listOfIncorrectNameSurname){
            user.setSurname(s);
            validator.validate(user);
        }
    }

    @Test(expected = IncorrectDataException.class)
    public void testUserFieldsValidationFailsWithEmptyLastName() throws IncorrectDataException {
        user.setSurname("");
        validator.validate(user);
    }

}
