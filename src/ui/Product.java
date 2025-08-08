package ui;

import java.awt.*;
import javax.swing.*;
import ui.MenuGroup.MenuLeft;
import ui.ProductGroup.ProductRight;

public class Product extends JPanel {

    private final ProductRight productRight; // 👈 Add this field

    public Product(JPanel mainPanel, CardLayout layout, UI ui) {
        setLayout(new BorderLayout());

        // === Left Panel ===
        JPanel leftPanel = new MenuLeft(mainPanel, layout, ui);

        // === Right Panel ===
        productRight = new ProductRight(mainPanel, layout, ui); // 👈 Store it

        add(leftPanel, BorderLayout.WEST);
        add(productRight, BorderLayout.CENTER);
    }

    public ProductRight getProductRight() {
        return productRight; // 👈 Allow UI to access this
    }
}
