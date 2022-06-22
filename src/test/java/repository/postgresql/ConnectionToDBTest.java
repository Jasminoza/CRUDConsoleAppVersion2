package repository.postgresql;

import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class ConnectionToDBTest {
    private static Connection connection;

    @BeforeClass
    public static void setUp(){
        System.out.println("Connecting to DB...");
        connection = ConnectionToDB.getConnection();
    }

    @Test
    public void connectionShouldBeNotNullTest() {
        assertNotNull(connection);
    }

    @AfterClass
    public static void tearDown() {
        try {
            System.out.println("Disconnecting from DB...");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
