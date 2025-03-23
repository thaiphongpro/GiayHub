package giayhub.Models;

public class CartItems {
    private int productID;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;

    public CartItems() {
    }

    public CartItems(int productID, String productName, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = calculateTotalPrice();
    }

    public void tangSoLuong() {
        this.quantity++;
        this.totalPrice = calculateTotalPrice();
    }

    public void giamSoLuong() {
        if (this.quantity > 1) {
            this.quantity--;
            this.totalPrice = calculateTotalPrice();
        }
    }

    // Phương thức tính tổng giá tiền
    private double calculateTotalPrice() {
        return this.quantity * this.price;
    }

    // Getter & Setter
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice(); // Cập nhật lại tổng tiền khi thay đổi số lượng
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.totalPrice = calculateTotalPrice(); // Cập nhật lại tổng tiền khi thay đổi giá
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "CartItems{" + "productID=" + productID + ", productName=" + productName +
                ", quantity=" + quantity + ", price=" + price + ", totalPrice=" + totalPrice + '}';
    }
}