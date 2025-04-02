/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class InvoiceManager {
    private int invoiceID;
    private String issueDate;
    private int customerID;
    private double totalMoney;
    private String paymentMethod;
    private String paymentStatus;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;

    public InvoiceManager() {
    }

    public InvoiceManager(int invoiceID, String issueDate, int customerID, double totalMoney, String paymentMethod, String paymentStatus, String customerName, String email, String phoneNumber, String address) {
        this.invoiceID = invoiceID;
        this.issueDate = issueDate;
        this.customerID = customerID;
        this.totalMoney = totalMoney;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public InvoiceManager(int invoiceID, String issueDate, int customerID, double totalMoney, String paymentMethod, String paymentStatus) {
        this.invoiceID = invoiceID;
        this.issueDate = issueDate;
        this.customerID = customerID;
        this.totalMoney = totalMoney;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
    
    public InvoiceManager(int customerID, String customerName, String email, String phoneNumber, String address) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "InvoiceManager{" + "invoiceID=" + invoiceID + ", issueDate=" + issueDate + ", customerID=" + customerID + ", totalMoney=" + totalMoney + ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + ", customerName=" + customerName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + '}';
    }

}
