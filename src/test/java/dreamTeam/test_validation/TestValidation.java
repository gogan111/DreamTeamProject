package dreamTeam.test_validation;

import dreamTeam.TestingLists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TestValidation {


    List<String> listOfCorrectNameSurname;
    List<String> listOfIncorrectNameSurname;
    List<Integer> listOfCorrectAges;
    List<Integer> listOfIncorrectAges;
    List<String> listOfCorrectEmails;
    List<String> listOfIncorrectEmails;

    @Before
    public void setUpLists(){
        listOfCorrectNameSurname = TestingLists.listOfCorrectNameSurname();
        listOfIncorrectNameSurname = TestingLists.listOfIncorrectNameSurname();
        listOfCorrectAges = TestingLists.listOfCorrectAges();
        listOfIncorrectAges = TestingLists.listOfIncorrectAges();
        listOfCorrectEmails = TestingLists.listOfCorrectEmails();
        listOfIncorrectEmails = TestingLists.listOfIncorrectEmails();
    }

    @Test
    public void testCorrectNameSurname() {

        for(String correctNameSurname : listOfCorrectNameSurname){

        }

    }

    @Test
    public void testIncorrectNameSurname(){

        for(String incorrectNameSurname: listOfIncorrectNameSurname){

        }
    }

    @Test
    public void testCorrectAges() {

        for(int correctAge : listOfCorrectAges){

        }

    }

    @Test
    public void testIncorrectAges(){


        for(int incorrectAge : listOfIncorrectAges){

        }



    }

    @Test
    public void testCorrectEmails() {

        for(String correctEmails : listOfCorrectEmails){

        }

    }

    @Test
    public void testIncorrectEmails(){

        for(String incorrectEmails: listOfIncorrectEmails){

        }

    }
}
