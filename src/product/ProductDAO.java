package product;

import db.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO product (name, quantity, price, category) VALUES (?, ?, ?, ?)";

        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getQuantity());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getCategory());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            // Log the exception or handle it appropriately
            System.err.println("SQLException: " + e.getMessage());
            return false;
        }
    }
}
