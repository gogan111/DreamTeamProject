package ru.zateev.javaee_jdbc.dao;

import ru.zateev.javaee_jdbc.Entity.Person;
import ru.zateev.javaee_jdbc.users.Users;

import java.util.List;

public interface PersonDao {
    public List<Person> personList(Users users);

    public Person getPerson(int id, Users users);

    public boolean personDelete(int id, Users users);

    public boolean saveOrUpdate(Person person, Users users);
}
