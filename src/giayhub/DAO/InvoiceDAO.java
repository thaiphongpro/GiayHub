/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.Invoices;
import giayhub.Models.createInvoices;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author phong
 */
public class InvoiceDAO {

    public List<Invoices> getAll() {
        try {
            String sql = """
                         SELECT * FROM Invoices
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<Invoices> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Invoices(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Invoices> searchMaHD(String maHD) {
        List<Invoices> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Invoices WHERE InvoiceID LIKE '%" + maHD + "%'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Invoices invoices = new Invoices();
                invoices.setInvoiceID(rs.getInt(1));
                invoices.setOrderID(rs.getInt(2));
                invoices.setIssueDate(rs.getString(3));
                invoices.setCustomerName(rs.getString(4));
                invoices.setTotalMoney(rs.getDouble(5));
                invoices.setPaymentMethod(rs.getString(6));
                invoices.setPaymentStatus(rs.getString(7));

                listSearch.add(invoices);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    public List<Invoices> searchMaDH(String maDH) {
        List<Invoices> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Invoices WHERE OrderID LIKE '%" + maDH + "%'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Invoices invoices = new Invoices();
                invoices.setInvoiceID(rs.getInt(1));
                invoices.setOrderID(rs.getInt(2));
                invoices.setIssueDate(rs.getString(3));
                invoices.setCustomerName(rs.getString(4));
                invoices.setTotalMoney(rs.getDouble(5));
                invoices.setPaymentMethod(rs.getString(6));
                invoices.setPaymentStatus(rs.getString(7));

                listSearch.add(invoices);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    public int addOrders(createInvoices createInvoice) {
        try {
            String insertOrders = """
                                  INSERT INTO Orders (OrderID, CustomerID, OrderDate, Status) 
                                  VALUES
                                  (?, ?, ?, ?) 
                                  """;
            DBConnection.update(insertOrders,
                    createInvoice.getOrderID(),
                    createInvoice.getCustomerID(),
                    createInvoice.getOrderDate(),
                    createInvoice.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createInvoice.getOrderID();
    }

    public void addInvoices(createInvoices createInvoice) {
        int orderID = layIdOrders();
        int invoiceID = layIdInvoices();
        String insertInvoices = """
                                    INSERT INTO Invoices (InvoiceID, OrderID, IssueDate, CustomerName, TotalMoney, PaymentMethod, PaymentStatus)
                                    VALUES
                                    (?,?,?,?,?,?,?)
                                    """;
        DBConnection.update(insertInvoices,
                invoiceID,
                orderID,
                createInvoice.getIssueDate(),
                createInvoice.getCustomerName(),
                createInvoice.getTotalMoney(),
                createInvoice.getPaymentMethod(),
                createInvoice.getPaymentStatus());
    }

    public void addOrderDetails(createInvoices createInvoice) {
        int orderDetailID = layIdOrderDetails();
        int orderID = layIdOrders();
        String insertOrderDetail = """
                                       INSERT INTO OrderDetails (OrderDetailsID, OrderID, ProductID, Quantity, TotalPrice)
                                       VALUES
                                       (?, ?, ?, ?, ?) 
                                       """;
        DBConnection.update(insertOrderDetail,
                orderDetailID,
                orderID,
                createInvoice.getProductID(),
                createInvoice.getQuantity(),
                createInvoice.getTotalPrice());
    }

    public int layIdOrders() {
//        int nextID = 1;
        try {
            String sql = """
                         SELECT MAX(OrderID) FROM Orders
                         """;
            ResultSet rs = DBConnection.query(sql);

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int layIdInvoices() {
        try {
            String sql = """
                         SELECT MAX(InvoiceID) FROM Invoices
                         """;
            ResultSet rs = DBConnection.query(sql);

            while (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int layIdOrderDetails() {
        try {
            String sql = """
                         SELECT MAX(OrderDetailsID) FROM OrderDetails
                         """;
            ResultSet rs = DBConnection.query(sql);

            while (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void huyHoaDon(createInvoices createInvoice) {
        try {
            String sql = """
                         UPDATE Invoices
                         SET PaymentStatus = N'Đã hủy'
                         WHERE InvoiceID = ?
                         """;
            DBConnection.update(sql,
                    createInvoice.getInvoiceID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void thanhToanHoaDon(createInvoices createInvoices) {
        try {
            String sql = """
                         UPDATE Invoices
                         SET PaymentStatus = N'Đã thanh toán'
                         WHERE InvoiceID = ?
                         """;
            DBConnection.update(sql, createInvoices.getInvoiceID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void daThanhToanDonHang(createInvoices createInvoices){
        int orderID = layIdOrders();
        try {
            String sql = """
                         UPDATE Orders
                         SET [Status] = N'Đã thanh toán'
                         WHERE OrderID = ?
                         """;
            DBConnection.update(sql, orderID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void huyThanhToanDonHang(createInvoices createInvoices){
        int orderID = layIdOrders();
        try {
            String sql = """
                         UPDATE Orders
                         SET [Status] = N'Đã hủy'
                         WHERE OrderID = ?
                         """;
            DBConnection.update(sql, orderID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
