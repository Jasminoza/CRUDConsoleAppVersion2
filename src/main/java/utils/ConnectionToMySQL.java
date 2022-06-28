package utils;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToMySQL extends ConnectionImpl {
    private static Connection connectionToMySQL;

    private ConnectionToMySQL() {
    }

    public static synchronized Connection getConnection() {
        if (connectionToMySQL == null) {
            try {
                String jdbcDriver = "com.mysql.cj.jdbc.Driver";
                String databaseUrl = "jdbc:mysql://localhost:3306/CRUDApplicationMySQL";
                String user = "root@localhost";
                String password = "Java22!mysql";
                Class.forName(jdbcDriver);
                connectionToMySQL = DriverManager.getConnection(databaseUrl, user, password);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connectionToMySQL;
    }
}
