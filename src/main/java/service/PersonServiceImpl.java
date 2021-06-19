package ru.zateev.javaee_jdbc.service;

import ru.zateev.javaee_jdbc.Entity.Person;
import ru.zateev.javaee_jdbc.dao.PersonDao;
import ru.zateev.javaee_jdbc.dao.PersonDaoImpl;
import ru.zateev.javaee_jdbc.users.Users;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    @Override
    public List<Person> personList(Users user) {
        return new PersonDaoImpl().personList(user);
    }

    @Override
    public Person getPerson(int id, Users users) {
        return new PersonDaoImpl().getPerson(id, users);
    }

    @Override
    public void personDelete(int id, Users users) {
        boolean delete = new PersonDaoImpl().personDelete(id, users);
    }

    @Override
    public void saveOrUpdate(Person person, Users users) {
        boolean saveOrUpdate = new PersonDaoImpl().saveOrUpdate(person, users);
    }

}
