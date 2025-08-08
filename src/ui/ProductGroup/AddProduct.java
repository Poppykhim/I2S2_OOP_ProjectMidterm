/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.ProductGroup;

import DAO.ProductDAO;
import Netbean.MenuSection;
import Storage.Product;
import db.MyDataBase;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import ui.UI;

/**
 *
 * @author User
 */
public class AddProduct extends javax.swing.JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private UI ui;
    private MenuSection menuSection; // Reference to MenuSection

    public AddProduct(JPanel mainPanel, CardLayout cardLayout, UI ui) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.ui = ui;
        initComponents();
        loadCategoriesIntoComboBox();
    }

    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CancelBtn = new RoundedButton("Cancel", 15);
        SaveBtn = new RoundedButton("Save", 15);
        jLabel2 = new javax.swing.JLabel();
        PCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PName = new javax.swing.JTextField();
        PName1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        PQty = new javax.swing.JTextField();
        PPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PStatus = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        PImage = new javax.swing.JLabel();
        PGroup = new javax.swing.JComboBox<>();
        PGroup.setName("PGroupComboBox");

        NewGroup = new RoundedButton("New Category", 15);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1000, 568));

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 28)); // NOI18N
        jLabel1.setText("Product");

        CancelBtn.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        CancelBtn.setForeground(new java.awt.Color(255, 189, 89));
        CancelBtn.setText("Cancel");
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });

        SaveBtn.setBackground(new java.awt.Color(255, 189, 89));
        SaveBtn.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        SaveBtn.setText("Save");
        SaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel2.setText("Code");

        PCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PCodeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel3.setText("Name");

        PName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNameActionPerformed(evt);
            }
        });

        PName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PName1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel4.setText("Name*");

        PQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PQtyActionPerformed(evt);
            }
        });

        PPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPriceActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel5.setText("Retail Price");

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel6.setText("Stock Qty");

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel7.setText("Product Group");

        PStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PStatusActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel8.setText("Status");
        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel9.setText("Upload Photo");

        PImage.setFont(new java.awt.Font("Segoe UI Emoji", 1, 20)); // NOI18N
        PImage.setText("  Pictures"); // ← text that will show before upload
        PImage.setIcon(new ImageIcon(getClass().getResource("/Images/flag-regular.png"))); // optional default image
        PImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Product Image");

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();

                    File destFolder = new File("images");
                    if (!destFolder.exists()) {
                        destFolder.mkdirs();
                    }

                    // Keep same timestamp for both destFile and saved path
                    String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                    File destFile = new File(destFolder, uniqueFileName);

                    try {
                        Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        // ✅ Correct: use same path you saved to
                        PImage.putClientProperty("imagePath", "images/" + uniqueFileName);

                        // UI updates
                        PImage.setText("Upload");
                        PImage.setIcon(resizeImage(destFile.getAbsolutePath(), 100, 100));
                        PImage.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(null, "Failed to upload image.");
                    }
                }
            }
        });

        PStatus.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[]{"Available", "Out of Stock"}));
        styleComboBox(PStatus);
        PGroup.setModel(new javax.swing.DefaultComboBoxModel<>());
        styleComboBox(PGroup);

        JPopupMenu PopupMenu = new JPopupMenu();

        JMenuItem deleteItem = new JMenuItem("🗑 Delete Category");
        JMenuItem renameItem = new JMenuItem("✏ Rename Category");
        PopupMenu.add(deleteItem);
        PopupMenu.add(renameItem);

        PGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    PopupMenu.show(PGroup, e.getX(), e.getY());
                    PopupMenu.show(PGroup, e.getX(), e.getY());
                }
            }
        });
        deleteItem.addActionListener(e -> {
            String selectedCategory = (String) PGroup.getSelectedItem();
            if (selectedCategory.equalsIgnoreCase("All")) {
                JOptionPane.showMessageDialog(null, "You cannot delete the 'All' category.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete the category: " + selectedCategory + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                // 1. Remove from database
                try (Connection conn = MyDataBase.getConnection()) {
                    String sql = "DELETE FROM category WHERE name = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, selectedCategory);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to delete category.");
                    return;
                }

                // 2. Remove from combo box
                PGroup.removeItem(selectedCategory);

                //update
                ui.getProduct().getProductRight().reloadCategoryComboBox();
                ui.getMenu().getMiddlePanel().loadCategoryButtons();
            }
        });
        renameItem.addActionListener(e -> {
            String selectedCategory = (String) PGroup.getSelectedItem();
            if (selectedCategory.equalsIgnoreCase("All")) {
                JOptionPane.showMessageDialog(null, "You cannot rename the 'All' category.");
                return;
            }

            String newName = JOptionPane.showInputDialog(null, "Enter new name for category:", selectedCategory);
            if (newName != null && !newName.trim().isEmpty()) {
                // 1. Update in database
                try (Connection conn = MyDataBase.getConnection()) {
                    String sql = "UPDATE category SET name = ? WHERE name = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newName.trim());
                    stmt.setString(2, selectedCategory);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to rename category.");
                    return;
                }

                // 2. Update in combo box
                PGroup.removeItem(selectedCategory);
                PGroup.addItem(newName.trim());
                PGroup.setSelectedItem(newName.trim());
                //update
                ui.getProduct().getProductRight().reloadCategoryComboBox();
                ui.getMenu().getMiddlePanel().loadCategoryButtons();

            }
        });

        NewGroup.setBackground(new java.awt.Color(255, 189, 89));
        NewGroup.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        NewGroup.setText("New Category");
        NewGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(CancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(SaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NewGroup)
                                                .addGap(33, 33, 33))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(PCode, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                                                .addComponent(jLabel5)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(PPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                                                .addComponent(jLabel6)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(PQty, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(1, 1, 1))
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(PImage)
                                                                                        .addComponent(PStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                                .addComponent(jLabel7)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(PGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(87, 87, 87))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(PName1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(PName, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(546, 546, 546))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(CancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(NewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(PCode, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)
                                        .addComponent(PGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(PName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(35, 35, 35)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(PName1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(PQty, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(35, 35, 35)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(PPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(PStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(PImage))
                                .addContainerGap(95, Short.MAX_VALUE))
        );

        CancelBtn.setBackground(new Color(255, 255, 255));
        PCode.setText("  Generate by system if blank");
        PCode.setForeground(Color.GRAY);
        PCode.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (PCode.getText().equals("  Generate by system if blank")) {
                    PCode.setText("");
                    PCode.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (PCode.getText().isEmpty()) {
                    PCode.setText("  Generate by system if blank");
                    PCode.setForeground(Color.GRAY);
                }
            }
        });
        PName.setText("  Product Name");
        PName.setForeground(Color.GRAY);
        PName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (PName.getText().equals("  Product Name")) {
                    PName.setText("");
                    PName.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (PName.getText().isEmpty()) {
                    PName.setText("  Product Name");
                    PName.setForeground(Color.GRAY);
                }
            }
        });
        PName1.setText("  Product Name in Local");
        PName1.setForeground(Color.GRAY);
        PName1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (PName1.getText().equals("  Product Name in Local")) {
                    PName1.setText("");
                    PName1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (PName1.getText().isEmpty()) {
                    PName1.setText("  Product Name in Local");
                    PName1.setForeground(Color.GRAY);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>                        

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ui.getProduct().getProductRight().loadProductTable(false);
        clearInputFields();
        ui.fadeSwitchTo("Product");
    }

    private void SaveBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        handleAddProduct();
        clearInputFields();
    }

    private void PCodeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void PNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void PName1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void PQtyActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void PPriceActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void PStatusActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String selectedStatus = (String) PStatus.getSelectedItem();
        if ("Out of Stock".equalsIgnoreCase(selectedStatus)) {
            PStatus.setBackground(Color.RED);
        } else {
            PStatus.setBackground(new Color(255, 189, 89)); // original color
        }
    }

    private void NewGroupActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String newCategory = JOptionPane.showInputDialog(
                null,
                "Enter new category name:",
                "Add New Category",
                JOptionPane.PLAIN_MESSAGE);
        // Check if already exists in comboBox
        for (int i = 0; i < PGroup.getItemCount(); i++) {
            if (PGroup.getItemAt(i).equalsIgnoreCase(newCategory)) {
                JOptionPane.showMessageDialog(null, "Category already exists!");
                return;
            }
        }
        ProductDAO dao = new ProductDAO();

        if (newCategory != null && !newCategory.trim().isEmpty()) {
            newCategory = newCategory.trim();
            dao.saveCategoryToDatabase(newCategory);

            // 2. Add to JComboBox (and optionally select it)
            PGroup.addItem(newCategory);
            PGroup.setSelectedItem(newCategory);
            ui.getProduct().getProductRight().reloadCategoryComboBox();
            ui.getMenu().getMiddlePanel().loadCategoryButtons();
        }
    }

    private void loadCategoriesIntoComboBox() {
        ProductDAO dao = new ProductDAO();
        PGroup.removeAllItems();
        dao.loadCategoriesIntoComboBox(PGroup, false);

    }

    private void handleAddProduct() {
        ProductDAO dao = new ProductDAO();
        String code = PCode.getText().trim();
        if (code.equals("Generate by system if blank") || code.isEmpty()) {
            // User left it blank, so we generate a code
            code = generateProductCode();

            // Double check if the new code somehow already exists
            if (dao.isProductCodeExists(code)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "System-generated product code already exists. Please try again.",
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            PCode.setText(code); // Show the code in the text field
        } else {
            // User entered a custom code manually — check if it already exists
            if (dao.isProductCodeExists(code)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Product code already exists. Please enter a unique code.",
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String name = PName.getText().trim();
        String localName = PName1.getText().trim();
        String group = (String) PGroup.getSelectedItem();
        String qtyStr = PQty.getText().trim();
        String priceStr = PPrice.getText().trim();
        String status = (String) PStatus.getSelectedItem();
        String imagePath = (String) PImage.getClientProperty("imagePath");// This should contain the image path
        if (!(imagePath == null || imagePath.isBlank())) {
            System.out.println("Image path: " + imagePath);
            System.out.println("get the path");
        } else {
            imagePath = "images/1753664187921_No_Image.jpg"; // fallback default
        }
        int quantity;
        double price;

        try {
            quantity = Integer.parseInt(qtyStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid quantity or price format.", "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create Product object and save it
        Product product = new Product(0, code, name, localName, quantity, price, group, status, imagePath);
        ProductDAO productDAO = new ProductDAO();

        if (productDAO.addProduct(product)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Product added successfully.", "Success",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            ui.getProduct().getProductRight().loadProductTable(false); // Refresh the product table
            ui.fadeSwitchTo("Product");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Failed to add product.", "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateProductCode() {
        ProductDAO dao = new ProductDAO();
        String code;
        int maxAttempts = 100; // prevent infinite loop

        do {
            int randomNum = (int) (Math.random() * 9000) + 1000; // 1000–9999
            code = "BM-" + randomNum;
            maxAttempts--;
        } while (dao.isProductCodeExists(code) && maxAttempts > 0);

        return code;
    }

    public static void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setForeground(new Color(50, 50, 50));
        comboBox.setBackground(new Color(255, 189, 89));
        comboBox.setFocusable(false);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Padding using custom renderer
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
                return label;
            }
        });
    }

    public ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    private void clearInputFields() {
        PCode.setText("  Generate by system if blank");
        PCode.setForeground(Color.GRAY);

        PName.setText("  Product Name");
        PName.setForeground(Color.GRAY);

        PName1.setText("  Product Name in Local");
        PName1.setForeground(Color.GRAY);

        PQty.setText("");
        PPrice.setText("");

        PImage.setText("  Pictures"); // reset hidden path
        PImage.setIcon(new ImageIcon(getClass().getResource("/Images/flag-regular.png")));

    }

    public class RoundedButton extends JButton {

        private final int radius;
        private Color borderColor = new Color(255, 189, 89); // default border color
        private int borderThickness = 3; // border thickness

        public RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false); // prevent default background fill
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            // Border
            g2.setStroke(new BasicStroke(borderThickness));
            g2.setColor(borderColor);
            g2.drawRoundRect(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness,
                    getHeight() - borderThickness, radius, radius);

            g2.dispose();

            super.paintComponent(g);
        }

        @Override
        public void setBackground(Color bg) {
            super.setBackground(bg);
            repaint();
        }

        public void setBorderColor(Color color) {
            this.borderColor = color;
            repaint();
        }

        public void setBorderThickness(int thickness) {
            this.borderThickness = thickness;
            repaint();
        }
    }

    public MenuSection getMenuSection() {
        return menuSection;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton CancelBtn;
    private javax.swing.JButton NewGroup;
    private javax.swing.JTextField PCode;
    private javax.swing.JComboBox<String> PGroup;
    private javax.swing.JLabel PImage;
    private javax.swing.JTextField PName;
    private javax.swing.JTextField PName1;
    private javax.swing.JTextField PPrice;
    private javax.swing.JTextField PQty;
    private javax.swing.JComboBox<String> PStatus;
    private javax.swing.JButton SaveBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration                   
}
