/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class ImportProductInformation {
    private int importID;
    private String productName;
    private int quantity;
    private double importPrice;
    private String importDate;
    private String supplierName;
    private String supplierPhoneNumber;
    private String supplierAddress;

    public ImportProductInformation() {
    }

    public ImportProductInformation(int importID, String productName, int quantity, double importPrice, String importDate, String supplierName, String supplierPhoneNumber, String supplierAddress) {
        this.importID = importID;
        this.productName = productName;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.importDate = importDate;
        this.supplierName = supplierName;
        this.supplierPhoneNumber = supplierPhoneNumber;
        this.supplierAddress = supplierAddress;
    }

    public int getImportID() {
        return importID;
    }

    public void setImportID(int importID) {
        this.importID = importID;
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

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    @Override
    public String toString() {
        return "ImportProductInformation{" + "importID=" + importID + ", productName=" + productName + ", quantity=" + quantity + ", importPrice=" + importPrice + ", importDate=" + importDate + ", supplierName=" + supplierName + ", supplierPhoneNumber=" + supplierPhoneNumber + ", supplierAddress=" + supplierAddress + '}';
    }

}
