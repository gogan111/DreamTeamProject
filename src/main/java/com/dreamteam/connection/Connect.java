package com.dreamteam.connection;

import com.dreamteam.users.UserImpl1;
import com.dreamteam.users.Users;
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
