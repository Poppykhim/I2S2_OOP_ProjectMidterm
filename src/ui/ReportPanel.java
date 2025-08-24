package ui;

import java.awt.*;
import java.awt.print.PrinterException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ReportPanel extends JPanel {

    private FileStorageManager reportManager;

    // UI Components
    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> tableSorter;
    private JTextField searchField;

    // Date filter components
    private JComboBox<String> quickFilterCombo;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;

    // Summary labels
    private JLabel totalOrdersLabel;
    private JLabel totalSalesLabel;
    private JLabel avgOrderLabel;
    private JLabel totalItemsLabel;

    // Buttons
    private JButton refreshBtn;
    private JButton exportPDFBtn;
    private JButton exportExcelBtn;
    private JButton printBtn;
    private JButton searchBtn;
    private JButton clearSearchBtn;
    private JButton backBtn;
    private UI ui;

    public ReportPanel(JPanel mainPanel, CardLayout layout, UI ui) {
        this.reportManager = FileStorageManager.getInstance();
        this.ui = ui;
        initComponents();
        loadInitialData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create main content
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        // headerPanel.setBorder(BorderFactory.createTitledBorder("Sales Report Dashboard"));

        // Title and summary cards
        JPanel titlePanel = createTitlePanel();
        JPanel summaryPanel = createSummaryCardsPanel();

        headerPanel.add(titlePanel, BorderLayout.NORTH);
        headerPanel.add(summaryPanel, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Sales & Orders Report");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 51, 51));
        panel.add(titleLabel);

        return panel;
    }

    private JPanel createSummaryCardsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Summary cards
        totalOrdersLabel = new JLabel("0");
        totalSalesLabel = new JLabel("$0.00");
        avgOrderLabel = new JLabel("$0.00");
        totalItemsLabel = new JLabel("0");

        panel.add(createSummaryCard("Total Orders", totalOrdersLabel, new Color(52, 152, 219)));
        panel.add(createSummaryCard("Total Sales", totalSalesLabel, new Color(46, 204, 113)));
        panel.add(createSummaryCard("Avg Order", avgOrderLabel, new Color(155, 89, 182)));
        panel.add(createSummaryCard("Items Sold", totalItemsLabel, new Color(241, 196, 15)));

        return panel;
    }

    private JPanel createSummaryCard(String title, JLabel valueLabel, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valueLabel.setForeground(color);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(valueLabel);

        return card;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        // Filter and action panel
        JPanel filterPanel = createFilterPanel();
        centerPanel.add(filterPanel, BorderLayout.NORTH);

        // Orders table
        JPanel tablePanel = createTablePanel();
        centerPanel.add(tablePanel, BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createFilterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Filters & Search"));

        // First row - Date filters
        JPanel dateFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dateFilterPanel.setBackground(Color.WHITE);

        // Quick filter dropdown
        dateFilterPanel.add(new JLabel("Quick Filter:"));
        quickFilterCombo = new JComboBox<>(new String[]{
            "Today", "Yesterday", "This Week", "This Month", "Last 7 Days", "Last 30 Days", "Custom Range"
        });
        quickFilterCombo.addActionListener(e -> handleQuickFilter());
        dateFilterPanel.add(quickFilterCombo);

        dateFilterPanel.add(Box.createHorizontalStrut(20));

        // Custom date range
        dateFilterPanel.add(new JLabel("From:"));
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateSpinner, "dd-MM-yyyy");
        startDateSpinner.setEditor(startEditor);
        startDateSpinner.setValue(java.util.Date.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        dateFilterPanel.add(startDateSpinner);

        dateFilterPanel.add(new JLabel("To:"));
        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateSpinner, "dd-MM-yyyy");
        endDateSpinner.setEditor(endEditor);
        endDateSpinner.setValue(java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        dateFilterPanel.add(endDateSpinner);
        dateFilterPanel.add(Box.createHorizontalStrut(20));

        // Refresh button
        refreshBtn = new JButton("🔄 Refresh");
        refreshBtn.addActionListener(e -> refreshData());
        dateFilterPanel.add(refreshBtn);

        panel.add(dateFilterPanel);

        // Second row - Search and export
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionPanel.setBackground(Color.WHITE);

        // Search functionality
        actionPanel.add(new JLabel("Search Orders:"));
        searchField = new JTextField(15);
        actionPanel.add(searchField);

        searchBtn = new JButton("🔍 Search");
        searchBtn.addActionListener(e -> performSearch());
        actionPanel.add(searchBtn);

        clearSearchBtn = new JButton("❌ Clear");
        clearSearchBtn.addActionListener(e -> clearSearch());
        actionPanel.add(clearSearchBtn);

        actionPanel.add(Box.createHorizontalStrut(20));

        // Export buttons
        exportPDFBtn = new JButton("📄 Export PDF");
        exportPDFBtn.addActionListener(e -> exportToPDF());
        actionPanel.add(exportPDFBtn);

        exportExcelBtn = new JButton("📊 Export Excel");
        exportExcelBtn.addActionListener(e -> exportToExcel());
        actionPanel.add(exportExcelBtn);

        printBtn = new JButton("🖨️ Print");
        printBtn.addActionListener(e -> printReport());
        actionPanel.add(printBtn);

        panel.add(actionPanel);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Order Details"));

        // Create table
        String[] columns = {
            "Receipt #XXXX", "Date", "Time", "Table", "Total Items", "Subtotal", "Discount", "Total", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) {
                    return Integer.class; // Items count

                }
                if (columnIndex >= 5 && columnIndex <= 7) {
                    return Double.class; // Money columns

                }
                return String.class;
            }
        };

        ordersTable = new JTable(tableModel);
        ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ordersTable.setRowHeight(25);
        ordersTable.getTableHeader().setReorderingAllowed(false);

        // Set column widths
        ordersTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // Receipt #
        ordersTable.getColumnModel().getColumn(1).setPreferredWidth(80);  // Date
        ordersTable.getColumnModel().getColumn(2).setPreferredWidth(70);  // Time
        ordersTable.getColumnModel().getColumn(3).setPreferredWidth(60);  // Table
        ordersTable.getColumnModel().getColumn(4).setPreferredWidth(50);  // Items
        ordersTable.getColumnModel().getColumn(5).setPreferredWidth(80);  // Subtotal
        ordersTable.getColumnModel().getColumn(6).setPreferredWidth(70);  // Discount
        ordersTable.getColumnModel().getColumn(7).setPreferredWidth(80);  // Total
        ordersTable.getColumnModel().getColumn(8).setPreferredWidth(70);  // Status

        // Add table sorter
        tableSorter = new TableRowSorter<>(tableModel);
        ordersTable.setRowSorter(tableSorter);

        // Add double-click listener for order details
        ordersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showOrderDetails();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(ordersTable);
        scrollPane.setPreferredSize(new Dimension(0, 300));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Summary"));

        JLabel infoLabel = new JLabel("Everything is mine");
        infoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        infoLabel.setForeground(Color.GRAY);
        panel.add(infoLabel);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> onBackButtonClicked());
        panel.add(backBtn);

        return panel;
    }

    private void onBackButtonClicked() {
        // Handle back button click
        ui.fadeSwitchTo("Menu");

    }

    // Event handlers
    private void handleQuickFilter() {
        String selected = (String) quickFilterCombo.getSelectedItem();
        LocalDate today = LocalDate.now();
        LocalDate startDate, endDate;

        switch (selected) {
            case "Today":
                startDate = endDate = today;
                break;
            case "Yesterday":
                startDate = endDate = today.minusDays(1);
                break;
            case "This Week":
                startDate = today.minusDays(today.getDayOfWeek().getValue() - 1);
                endDate = today;
                break;
            case "This Month":
                startDate = today.withDayOfMonth(1);
                endDate = today;
                break;
            case "Last 7 Days":
                startDate = today.minusDays(7);
                endDate = today;
                break;
            case "Last 30 Days":
                startDate = today.minusDays(30);
                endDate = today;
                break;
            default: // Custom Range
                return; // Don't auto-update for custom range
        }

        startDateSpinner.setValue(java.util.Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        endDateSpinner.setValue(java.util.Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        refreshData();
    }

    private void refreshData() {
        // Fix: Convert java.util.Date to LocalDate properly
        java.util.Date startUtilDate = (java.util.Date) startDateSpinner.getValue();
        java.util.Date endUtilDate = (java.util.Date) endDateSpinner.getValue();

        LocalDate startDate = startUtilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = endUtilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        loadOrdersData(startDate, endDate);
        updateSummaryCards(startDate, endDate);
    }

    private void loadOrdersData(LocalDate startDate, LocalDate endDate) {
        tableModel.setRowCount(0);

        List<OrderData> orders = reportManager.loadOrdersByDateRange(startDate, endDate);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (OrderData order : orders) {
            Object[] row = {
                String.format("#%04d", order.getReceiptNumber()),
                order.getOrderDateTime().format(dateFormatter),
                order.getOrderDateTime().format(timeFormatter),
                order.getTableName(),
                order.getItems().size(),
                order.getSubtotal(),
                order.getDiscount(),
                order.getGrandTotal(),
                order.getStatus()
            };
            tableModel.addRow(row);
        }

        // Update table display
        ordersTable.revalidate();
        ordersTable.repaint();
    }

    private void updateSummaryCards(LocalDate startDate, LocalDate endDate) {
        List<OrderData> orders = reportManager.loadOrdersByDateRange(startDate, endDate);

        int totalOrders = orders.size();
        double totalSales = orders.stream().mapToDouble(OrderData::getGrandTotal).sum();
        double avgOrder = totalOrders > 0 ? totalSales / totalOrders : 0;
        int totalItems = orders.stream()
                .mapToInt(order -> order.getItems().stream().mapToInt(OrderData.OrderItem::getQuantity).sum())
                .sum();

        totalOrdersLabel.setText(String.valueOf(totalOrders));
        totalSalesLabel.setText(String.format("$%.2f", totalSales));
        avgOrderLabel.setText(String.format("$%.2f", avgOrder));
        totalItemsLabel.setText(String.valueOf(totalItems));
    }

    private void performSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            tableSorter.setRowFilter(null);
            return;
        }

        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchText);
        tableSorter.setRowFilter(rowFilter);
    }

    private void clearSearch() {
        searchField.setText("");
        tableSorter.setRowFilter(null);
    }

    private void exportToPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF Report");
        fileChooser.setSelectedFile(new java.io.File("Sales_Report_" + LocalDate.now() + ".pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                // Here you would implement PDF generation
                // For now, show a message
                JOptionPane.showMessageDialog(this,
                        "PDF export functionality will be implemented.\nFile would be saved to: "
                        + fileChooser.getSelectedFile().getAbsolutePath(),
                        "Export PDF",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error exporting to PDF: " + e.getMessage(),
                        "Export Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel Report");
        fileChooser.setSelectedFile(new java.io.File("Sales_Report_" + LocalDate.now() + ".csv"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                exportTableToCSV(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this,
                        "Report exported successfully!",
                        "Export Complete",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Error exporting to Excel: " + e.getMessage(),
                        "Export Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportTableToCSV(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        // Write headers
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            writer.append(tableModel.getColumnName(i));
            if (i < tableModel.getColumnCount() - 1) {
                writer.append(",");
            }
        }
        writer.append("\n");

        // Write data
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                Object value = tableModel.getValueAt(i, j);
                writer.append(value != null ? value.toString() : "");
                if (j < tableModel.getColumnCount() - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");
        }

        writer.close();
    }

    private void printReport() {
        try {
            boolean complete = ordersTable.print(
                    JTable.PrintMode.FIT_WIDTH,
                    new MessageFormat("Sales Report - " + LocalDate.now()),
                    new MessageFormat("Page {0}")
            );

            if (complete) {
                JOptionPane.showMessageDialog(this,
                        "Report printed successfully!",
                        "Print Complete",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(this,
                    "Error printing report: " + e.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showOrderDetails() {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Convert view row to model row (in case of sorting/filtering)
            int modelRow = ordersTable.convertRowIndexToModel(selectedRow);
            String receiptStr = (String) tableModel.getValueAt(modelRow, 0);
            int receiptNumber = Integer.parseInt(receiptStr.substring(1)); // Remove #

            // Find and show order details
            List<OrderData> allOrders = reportManager.loadAllOrdersFromFile();
            OrderData selectedOrder = allOrders.stream()
                    .filter(order -> order.getReceiptNumber() == receiptNumber)
                    .findFirst()
                    .orElse(null);

            if (selectedOrder != null) {
                Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                // OrderDetailDialog detailDialog = new OrderDetailDialog(parentFrame, selectedOrder);
                // detailDialog.setVisible(true);
            }
        }
    }

    private void loadInitialData() {
        // Set initial filter to "Today"
        quickFilterCombo.setSelectedItem("Today");
        handleQuickFilter();
    }
}
