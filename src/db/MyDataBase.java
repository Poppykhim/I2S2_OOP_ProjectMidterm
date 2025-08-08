package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDataBase {

    private static String URL;


    
    private static String USER;
    private static String PASS;

    static {
        try (FileInputStream fis = new FileInputStream("config/db.properties")) {
            Properties props = new Properties();
            props.load(fis);

            URL = "jdbc:mysql://localhost:3306/test";
            USER = "root";
            PASS = props.getProperty("db.password");

        } catch (IOException e) {
            System.out.println("Failed to load DB Password: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}
