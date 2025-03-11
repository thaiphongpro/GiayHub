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
    private int invoiceId;
    private int orderId;
    private String Date;
    private float totalAmount;


    public Invoices() {
    }

    public Invoices(int invoiceId, int orderId, String Date, float totalAmount) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.Date = Date;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Invoices{" + "invoiceId=" + invoiceId + ", orderId=" + orderId + ", Date=" + Date + ", totalAmount=" + totalAmount + '}';
    }

}
