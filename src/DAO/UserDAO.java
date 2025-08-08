package DAO;

import Storage.User;
import db.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean Register(User user) {
        // Validate input first

        String sql = "INSERT INTO user (username, password,email) VALUES (?, ?, ?)";

        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername().trim());
            stmt.setString(2, user.getPassword().trim());
            stmt.setString(3, user.getEmail().trim());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Insert error: " + e.getMessage());
            return false;
        }
    }

    public static boolean Login(User user) {
        // Validate input

        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername().trim());
            stmt.setString(2, user.getPassword().trim());

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
            return false;
        }
    }

    public static boolean usernameExists(String username) {
        String sql = "SELECT 1 FROM user WHERE username = ?";
        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username.trim());
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if username already exists

        } catch (SQLException e) {
            System.err.println("Check error: " + e.getMessage());
            return true; // treat error as if name exists to be safe
        }
    }

}
