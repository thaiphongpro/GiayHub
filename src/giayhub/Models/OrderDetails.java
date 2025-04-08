/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class OrderDetails {

    private int orderDetailsID;
    private int orderID;
    private int productID;
    private String productName;
    private String customerName;
    private int quantity;
    private double price;
    private double totalPrice;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailsID, int orderID, int productID, String productName, int quantity, double price, double totalPrice) {
        this.orderDetailsID = orderDetailsID;
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public OrderDetails(int orderID, int productID, String productName, int quantity, double price, double totalPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public OrderDetails(int orderDetailsID, int orderID, int productID, int quantity, double totalPrice) {
        this.orderDetailsID = orderDetailsID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderDetails(int orderID, String customerName, int productID, String productName, int quantity, double price, double totalPrice) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public int getOrderDetailsID() {
        return orderDetailsID;
    }

    public void setOrderDetailsID(int orderDetailsID) {
        this.orderDetailsID = orderDetailsID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderDetailsID=" + orderDetailsID + ", orderID=" + orderID + ", productID=" + productID + ", productName=" + productName + ", customerName=" + customerName + ", quantity=" + quantity + ", price=" + price + ", totalPrice=" + totalPrice + '}';
    }

    
}
