package com.dreamteam.dao;


import com.dreamteam.Entity.Person;
import com.dreamteam.users.Users;

import java.util.List;

public interface PersonDao {
    public List<Person> personList(Users users);

    public Person getPerson(int id, Users users);

    public boolean personDelete(int id, Users users);

    public int saveOrUpdate(Person person, Users users);
}
