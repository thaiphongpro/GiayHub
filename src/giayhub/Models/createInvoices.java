/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

import java.time.LocalDate;

/**
 *
 * @author phong
 */
public class createInvoices {
    // Orders
    private int orderID;
    private int customerID;
    private String orderDate;
    private String status;
    
    // OrderDetails
    private int orderDetailID;
    private int productID;
    private int quantity;
    private double totalPrice;
    
    // Invoices
    private int invoiceID;
    private String issueDate;
    private double totalMoney;
    private String paymentMethod;
    private String paymentStatus;

    public createInvoices() {
    }

    // Orders
    public createInvoices(int orderID, int customerID, String orderDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.status = status;
    }
    
    // OrderDetails
    public createInvoices(int orderDetailID, int orderID, int productID, int quantity, double totalPrice) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
    
    // Invoices
    public createInvoices(int invoiceID, int orderID, String issueDate, double totalMoney, String paymentMethod, String paymentStatus) {      
        this.invoiceID = invoiceID;
        this.orderID = orderID;
        this.issueDate = issueDate;
        this.totalMoney = totalMoney;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
    
    public createInvoices(int orderID, int customerID, String orderDate, String status, int orderDetailID, int productID, int quantity, double totalPrice, int invoiceID, String issueDate, double totalMoney, String paymentMethod, String paymentStatus) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.status = status;
        this.orderDetailID = orderDetailID;
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.invoiceID = invoiceID;
        this.issueDate = issueDate;
        this.totalMoney = totalMoney;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public createInvoices(int invoiceID, int orderID, LocalDate ngayHienTai, double d, String string, String chờ_thanh_toán) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "createInvoices{" + "orderID=" + orderID + ", customerID=" + customerID + ", orderDate=" + orderDate + ", status=" + status + ", orderDetailID=" + orderDetailID + ", productID=" + productID + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", invoiceID=" + invoiceID + ", issueDate=" + issueDate + ", totalMoney=" + totalMoney + ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + '}';
    }
}
