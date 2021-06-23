package dreamTeam.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // TODO: change this fields next time
    //change DB_URL database userdb->alex
    private final String DB_URL = "jdbc:postgresql://37.139.171.136:5432/userdb";
    private final String USER = "postgres";
    private final String PASS = "admin";
    /* private final String DB_URL = "jdbc:postgresql://localhost:5432/alex";
     private final String USER = "alex";
     private final String PASS = "1234";
 */
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
