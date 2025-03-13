/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class Invoices {

    private int invoiceID;
    private int orderID;
    private String issueDate;
    private double totalMoney;
    private String paymentMethod;
    private String paymentStatus;

    public Invoices() {
    }

    public Invoices(int invoiceID, int orderID, String issueDate, double totalMoney, String paymentMethod, String paymentStatus) {
        this.invoiceID = invoiceID;
        this.orderID = orderID;
        this.issueDate = issueDate;
        this.totalMoney = totalMoney;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
        return "Invoices{" + "invoiceID=" + invoiceID + ", orderID=" + orderID + ", issueDate=" + issueDate + ", totalMoney=" + totalMoney + ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + '}';
    }

    
}
