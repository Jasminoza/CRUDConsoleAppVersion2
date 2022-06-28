package utils;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionToMySQLTest {

    @Test
    public void shouldGetConnection() {
        Connection connection = ConnectionToMySQL.getConnection();
        try {
            Assert.assertTrue(connection.isValid(1));
            Assert.assertFalse(connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}