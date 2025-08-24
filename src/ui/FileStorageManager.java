package ui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Handles file storage operations for orders Supports multiple storage formats:
 * binary serialization, CSV, JSON-like format
 */
public class FileStorageManager {

    private static final Logger LOGGER = Logger.getLogger(FileStorageManager.class.getName());

    // File paths and directories
    private static final String DATA_DIR = "data";
    private static final String ORDERS_DIR = DATA_DIR + File.separator + "orders";
    private static final String BACKUP_DIR = DATA_DIR + File.separator + "backup";
    private static final String DAILY_ORDERS_FILE = ORDERS_DIR + File.separator + "daily_orders.dat";
    private static final String ALL_ORDERS_FILE = ORDERS_DIR + File.separator + "all_orders.dat";

    private static FileStorageManager instance;

    // Private constructor for singleton pattern
    private FileStorageManager() {
        initializeDirectories();
    }

    /**
     * Get singleton instance
     */
    public static synchronized FileStorageManager getInstance() {
        if (instance == null) {
            instance = new FileStorageManager();
        }
        return instance;
    }

    /**
     * Initialize required directories
     */
    private void initializeDirectories() {
        try {
            createDirectoryIfNotExists(DATA_DIR);
            createDirectoryIfNotExists(ORDERS_DIR);
            createDirectoryIfNotExists(BACKUP_DIR);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize directories", e);
        }
    }

    /**
     * Create directory if it doesn't exist
     */
    private void createDirectoryIfNotExists(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                LOGGER.info("Created directory: " + dirPath);
            } else {
                LOGGER.warning("Failed to create directory: " + dirPath);
            }
        }
    }

    /**
     * Save a completed order to file storage
     */
    public boolean saveOrder(OrderData order) {
        try {
            // Save to daily file
            saveToDailyFile(order);

            // Save to all orders file
            saveToAllOrdersFile(order);

            // Create backup
            createDailyBackup(order);

            // Save as CSV for easy reading
            saveOrderToCSV(order);

            LOGGER.info("Order #" + order.getReceiptNumber() + " saved successfully");
            return true;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to save order #" + order.getReceiptNumber(), e);
            return false;
        }
    }

    /**
     * Save order to daily file (binary format)
     */
    private void saveToDailyFile(OrderData order) throws IOException {
        String dailyFileName = ORDERS_DIR + File.separator
                + "orders_" + order.getFormattedDate().replace("-", "_") + ".dat";

        List<OrderData> dailyOrders = loadDailyOrders(order.getOrderDateTime().toLocalDate());
        dailyOrders.add(order);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dailyFileName))) {
            oos.writeObject(dailyOrders);
        }
    }

    /**
     * Save order to all orders file (binary format)
     */
    private void saveToAllOrdersFile(OrderData order) throws IOException {
        List<OrderData> allOrders = loadAllOrdersFromFile();
        allOrders.add(order);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ALL_ORDERS_FILE))) {
            oos.writeObject(allOrders);
        }
    }

    /**
     * Save order to CSV format for easy reading
     */
    private void saveOrderToCSV(OrderData order) {
        String csvFileName = ORDERS_DIR + File.separator + "orders.csv";
        boolean fileExists = new File(csvFileName).exists();

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFileName, true))) {
            // Write header if file is new
            if (!fileExists) {
                pw.println("Receipt_Number,Date,Time,Table,Status,Subtotal,Discount,Grand_Total,Items_Count,Items_Detail");
            }

            // Write order data
            StringBuilder itemsDetail = new StringBuilder();
            for (OrderData.OrderItem item : order.getItems()) {
                if (itemsDetail.length() > 0) {
                    itemsDetail.append("; ");
                }
                itemsDetail.append(String.format("%s(x%d@%.2f)",
                        item.getName(), item.getQuantity(), item.getUnitPrice()));
            }

            pw.printf("%d,%s,%s,%s,%s,%.2f,%.2f,%.2f,%d,\"%s\"%n",
                    order.getReceiptNumber(),
                    order.getFormattedDate(),
                    order.getFormattedTime(),
                    order.getTableName(),
                    order.getStatus(),
                    order.getSubtotal(),
                    order.getDiscount(),
                    order.getGrandTotal(),
                    order.getItems().size(),
                    itemsDetail.toString()
            );

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to save order to CSV", e);
        }
    }

    /**
     * Create daily backup
     */
    private void createDailyBackup(OrderData order) {
        try {
            String backupFileName = BACKUP_DIR + File.separator
                    + "backup_" + order.getFormattedDate().replace("-", "_") + ".dat";

            List<OrderData> dailyOrders = loadDailyOrders(order.getOrderDateTime().toLocalDate());

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backupFileName))) {
                oos.writeObject(dailyOrders);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to create backup", e);
        }
    }

    /**
     * Load all orders from file storage
     */
    @SuppressWarnings("unchecked")
    public List<OrderData> loadAllOrdersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ALL_ORDERS_FILE))) {
            return (List<OrderData>) ois.readObject();
        } catch (FileNotFoundException e) {
            LOGGER.info("All orders file not found, returning empty list");
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load all orders from file", e);
            return new ArrayList<>();
        }
    }

    /**
     * Load orders for a specific date
     */
    @SuppressWarnings("unchecked")
    public List<OrderData> loadDailyOrders(LocalDate date) {
        String dailyFileName = ORDERS_DIR + File.separator
                + "orders_" + date.format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".dat";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dailyFileName))) {
            return (List<OrderData>) ois.readObject();
        } catch (FileNotFoundException e) {
            LOGGER.info("Daily orders file not found for " + date + ", returning empty list");
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load daily orders for " + date, e);
            return new ArrayList<>();
        }
    }

    /**
     * Load orders within a date range
     */
    public List<OrderData> loadOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        List<OrderData> result = new ArrayList<>();

        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            result.addAll(loadDailyOrders(current));
            current = current.plusDays(1);
        }

        return result;
    }

    /**
     * Get the next receipt number
     */
    public synchronized int getNextReceiptNumber() {
        List<OrderData> allOrders = loadAllOrdersFromFile();
        if (allOrders.isEmpty()) {
            return 1001; // Starting receipt number
        }

        int maxReceiptNumber = allOrders.stream()
                .mapToInt(OrderData::getReceiptNumber)
                .max()
                .orElse(1000);

        return maxReceiptNumber + 1;
    }

    /**
     * Update an existing order
     */
    public boolean updateOrder(OrderData updatedOrder) {
        try {
            // Update in all orders file
            List<OrderData> allOrders = loadAllOrdersFromFile();
            boolean updated = false;

            for (int i = 0; i < allOrders.size(); i++) {
                if (allOrders.get(i).getReceiptNumber() == updatedOrder.getReceiptNumber()) {
                    allOrders.set(i, updatedOrder);
                    updated = true;
                    break;
                }
            }

            if (updated) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ALL_ORDERS_FILE))) {
                    oos.writeObject(allOrders);
                }

                // Update daily file as well
                updateDailyFile(updatedOrder);

                LOGGER.info("Order #" + updatedOrder.getReceiptNumber() + " updated successfully");
                return true;
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update order #" + updatedOrder.getReceiptNumber(), e);
        }

        return false;
    }

    /**
     * Update order in daily file
     */
    private void updateDailyFile(OrderData updatedOrder) throws IOException {
        LocalDate orderDate = updatedOrder.getOrderDateTime().toLocalDate();
        List<OrderData> dailyOrders = loadDailyOrders(orderDate);

        for (int i = 0; i < dailyOrders.size(); i++) {
            if (dailyOrders.get(i).getReceiptNumber() == updatedOrder.getReceiptNumber()) {
                dailyOrders.set(i, updatedOrder);
                break;
            }
        }

        String dailyFileName = ORDERS_DIR + File.separator
                + "orders_" + orderDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dailyFileName))) {
            oos.writeObject(dailyOrders);
        }
    }

    /**
     * Delete an order
     */
    public boolean deleteOrder(int receiptNumber) {
        try {
            List<OrderData> allOrders = loadAllOrdersFromFile();
            OrderData orderToDelete = null;

            // Find and remove from all orders
            allOrders.removeIf(order -> {
                if (order.getReceiptNumber() == receiptNumber) {
                    return true;
                }
                return false;
            });

            // Save updated all orders file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ALL_ORDERS_FILE))) {
                oos.writeObject(allOrders);
            }

            LOGGER.info("Order #" + receiptNumber + " deleted successfully");
            return true;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete order #" + receiptNumber, e);
            return false;
        }
    }

    /**
     * Export orders to external CSV file
     */
    public boolean exportOrdersToCSV(String fileName, List<OrderData> orders) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            // Write header
            pw.println("Receipt_Number,Date,Time,Table,Status,Subtotal,Discount,Grand_Total,Items_Count");

            // Write orders
            for (OrderData order : orders) {
                pw.printf("%d,%s,%s,%s,%s,%.2f,%.2f,%.2f,%d%n",
                        order.getReceiptNumber(),
                        order.getFormattedDate(),
                        order.getFormattedTime(),
                        order.getTableName(),
                        order.getStatus(),
                        order.getSubtotal(),
                        order.getDiscount(),
                        order.getGrandTotal(),
                        order.getItems().size()
                );
            }

            return true;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to export orders to CSV", e);
            return false;
        }
    }

    /**
     * Get storage statistics
     */
    public StorageStats getStorageStats() {
        List<OrderData> allOrders = loadAllOrdersFromFile();

        StorageStats stats = new StorageStats();
        stats.totalOrders = allOrders.size();
        stats.totalSales = allOrders.stream().mapToDouble(OrderData::getGrandTotal).sum();
        stats.oldestOrderDate = allOrders.stream()
                .map(order -> order.getOrderDateTime().toLocalDate())
                .min(LocalDate::compareTo)
                .orElse(LocalDate.now());
        stats.newestOrderDate = allOrders.stream()
                .map(order -> order.getOrderDateTime().toLocalDate())
                .max(LocalDate::compareTo)
                .orElse(LocalDate.now());

        return stats;
    }

    /**
     * Storage statistics inner class
     */
    public static class StorageStats {

        public int totalOrders;
        public double totalSales;
        public LocalDate oldestOrderDate;
        public LocalDate newestOrderDate;

        @Override
        public String toString() {
            return String.format("Total Orders: %d, Total Sales: $%.2f, Date Range: %s to %s",
                    totalOrders, totalSales, oldestOrderDate, newestOrderDate);
        }
    }
}
