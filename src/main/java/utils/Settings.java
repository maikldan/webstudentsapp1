package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Student on 2/8/2017.
 */
public class Settings {
    private static Connection connection;
        public static Connection getConnection() throws SQLException {
            if (connection == null) {
                System.out.println("PostgreSQL JDBC Driver Registered!");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studentapp", "postgres", "postgres");
            }
            return connection;
    }
    public static void resetAll() throws SQLException {
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}
