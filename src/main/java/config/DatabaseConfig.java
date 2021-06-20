package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private final String DB_URL = "jdbc:postgresql://localhost:5432/usersdb";  // TODO: change this fields next time
    private final String USER = "postgres";
    private final String PASS = "...";
    Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
                e.printStackTrace();

            }
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                System.out.println("Connection Failed");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
