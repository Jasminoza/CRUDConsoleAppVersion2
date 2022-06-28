package utils;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToMySQL extends ConnectionImpl {
    private static ConnectionToMySQL connectionToMySQL;

    private ConnectionToMySQL() {
        try {
            String databaseUrl = "jdbc:mysql://localhost:3306/CRUDApplicationMySQL";
            String user = "root@localhost";
            String password = "Java22!mysql";
            connectionToMySQL = (ConnectionToMySQL) DriverManager.getConnection(databaseUrl, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Connection getConnection() {
        if (connectionToMySQL == null) {
            connectionToMySQL = new ConnectionToMySQL();
        }
        return connectionToMySQL;
    }
}
