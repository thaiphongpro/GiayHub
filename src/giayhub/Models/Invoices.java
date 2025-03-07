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
    private int InvoiceID;
    private int OrderID;
    private String InvoiceDate;
    private double TotalAmount;
    private String Status;

    public Invoices() {
    }

    public Invoices(int InvoiceID, int OrderID, String InvoiceDate, double TotalAmount, String Status) {
        this.InvoiceID = InvoiceID;
        this.OrderID = OrderID;
        this.InvoiceDate = InvoiceDate;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public int getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(int InvoiceID) {
        this.InvoiceID = InvoiceID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Invoices{" + "InvoiceID=" + InvoiceID + ", OrderID=" + OrderID + ", InvoiceDate=" + InvoiceDate + ", TotalAmount=" + TotalAmount + ", Status=" + Status + '}';
    }

}
