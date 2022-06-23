package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToPostgreSQL {
    private static final String JDBC_Driver = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/CRUDConsoleApp";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Connection connection;

    static {
        try {
            Class.forName(JDBC_Driver);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
