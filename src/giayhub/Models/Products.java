/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class Products {
    private int productID;
    private String productName;
    private String description;
    private double Price;
    private int stockQuantity;
    private String size;
    private String color;

    public Products() {
    }

    public Products(int productID, String productName, String description, double Price, int stockQuantity, String size, String color) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.Price = Price;
        this.stockQuantity = stockQuantity;
        this.size = size;
        this.color = color;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Products{" + "productID=" + productID + ", productName=" + productName + ", description=" + description + ", Price=" + Price + ", stockQuantity=" + stockQuantity + ", size=" + size + ", color=" + color + '}';
    }

}
