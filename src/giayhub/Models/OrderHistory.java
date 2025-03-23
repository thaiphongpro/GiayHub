/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class OrderHistory {
    private int orderID;
    private String fullName;
    private String orderDate;
    private String status;
    private String productName;
    private int quantity;
    private double totalPrice;

    public OrderHistory() {
    }

    public OrderHistory(int orderID, String fullName, String orderDate, String status, String productName, int quantity, double totalPrice) {
        this.orderID = orderID;
        this.fullName = fullName;
        this.orderDate = orderDate;
        this.status = status;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderHistory{" + "orderID=" + orderID + ", fullName=" + fullName + ", orderDate=" + orderDate + ", status=" + status + ", productName=" + productName + ", quantity=" + quantity + ", totalPrice=" + totalPrice + '}';
    }
    
    
}
