package ui;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Data model class representing an order with all its details Implements
 * Serializable for file storage capabilities
 */
public class OrderData implements Serializable {

    private static final long serialVersionUID = 1L;

    private int receiptNumber;
    private LocalDateTime orderDateTime;
    private String tableName;
    private String status;
    private double subtotal;
    private double discount;
    private double grandTotal;
    private List<OrderItem> items;

    // Constructors
    public OrderData() {
        this.items = new ArrayList<>();
        this.orderDateTime = LocalDateTime.now();
        this.status = "Pending";
    }

    public OrderData(int receiptNumber, String tableName) {
        this();
        this.receiptNumber = receiptNumber;
        this.tableName = tableName;
    }

    // Getters and Setters
    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items != null ? items : new ArrayList<>();
    }

    // Utility methods
    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public void calculateTotals() {
        this.subtotal = items.stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();
        this.grandTotal = this.subtotal - this.discount;
    }

    public String getFormattedDateTime() {
        return orderDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public String getFormattedDate() {
        return orderDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getFormattedTime() {
        return orderDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("Order #%04d - %s - %s - $%.2f",
                receiptNumber, tableName, getFormattedDateTime(), grandTotal);
    }

    /**
     * Inner class representing an individual item in an order
     */
    public static class OrderItem implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;
        private String type;
        private String size;
        private String ice;
        private String sugar;
        private String remark;
        private int quantity;
        private double unitPrice;
        private int orderNumber;

        // Constructors
        public OrderItem() {
        }

        public OrderItem(String name, int quantity, double unitPrice) {
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public OrderItem(String name, String type, String size, String ice,
                String sugar, String remark, int quantity, double unitPrice, int orderNumber) {
            this.name = name;
            this.type = type;
            this.size = size;
            this.ice = ice;
            this.sugar = sugar;
            this.remark = remark;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.orderNumber = orderNumber;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getIce() {
            return ice;
        }

        public void setIce(String ice) {
            this.ice = ice;
        }

        public String getSugar() {
            return sugar;
        }

        public void setSugar(String sugar) {
            this.sugar = sugar;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public double getTotalPrice() {
            return quantity * unitPrice;
        }

        public String getOptionsString() {
            if ("Drink".equalsIgnoreCase(type)) {
                return String.format("%s | %s | %s",
                        size != null ? size : "",
                        ice != null ? ice : "",
                        sugar != null ? sugar : "");
            } else if ("Food".equalsIgnoreCase(type)) {
                return remark != null ? remark : "";
            }
            return "";
        }

        @Override
        public String toString() {
            return String.format("%s (x%d) - $%.2f", name, quantity, getTotalPrice());
        }
    }
}
