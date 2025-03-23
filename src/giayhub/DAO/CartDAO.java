/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.CartItems;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author phong
 */
public class CartDAO {

    private List<CartItems> lists = new ArrayList<>();

    public void addToCart(int productID, String productName, double price) {
        for (CartItems cartItems : lists) {
            if (cartItems.getProductID() == productID) {
                cartItems.tangSoLuong();
                return;
            }
        }
        lists.add(new CartItems(productID, productName, 1, price));
    }

    public void removeFromCart(int productID) {
        for (CartItems cartItems : lists) {
            if (cartItems.getProductID() == productID) {
                lists.remove(productID);
            }
        }
    }

    public boolean checkStock(int stockQuantity) {
        for (CartItems cartItems : lists) {
            if (cartItems.getQuantity() >= stockQuantity) {
                return false;
            }
        }
        return true;
    }

    public double getTotal() {
        double total = 0;
        for (CartItems cartItems : lists) {
            total += cartItems.getTotalPrice();
        }
        return total;
    }

    public List<CartItems> getLists() {
        return lists;
    }

    public void clearCart() {
        lists.clear();
    }
}
