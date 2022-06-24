package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToMySQL {
    private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/CRUDApplicationMySQL";
    private static final String USER = "root@localhost";
    private static final String PASSWORD = "Java22!mysql";
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
