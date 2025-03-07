/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class Payments {
    private int paymentID;
    private int invoiceID;
    private int paymentMethodID;
    private double amount;
    private String paymentDate;

    public Payments() {
    }

    public Payments(int paymentID, int invoiceID, int paymentMethodID, double amount, String paymentDate) {
        this.paymentID = paymentID;
        this.invoiceID = invoiceID;
        this.paymentMethodID = paymentMethodID;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payments{" + "paymentID=" + paymentID + ", invoiceID=" + invoiceID + ", paymentMethodID=" + paymentMethodID + ", amount=" + amount + ", paymentDate=" + paymentDate + '}';
    }

    
}
