package com.dreamteam.dao;


import com.dreamteam.Entity.Person;

import com.dreamteam.users.Users;
import com.dreamteam.connection.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    @Override
    public List<Person> personList(Users users) {
        List<Person> personList = new ArrayList<>();

        try (Connection connection = new Connect().newInstance()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps
                         = connection.prepareStatement(users.getSELECT_TABLE())) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setAge(resultSet.getInt("age"));
                    person.setName(resultSet.getString("name"));
                    person.setSurname(resultSet.getString("surname"));
                    person.setMail(resultSet.getString("mail"));
                    personList.add(person);
                }

            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException();
            }
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    @Override
    public Person getPerson(int id, Users users) {
        Person person = new Person();
        String idd = String.valueOf(id);
        try (Connection connection = new Connect().newInstance()) {

            try (PreparedStatement ps = connection.prepareStatement(users.getSELECT_BY_ID() + idd)) {
                ResultSet resultSet = ps.executeQuery();
                System.out.println("sdsdsd");
                while (resultSet.next()) {
                    person.setId(resultSet.getInt("id"));
                    person.setName(resultSet.getString("name"));
                    person.setSurname(resultSet.getString("surname"));
                    person.setAge(resultSet.getInt("age"));
                    person.setMail(resultSet.getString("mail"));
                }



            } catch (NullPointerException e) {
                System.err.println("Проблемы с prepare");
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public boolean personDelete(int id, Users users) {
        try (Connection connection = new Connect().newInstance()) {
            try (PreparedStatement pstmt = connection.prepareStatement(users.getDELETE())) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
                throw new SQLException();

            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int saveOrUpdate(Person person, Users users) {
        int id = 0;
        try (Connection connection = new Connect().newInstance()) {
            try {
                PreparedStatement ps;
                if (person.getId() != 0) {
                    ps = connection.prepareStatement(users.getUPDATE_DATA() + person.getId());
                    id = person.getId();
                } else {
                    ps = connection.prepareStatement(users.getSAVE_DATA(), Statement.RETURN_GENERATED_KEYS);




                }
                ps.setString(1, person.getName());
                ps.setString(2, person.getSurname());
                ps.setInt(3, person.getAge());
                ps.setString(4, person.getMail());
                ps.executeUpdate();
                if (id == 0) {
                    ResultSet resultSet = ps.getGeneratedKeys();
                    while (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                }

            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException();
            }
            connection.commit();
            connection.setAutoCommit(true);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
