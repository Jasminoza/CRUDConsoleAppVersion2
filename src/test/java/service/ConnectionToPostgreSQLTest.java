package service;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionToPostgreSQLTest {
    private static Connection connection;
    @Test
    public void checkConnectionTest() {
        System.out.println("Connecting to DB...");
        connection = ConnectionToPostgreSQL.getConnection();
        try {
            System.out.println("Disconnecting from DB...");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}