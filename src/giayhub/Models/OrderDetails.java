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
    private int OrderDetailID;
    private int OrderID;
    private int ProductID;
    private int Quantity;
    private double Price;

    public OrderDetails() {
    }

    public OrderDetails(int OrderDetailID, int OrderID, int ProductID, int Quantity, double Price) {
        this.OrderDetailID = OrderDetailID;
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "OrderDetailID=" + OrderDetailID + ", OrderID=" + OrderID + ", ProductID=" + ProductID + ", Quantity=" + Quantity + ", Price=" + Price + '}';
    }

}
