package repository.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
    private static String URL = "jdbc:postgresql://localhost:5432/CRUDConsoleApp";
    private static String USER = "postgres";
    private static String PASSWORD = "postgres";

    public static Connection getConnection() {
        return connection;
    }

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
