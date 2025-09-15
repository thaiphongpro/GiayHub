/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class ImportProducts {
    private int importID;
    private int productID;
    private int supplierID;
    private String importDate;
    private int quantity;
    private double importPrice;

    public ImportProducts() {
    }

    public ImportProducts(int importID, int productID, int supplierID, String importDate, int quantity, double importPrice) {
        this.importID = importID;
        this.productID = productID;
        this.supplierID = supplierID;
        this.importDate = importDate;
        this.quantity = quantity;
        this.importPrice = importPrice;
    }

    public int getImportID() {
        return importID;
    }

    public void setImportID(int importID) {
        this.importID = importID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
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

    @Override
    public String toString() {
        return "ImportProducts{" + "importID=" + importID + ", productID=" + productID + ", supplierID=" + supplierID + ", importDate=" + importDate + ", quantity=" + quantity + ", importPrice=" + importPrice + '}';
    }
    
    
}
