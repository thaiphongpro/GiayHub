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
            if (cartItems.getProductID() == productID) {
                cartItems.setQuantity(cartItems.getQuantity() + quantity);
                return;
            }
        }
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
        int daNhap = 0;
        if (stockQuantity <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Đã hết sản phẩm, vui lòng nhập thêm hàng");
            return false;
        }
        for (int i = 0; i < lists.size(); i++) {
            CartItems items = lists.get(i); // Lay phan tu thu i trong danh sach cartItems
            if (items.getProductID() == productID) {
                daNhap += items.getQuantity(); // Nếu sản phẩm trùng, thì cộng số lượng của nó vào biến daNhap
            }
        }
        if (daNhap + requestedQuantity > stockQuantity) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER,
                    "Vượt quá số lượng tồn kho!");
            return false;
        }
        return requestedQuantity <= stockQuantity;
    }

    public int getCartQuantity(int productID) {
        for (CartItems cartItems : lists) {
            if (cartItems.getQuantity() == productID) {
                return cartItems.getQuantity();
            }
        }
        return 0;
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
