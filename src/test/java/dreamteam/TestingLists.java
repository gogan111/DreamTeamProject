package dreamteam;

import java.util.ArrayList;
import java.util.List;

public class TestingLists {

    public static List<String> listOfCorrectNameSurname(){
        List<String> listOfCorrectNameSurname = new ArrayList<String>();

        listOfCorrectNameSurname.add("Nikolai");
        listOfCorrectNameSurname.add("Vladimir");
        listOfCorrectNameSurname.add("Petr");
        listOfCorrectNameSurname.add("Tony");
        listOfCorrectNameSurname.add("Smith");
        listOfCorrectNameSurname.add("Ivanov");
        listOfCorrectNameSurname.add("Petrov");

        return listOfCorrectNameSurname;
    }

    public static List<String> listOfIncorrectNameSurname(){
        List<String> listOfCorrectNameSurname = new ArrayList<String>();

        listOfCorrectNameSurname.add("1Nikolai");
        listOfCorrectNameSurname.add("_Vlad23imir");
        listOfCorrectNameSurname.add(" ");
        listOfCorrectNameSurname.add("1To_ny2");
        listOfCorrectNameSurname.add("Sm_it_h");
        listOfCorrectNameSurname.add("Ivanov__");
        listOfCorrectNameSurname.add("Petrov12");

        return listOfCorrectNameSurname;
    }

    public static List<Integer> listOfCorrectAges(){
        List<Integer> listOfCorrectAges = new ArrayList<Integer>();

        listOfCorrectAges.add(12);
        listOfCorrectAges.add(45);
        listOfCorrectAges.add(89);
        listOfCorrectAges.add(110);
        listOfCorrectAges.add(24);
        listOfCorrectAges.add(5);
        listOfCorrectAges.add(1);

        return listOfCorrectAges;
    }

    public static List<Integer> listOfIncorrectAges(){
        List<Integer> listOfIncorrectAges = new ArrayList<Integer>();

        listOfIncorrectAges.add(111);
        listOfIncorrectAges.add(405);
        listOfIncorrectAges.add(189);
        listOfIncorrectAges.add(1102);
        listOfIncorrectAges.add(22124);
        listOfIncorrectAges.add(53323);
        listOfIncorrectAges.add(991);

        return listOfIncorrectAges;

    }

    public static List<String> listOfCorrectEmails(){
        List<String> listOfCorrectEmails = new ArrayList<String>();

        listOfCorrectEmails.add("email@example.com");
        listOfCorrectEmails.add("firstname.lastname@example.com");
        listOfCorrectEmails.add("email@subdomain.example.com");
        listOfCorrectEmails.add("email@123.123.123.123");
        listOfCorrectEmails.add("1234567890@example.com");
        listOfCorrectEmails.add("email@example-one.com");
        listOfCorrectEmails.add("_______@example.com");
        listOfCorrectEmails.add("email@example.name");
        listOfCorrectEmails.add("email@example.museum");
        listOfCorrectEmails.add("email@example.co.jp");
        listOfCorrectEmails.add("firstname-lastname@example.com");

        return listOfCorrectEmails;
    }

    public static List<String> listOfIncorrectEmails(){
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

        return listOfIncorrectEmails;
    }
}
