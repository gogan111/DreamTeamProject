package com.dreamteam.service;

import com.dreamteam.Entity.Person;
import com.dreamteam.dao.PersonDaoImpl;
import com.dreamteam.users.Users;

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
    public int saveOrUpdate(Person person, Users users) {
        return new PersonDaoImpl().saveOrUpdate(person, users);
    }

}
