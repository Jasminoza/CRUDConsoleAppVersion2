package service;

import org.junit.Test;
import utils.ConnectionToMySQL;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionToMySQLTest {
    private static Connection connection;
    @Test
    public void checkConnectionTest() {
        System.out.println("Connecting to DB...");
        connection = ConnectionToMySQL.getConnection();
        System.out.println("Connection established.");
        try {
            System.out.println("Disconnecting from DB...");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
