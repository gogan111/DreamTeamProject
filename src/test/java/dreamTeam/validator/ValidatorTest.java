package dreamTeam.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidatorTest {

    @Test
    public void checkCorrectName() {
        List<String> strings = new ArrayList<>();
        strings.add("Johny");
        strings.add("Depp");
        strings.add("Leonardo");
        strings.add("Dicaprio");
        for (String name : strings) {
            Assert.assertFalse(Validator.errorName(name));
        }
    }

    @Test
    public void checkCorrectEmail() {
        List<String> strings = new ArrayList<>();
        strings.add("11tehno11@ukr.net");
        strings.add("test@test.test");
        strings.add("7up@gmail.com");
        strings.add("must@have.ru");
        for (String email : strings) {
            Assert.assertFalse(Validator.errorEmail(email));
        }
    }

    @Test
    public void checkCorrectAge() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("99");
        strings.add("25");
        strings.add("75");
        for (String age : strings) {
            Assert.assertFalse(Validator.errorAge(age));
        }
    }

    @Test
    public void checkUnCorrectName() {
        List<String> strings = new ArrayList<>();
        strings.add("sdffff");
        strings.add("125");
        strings.add("@dd23%");
        strings.add("&$#@!))*");
        for (String name : strings) {
            Assert.assertTrue(Validator.errorName(name));
        }
    }

    @Test
    public void checkUnCorrectEmail() {
        List<String> strings = new ArrayList<>();
        strings.add("11tehno11@ukrnet");
        strings.add("testtest.test");
        strings.add("7upgmailcom");
        strings.add("#$@@###>##");
        for (String email : strings) {
            Assert.assertTrue(Validator.errorEmail(email));
        }
    }

    @Test
    public void checkUnCorrectAge() {
        List<String> strings = new ArrayList<>();
        strings.add("adsd");
        strings.add("@4341123$");
        strings.add("-12");
        strings.add("120");
        for (String age : strings) {
            Assert.assertTrue(Validator.errorAge(age));
        }
    }

}
