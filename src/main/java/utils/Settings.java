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
                connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-217-232-100.eu-west-1.compute.amazonaws.com:5432/d69lpdvnmq016b?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "abasutwqlhnerx", "064c298d1f9a769459655d5dd456a0bf2bb3ab0600ebb520c7915efbe4e7f3c4");
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
