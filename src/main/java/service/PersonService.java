package ru.zateev.javaee_jdbc.service;

import ru.zateev.javaee_jdbc.Entity.Person;
import ru.zateev.javaee_jdbc.users.Users;

import java.util.List;

public interface PersonService {

    public List<Person> personList(Users user);

    public Person getPerson(int id, Users users);

    public void personDelete(int id, Users users);

    public void saveOrUpdate(Person person, Users users);
}
