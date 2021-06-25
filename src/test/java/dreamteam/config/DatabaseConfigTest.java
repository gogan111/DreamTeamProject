package dreamteam.config;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DatabaseConfigTest {
    DatabaseConfig databaseConfig;

    @BeforeEach
    public void init (){
        databaseConfig = new DatabaseConfig();
    }

//    @Test
//    void testExceptionConnection() throws SQLException {
//        DatabaseConfig databaseConfig = mock(DatabaseConfig.class);
//        Mockito.when(databaseConfig.getConnection())
//                .thenThrow(new SQLException("Нет соединения с БД"));
//        SQLException sqlException = Assertions.assertThrows(SQLException.class, () -> {
//            databaseConfig.getConnection();
//        });
//        Assertions.assertNotNull(sqlException.getMessage());
//    }

    @Test
    public void connectionByDatabaseTest(){
        Assertions.assertNotNull(databaseConfig.getConnection(), "Соединение не создано");
    }


}
