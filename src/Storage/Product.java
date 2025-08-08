package Storage;

public class Product {

    private int id;
    private String code;
    private String name;
    private String localName;
    private int quantity;
    private double price;
    private String category;
    private String status;
    private String imagePath;

    public Product(int id, String code, String name, String localName, int quantity, double price, String category, String status, String imagePath) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.localName = localName;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.status = status;
        this.imagePath = imagePath;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return name + " - " + quantity + " units @ $" + price + " (" + category + ")";
    }
}
