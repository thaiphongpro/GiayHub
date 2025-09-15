/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.CartItems;
import java.util.ArrayList;
import java.util.List;
import giayhub.DAO.ProductsDAO;
import javax.swing.JOptionPane;
import raven.toast.Notifications;

/**
 *
 * @author phong
 */
public class CartDAO {
    
    private List<CartItems> lists = new ArrayList<>();
    private ProductsDAO serviceSP = new ProductsDAO();
    
    public void addToCart(int productID, String productName, double price, int quantity) {
        for (CartItems cartItems : lists) {
            if (cartItems.getProductID() == productID) { // Nếu sản phẩm nhập vào trùng productID với sp trong List
                cartItems.setQuantity(cartItems.getQuantity() + quantity); // Cộng dồn số lượng đã có với số lượng đã nhập vào
                return;
            }
        }
        // Nếu duyệt hết trong list mà chưa thấy sản phẩm nào trùng productID -> tạo 1 sản phẩm mới và thêm vào list
        lists.add(new CartItems(productID, productName, quantity, price));
    }
    
    public void removeFromCart(int productID) {
        for (CartItems cartItems : lists) {
            if (cartItems.getProductID() == productID) {
                lists.remove(productID);
            }
        }
    }
    
    public boolean checkStock(int productID, int requestedQuantity) {
        int stockQuantity = serviceSP.laySLTonKho(productID);
        if (stockQuantity <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Đã hết sản phẩm, vui lòng nhập thêm hàng");
            return false;
        }
        for (CartItems items : lists) {
            if (items.getProductID() == productID) {
                requestedQuantity += items.getQuantity();
            }
        }
        if (requestedQuantity > stockQuantity) {
            return false;
        }
        return requestedQuantity <= stockQuantity;
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
