package com.dreamteam.service;

import com.dreamteam.Entity.Person;
import com.dreamteam.users.Users;

import java.util.List;

public interface PersonService {

    public List<Person> personList(Users user);

    public Person getPerson(int id, Users users);

    public void personDelete(int id, Users users);

    public int saveOrUpdate(Person person, Users users);
}
