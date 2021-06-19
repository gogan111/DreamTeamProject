package ru.zateev.javaee_jdbc.connection;

import ru.zateev.javaee_jdbc.users.UserImpl1;
import ru.zateev.javaee_jdbc.users.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public  Connection connection;

    public Connect() {
    }

    public  synchronized Connection newInstance() {
        if (connection != null) {
            return connection;
        }
        else {
            Users users = new UserImpl1();
            try {
                Class.forName(users.getDRIVER());
                connection = DriverManager.getConnection(users.getURL()
                        , users.getUser()
                        , users.getPass());
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                System.out.println("Connect");
                return connection;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
