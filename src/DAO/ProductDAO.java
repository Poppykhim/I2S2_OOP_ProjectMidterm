package DAO;

import Storage.Product;
import db.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class ProductDAO {

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO product (code, localName, name, quantity, price, category, status, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getCode());
            stmt.setString(2, product.getLocalName());
            stmt.setString(3, product.getName());
            stmt.setInt(4, product.getQuantity());
            stmt.setDouble(5, product.getPrice());
            stmt.setString(6, product.getCategory());
            stmt.setString(7, product.getStatus());
            stmt.setString(8, product.getImagePath());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            // Log the exception or handle it appropriately
            System.err.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM product";

        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String localName = rs.getString("localName");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                String status = rs.getString("status");

                Product p = new Product(id, code, name, localName, quantity, price, category, status, rs.getString("image"));
                productList.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int deleted = stmt.executeUpdate();
            return deleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProductField(int id, String columnName, Object newValue) {
        String sql = "UPDATE product SET " + columnName + " = ? WHERE id = ?";
        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, newValue);
            stmt.setInt(2, id);
            int updated = stmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveCategoryToDatabase(String category) {
        String sql = "INSERT INTO category (name) VALUES (?)";
        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadCategoriesIntoComboBox(JComboBox<String> comboBox, boolean includeAll) {
        String sql = "SELECT name FROM category ORDER BY id ASC"; //ORDER BY name ASC
        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            comboBox.removeAllItems();
            if (includeAll) {
                comboBox.addItem("All"); // Add "All" option if needed
            }
            while (rs.next()) {
                comboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTableByCategory(String categoryName, DefaultTableModel model) {
        model.setRowCount(0); // Clear old data
        String sql = "SELECT id, code, localName, name, quantity, price, category, status FROM product WHERE category = ?";

        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Replace with your actual column data
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("localName"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("status")
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isProductCodeExists(String code) {
        String sql = "SELECT COUNT(*) FROM product WHERE code = ?";
        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<String> fetchCategories() {
        List<String> categories = new ArrayList<>();
        try (Connection con = MyDataBase.getConnection(); PreparedStatement stmt = con.prepareStatement("SELECT name FROM category ORDER BY id ASC"); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Collections.reverse(categories);
        return categories;
    }

    public List<Product> getProductsByCategory(String categoryName) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE category = ?";

        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("localName"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getString("image"));
                productList.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public boolean updateProductField(int id, String fieldName, String value) {
        String sql = "UPDATE product SET " + fieldName + " = ? WHERE id = ?";
        try (Connection conn = MyDataBase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, value);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /*
     -- 🧋 HOT DRINKS
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1001', 'កាហ្វេអាមេរិកាណូក្ដៅ', 'Hot Americano', 10, 1.95, 'Hot Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1002', 'កាអេស្ផ្រេសសូក្ដៅ', 'Hot Espresso', 10, 1.95, 'Hot Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1003', 'កាហ្វេឡាតេក្ដៅ', 'Hot Latte', 10, 2.50, 'Hot Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1004', 'ឡាតេប្រចាំហាងក្ដៅ', 'Hot Signature Latte', 10, 2.50, 'Hot Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1005', 'កាហ្វេម៉ូកាក្ដៅ', 'Hot​ Mocha', 10, 2.50, 'Hot Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1006', 'សូកូឡាក្ដៅ', 'Hot Chocolate', 10, 2.50, 'Hot Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1007', 'តែទឹកដោះគោក្ដៅ', 'Hot Milk Tea', 10, 2.50, 'Hot Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1008', 'កាហ្វេកាពូជីណូក្ដៅ', 'Hot Cappuccino', 10, 2.50, 'Hot Drink', 'Available');

-- 🧊 ICED DRINKS
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1009', 'កាហ្វេខ្មៅទឹកកក', 'Iced Americano', 10, 2.25, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1010', 'កាហ្វេឡាតេ', 'Iced Latte', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1011', 'កាហ្វេកាពូជីណូ', 'Iced Signature Latte', 10, 3.00, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1012', 'ឡាតេប្រចាំហាង', 'Green Tea Latte', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1013', 'កាហ្វេម៉ូកា', 'Iced Mocha', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1014', 'សូកូឡាទឹកកក', 'Iced Chocolate', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1015', 'តែទឹកដោះគោទឹកកក', 'Iced Milk Tea', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1016', 'តែក្រូចឆ្មារ', 'Iced Lemon Tea', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1017', 'ម៉ាឆាឡាតេ', 'Macha Latte', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1018', 'សូដាក្រូចឆ្មារទឹកកក', 'Iced  Lemon Soda', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1019', 'ផាសិនទឹកដោះគោទឹកកក', 'Iced Passion Milk', 10, 2.75, 'Iced Drink', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1020', 'ផាសិនសូដាទឹកកក', 'Iced Passion Soda', 10, 2.75, 'Iced Drink', 'Available');

-- 🍓 SMOOTHIE & FRAPPE
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1021', 'ចេកក្រឡុក', 'Banana Smoothie', 10, 3.75, 'Smoothie', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1022', 'ប័រក្រឡុក', 'Avocado Smoothie', 10, 3.75, 'Smoothie', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1023', 'ផាសិនក្រឡុក', 'Passion Fruit Smoothie', 10, 3.50, 'Smoothie', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1024', 'សូកូឡាក្រឡុក', 'Chocolate Frappe', 10, 3.50, 'Smoothie', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1025', 'តែបៃតងក្រឡុក', 'Green Tea Frappe', 10, 3.25, 'Smoothie', 'Available');
INSERT INTO product (code, localName, name, quantity, price, category, status) VALUES ('BM-1026', 'កាហ្ចេក្រឡុក', 'Coffee Frappe', 10, 3.25, 'Smoothie', 'Available');

     */
 /*  reset the order of the product id
    -- Temporarily disable safe updates
    SET SQL_SAFE_UPDATES = 0;

    -- Reset and reorder IDs
    SET @new_id  = 0;
    UPDATE product SET id = (@new_id := @new_id 
    + 1);

    -- Reset auto
    -increment if needed ALTER TABLE product AUTO_INCREMENT  = 1;

    -- Re
    -enable safe

    updates(optional) 
        SET SQL_SAFE_UPDATES = 1;
        select * from product;
     */
}
