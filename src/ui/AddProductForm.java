package ui;

import javax.swing.*;
import product.Product;
import product.ProductDAO;
import validator.Validator;

public class AddProductForm extends JFrame {

    private JTextField nameField, qtyField, priceField, categoryField;
    private JButton addButton;

    public AddProductForm() {
        setTitle("Add New Product");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(100, 20, 150, 25);
        add(nameField);

        JLabel qtyLabel = new JLabel("Quantity:");
        qtyLabel.setBounds(20, 60, 80, 25);
        add(qtyLabel);
        qtyField = new JTextField();
        qtyField.setBounds(100, 60, 150, 25);
        add(qtyField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 100, 80, 25);
        add(priceLabel);
        priceField = new JTextField();
        priceField.setBounds(100, 100, 150, 25);
        add(priceField);

        JLabel catLabel = new JLabel("Category:");
        catLabel.setBounds(20, 140, 80, 25);
        add(catLabel);
        categoryField = new JTextField();
        categoryField.setBounds(100, 140, 150, 25);
        add(categoryField);

        addButton = new JButton("Add");
        addButton.setBounds(100, 180, 80, 25);
        add(addButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String qtyStr = qtyField.getText();
            String priceStr = priceField.getText();
            String category = categoryField.getText();

            if (!Validator.isValidName(name)
                    || !Validator.isPositiveInteger(qtyStr)
                    || !Validator.isValidPrice(priceStr)
                    || !Validator.isValidCategory(category)) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int qty = Integer.parseInt(qtyStr);
            double price = Double.parseDouble(priceStr);

            Product product = new Product(0, name, qty, price, category);
            ProductDAO dao = new ProductDAO();
            if (dao.addProduct(product)) {
                JOptionPane.showMessageDialog(this, "Product added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
