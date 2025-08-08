/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.ProductGroup;

import DAO.ProductDAO;
import Storage.Product;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import ui.UI;

public class ProductRight extends javax.swing.JPanel {

    /**
     * Creates new form ProductRight
     */
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private UI ui;
    private ProductRight productRight;
    private DefaultTableModel model;
    private Object oldValue = null;

    public ProductRight(JPanel mainPanel, CardLayout cardLayout, UI ui) {
        initComponents();
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.ui = ui;
        loadProductTable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        AddProductLabel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        DeleteBtn = new javax.swing.JButton();
        UploadNewPicture = new javax.swing.JButton();
        CategoryBox = new javax.swing.JComboBox<>();
        CategoryBox.setName("CategoryBox");

        jRadioButton1.setText("jRadioButton1");

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel2.setText("Product");

        AddProductLabel.setBackground(new java.awt.Color(255, 189, 89));
        AddProductLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        AddProductLabel.setText("Add product");
        AddProductLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProductLabelActionPerformed(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                int col = jTable1.columnAtPoint(evt.getPoint());

                if (row >= 0 && col >= 0) {
                    oldValue = jTable1.getValueAt(row, col);
                }
            }
        });
        jScrollPane1.setViewportView(jTable1);

        DeleteBtn.setBackground(new java.awt.Color(255, 189, 89));
        DeleteBtn.setText("Delete");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        UploadNewPicture.setBackground(new java.awt.Color(255, 189, 89));
        UploadNewPicture.setText("Change image");

        UploadNewPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadNewPictureActionPerformed(evt);
            }
        });

        CategoryBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"All"}));
        CategoryBox.addActionListener(e -> {
            String selectedCategory = (String) CategoryBox.getSelectedItem();
            ProductDAO dao = new ProductDAO();
            if ("All".equals(selectedCategory)) {
                loadProductTable(false); // Show all rows
            } else {
                dao.loadTableByCategory(selectedCategory, model); // Filter by category
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(CategoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(UploadNewPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 592, Short.MAX_VALUE)
                                                .addComponent(AddProductLabel)))
                                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(AddProductLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(UploadNewPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CategoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    private void AddProductLabelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ui.fadeSwitchTo("AddProduct");
    }

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        deleteSelectedRow(jTable1, model);
    }

    private void UploadNewPictureActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        UploadNewPictureSelectedRow(jTable1, model);
    }

    public void loadProductTable(boolean loadCategory) {
        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAllProducts();

        String[] columnNames = {
            "ID", "Code", "LocalName", "Name", "Quantity", "Price", "Category", "Status"
        };

        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Prevent editing ID column
            }
        };
        jTable1.setModel(model); // `jTable1` is your JTable
        for (Product p : products) {
            Object[] row = {
                p.getId(),
                p.getCode(),
                p.getLocalName(),
                p.getName(),
                p.getQuantity(),
                p.getPrice(),
                p.getCategory(),
                p.getStatus()
            };
            model.addRow(row);
        }

        jTable1.setModel(model); // `productTable` is your JTable
        customizeTable();
        addTableModelListener();
        if (loadCategory) {
            loadCategoriesIntoComboBox();
        }
        AddProduct.styleComboBox(CategoryBox);
    }

    public ProductRight getProductRight() {
        return productRight;
    }

    private void UploadNewPictureSelectedRow(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a product.");
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0); // Assuming 'id' is column 0

        // Prompt user to pick a new image
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select New Product Image");

        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();
        ImageIcon previewIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath())
                .getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));

        // Ask for confirmation with preview
        int confirm = JOptionPane.showConfirmDialog(
                null,
                new JLabel("Confirm image change:", previewIcon, JLabel.CENTER),
                "Confirm Update",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            String fileName = System.currentTimeMillis() + "_" + selectedFile.getName();
            File destFolder = new File("images");
            if (!destFolder.exists()) {
                destFolder.mkdirs();
            }

            File destFile = new File(destFolder, fileName);
            try {
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String newPath = "images/" + fileName;
                ProductDAO dao = new ProductDAO();
                // Update database image path
                boolean success = dao.updateProductField(id, "image", newPath);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Image updated successfully.");
                    loadProductTable(false); // Refresh the table
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update image in database.");
                }

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to copy new image.");
            }
        }
    }

    public void deleteSelectedRow(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString()); // assuming ID is column 0
        ProductDAO dao = new ProductDAO();
        if (dao.deleteProduct(id)) {
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(null, "Row deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete row.");
        }
    }

    public void addTableModelListener() {
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int col = e.getColumn();

            if (col >= 0) {
                Object newValue = model.getValueAt(row, col);
                int id = (int) model.getValueAt(row, 0); // Safe now
                String columnName = model.getColumnName(col);
                if (newValue == null || newValue.toString().trim().isEmpty()
                        || newValue.toString().equals(oldValue.toString())) {
                    System.out.println("Skipping update for empty or unchanged value.");
                    return;
                }
                ProductDAO dao = new ProductDAO();
                boolean success = dao.updateProductField(id, columnName, newValue);

                if (success) {
                    JOptionPane.showMessageDialog(null,
                            columnName + " updated successfully for ID " + id + ".");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Update failed for ID " + id + ".");
                }
            }
        });
    }

    private void customizeTable() {
        jTable1.setRowHeight(28);
        jTable1.setFont(new Font("Khmer OS Siemreap", Font.PLAIN, 14));
        jTable1.setForeground(Color.BLACK);
        jTable1.setSelectionBackground(new Color(255, 219, 130));
        jTable1.setSelectionForeground(Color.BLACK);
        jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.LIGHT_GRAY);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(15);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);

        jTable1.getTableHeader().setFont(new Font("Khmer OS Siemreap", Font.BOLD, 16));
        jTable1.getTableHeader().setBackground(new Color(255, 189, 89));
        jTable1.getTableHeader().setForeground(Color.BLACK);

        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.WHITE);
                } else {
                    c.setBackground(new Color(255, 219, 130));
                }
                return c;
            }
        });
    }

    public void loadCategoriesIntoComboBox() {
        ProductDAO dao = new ProductDAO();
        dao.loadCategoriesIntoComboBox(CategoryBox, true);
    }

    public JComboBox<String> getCategoryBox() {
        return CategoryBox;
    }

    public void reloadCategoryComboBox() {
        ProductDAO dao = new ProductDAO();
        CategoryBox.removeAllItems(); // Replace with the actual variable name
        dao.loadCategoriesIntoComboBox(CategoryBox, false);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton AddProductLabel;
    private javax.swing.JComboBox<String> CategoryBox;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton UploadNewPicture;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
}
