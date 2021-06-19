package ru.zateev.javaee_jdbc.dao;

import ru.zateev.javaee_jdbc.Entity.Person;
import ru.zateev.javaee_jdbc.connection.Connect;
import ru.zateev.javaee_jdbc.users.Users;

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
    public boolean saveOrUpdate(Person person, Users users) {
        try (Connection connection = new Connect().newInstance()) {
            try {
                PreparedStatement ps;
                if (person.getId() != 0) {
                    ps = connection.prepareStatement(users.getUPDATE_DATA() + person.getId());
                } else {
                    ps = connection.prepareStatement(users.getSAVE_DATA(), Statement.RETURN_GENERATED_KEYS);
                }
                ps.setString(1, person.getName());
                ps.setString(2, person.getSurname());
                ps.setInt(3, person.getAge());
                ps.setString(4, person.getMail());
                ps.executeUpdate();


            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
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


}
