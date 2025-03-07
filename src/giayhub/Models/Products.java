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
    private double price;
    private int stock;
    private String description;
    private String createdDate;

    public Products() {
    }

    public Products(int productID, String productName, double price, int stock, String description, String createdDate) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.createdDate = createdDate;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Products{" + "productID=" + productID + ", productName=" + productName + ", price=" + price + ", stock=" + stock + ", description=" + description + ", createdDate=" + createdDate + '}';
    }

}
