package dreamTeam.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private final String DB_URL = "jdbc:postgresql://localhost:5432/userdb";
    private final String USER = "postgres";
    private final String PASS = "admin";

    //private final String DB_URL = "jdbc:postgresql://localhost:5432/alex";
    //change DB_URL database userdb->alex
    /*private final String USER = "alex";
    private final String PASS = "1234";*/

    /*private final String DB_URL = "jdbc:postgresql://aaai5s02tybp3s.cjb8szx8ridk.eu-central-1.rds.amazonaws.com:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "DreamTeam123";*/

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
