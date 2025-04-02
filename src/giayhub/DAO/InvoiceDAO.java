/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.Customers;
import giayhub.Models.InvoiceManager;
import giayhub.Models.Invoices;
import giayhub.Models.OrderDetails;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author phong
 */
public class InvoiceDAO {

    public List<InvoiceManager> getAllHoaDon() {
        try {
            String sql = """
                          SELECT i.InvoiceID, i.IssueDate, o.CustomerID, i.TotalMoney, i.PaymentMethod, i.PaymentStatus 
                          FROM Orders o
                          INNER JOIN Invoices i
                              ON o.OrderID = i.OrderID
                          """;
            ResultSet rs = DBConnection.query(sql);

            List<InvoiceManager> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new InvoiceManager(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InvoiceManager> getAllKhachHang() {
        try {
            String sql = """
                          SELECT CustomerID, FullName, Email, PhoneNumber, Address
                          FROM Customers
                          """;
            ResultSet rs = DBConnection.query(sql);

            List<InvoiceManager> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new InvoiceManager(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<OrderDetails> getAllHoaDonChiTiet(){
        try {
            String sql = """
                         SELECT * FROM OrderDetails
                         """;
            ResultSet rs = DBConnection.query(sql);
            
            List<OrderDetails> lists = new ArrayList<>();
            
            while (rs.next()) {                
                lists.add(new OrderDetails(
                        rs.getInt(1), 
                        rs.getInt(2), 
                        rs.getInt(3), 
                        rs.getInt(4), 
                        rs.getInt(5)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InvoiceManager> getAllHoaDonForIDKH(int idKH) {
        List<InvoiceManager> lists = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = """
                         SELECT i.InvoiceID, i.IssueDate, c.customerID, i.TotalMoney, i.PaymentMethod, i.paymentStatus
                         FROM Customers c
                         INNER JOIN Orders o
                            ON c.CustomerID = o.CustomerID
                         INNER JOIN Invoices i
                            ON o.OrderID = i.OrderID
                         WHERE c.CustomerID = ?
                         """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKH);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lists.add(new InvoiceManager(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<InvoiceManager> tatCa() {
        try {
            String sql = """
                         SELECT i.InvoiceID, i.IssueDate, c.CustomerID, i.TotalMoney, i.PaymentMethod, i.PaymentStatus
                         FROM Customers c
                         INNER JOIN Orders o
                             ON c.CustomerID = o.CustomerID
                         INNER JOIN Invoices i
                             ON o.OrderID = i.OrderID
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<InvoiceManager> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new InvoiceManager(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InvoiceManager> choThanhToan() {
        try {
            String sql = """
                         SELECT i.InvoiceID, i.IssueDate, c.CustomerID, i.TotalMoney, i.PaymentMethod, i.PaymentStatus
                         FROM Customers c
                         INNER JOIN Orders o
                             ON c.CustomerID = o.CustomerID
                         INNER JOIN Invoices i
                             ON o.OrderID = i.OrderID
                         WHERE i.PaymentStatus = N'Chờ thanh toán'
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<InvoiceManager> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new InvoiceManager(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InvoiceManager> daThanhToan() {
        try {
            String sql = """
                         SELECT i.InvoiceID, i.IssueDate, c.CustomerID, i.TotalMoney, i.PaymentMethod, i.PaymentStatus
                         FROM Customers c
                         INNER JOIN Orders o
                             ON c.CustomerID = o.CustomerID
                         INNER JOIN Invoices i
                             ON o.OrderID = i.OrderID
                         WHERE i.PaymentStatus = N'Đã thanh toán'
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<InvoiceManager> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new InvoiceManager(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InvoiceManager> daHuy() {
        try {
            String sql = """
                         SELECT i.InvoiceID, i.IssueDate, c.CustomerID, i.TotalMoney, i.PaymentMethod, i.PaymentStatus
                         FROM Customers c
                         INNER JOIN Orders o
                             ON c.CustomerID = o.CustomerID
                         INNER JOIN Invoices i
                             ON o.OrderID = i.OrderID
                         WHERE i.PaymentStatus = N'Đã hủy'
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<InvoiceManager> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new InvoiceManager(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InvoiceManager> timKiemMaHD(String maHD) {
        List<InvoiceManager> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT i.InvoiceID, i.IssueDate, o.CustomerID, i.TotalMoney, i.PaymentMethod, i.PaymentStatus \n"
                    + "FROM Orders o\n"
                    + "INNER JOIN Invoices i\n"
                    + "ON o.OrderID = i.OrderID\n"
                    + "WHERE i.InvoiceID LIKE '%" + maHD + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                InvoiceManager invoice = new InvoiceManager();
                invoice.setInvoiceID(rs.getInt(1));
                invoice.setIssueDate(rs.getString(2));
                invoice.setCustomerID(rs.getInt(3));
                invoice.setTotalMoney(rs.getDouble(4));
                invoice.setPaymentMethod(rs.getString(5));
                invoice.setPaymentStatus(rs.getString(6));

                listSearch.add(invoice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    public List<InvoiceManager> timKiemPhuongThucThanhToan(String PTTT) {
        List<InvoiceManager> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT i.InvoiceID, i.IssueDate, o.CustomerID, i.TotalMoney, i.PaymentMethod, i.PaymentStatus \n"
                    + "FROM Orders o\n"
                    + "INNER JOIN Invoices i\n"
                    + "ON o.OrderID = i.OrderID\n"
                    + "WHERE i.InvoiceID LIKE '%" + PTTT + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                InvoiceManager invoice = new InvoiceManager();
                invoice.setInvoiceID(rs.getInt(1));
                invoice.setIssueDate(rs.getString(2));
                invoice.setCustomerID(rs.getInt(3));
                invoice.setTotalMoney(rs.getDouble(4));
                invoice.setPaymentMethod(rs.getString(5));
                invoice.setPaymentStatus(rs.getString(6));

                listSearch.add(invoice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    public List<Customers> timKiemMaKH(String maKH) {
        List<Customers> listSearch = new ArrayList<>();
        try {
            String sql = "SELECT CustomerID, FullName, Email, PhoneNumber, Address \n"
                    + "FROM Customers\n"
                    + "WHERE CustomerID LIKE N'%"+maKH+"%'";
            ResultSet rs = DBConnection.query(sql);
            
            while (rs.next()) {                
                Customers customers = new Customers();
                customers.setCustomerID(rs.getInt(1));
                customers.setFullName(rs.getString(2));
                customers.setEmail(rs.getString(3));
                customers.setPhoneNumber(rs.getString(4));
                customers.setAddress(rs.getString(5));
                
                listSearch.add(customers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
    
    public List<Customers> timKiemTenKH(String tenKH) {
        List<Customers> listSearch = new ArrayList<>();
        try {
            String sql = "SELECT CustomerID, FullName, Email, PhoneNumber, Address \n"
                    + "FROM Customers\n"
                    + "WHERE FullName LIKE N'%"+tenKH+"%'";
            ResultSet rs = DBConnection.query(sql);
            
            while (rs.next()) {                
                Customers customers = new Customers();
                customers.setCustomerID(rs.getInt(1));
                customers.setFullName(rs.getString(2));
                customers.setEmail(rs.getString(3));
                customers.setPhoneNumber(rs.getString(4));
                customers.setAddress(rs.getString(5));
                
                listSearch.add(customers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
    
    public List<OrderDetails> timKiemMaHDCT(String maHDCT){
        List<OrderDetails> listSearch = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetails WHERE OrderDetailsID LIKE '%"+maHDCT+"%'";
            ResultSet rs = DBConnection.query(sql);
            
            while (rs.next()) {                
                OrderDetails orderdetails = new OrderDetails();
                orderdetails.setOrderDetailsID(rs.getInt(1));
                orderdetails.setOrderID(rs.getInt(2));
                orderdetails.setProductID(rs.getInt(3));
                orderdetails.setQuantity(rs.getInt(4));
                orderdetails.setTotalPrice(rs.getDouble(5));
                
                listSearch.add(orderdetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
    
    public List<OrderDetails> timKiemMaDH(String maDH){
        List<OrderDetails> listSearch = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetails WHERE OrderID LIKE '%"+maDH+"%'";
            ResultSet rs = DBConnection.query(sql);
            
            while (rs.next()) {                
                OrderDetails orderdetails = new OrderDetails();
                orderdetails.setOrderDetailsID(rs.getInt(1));
                orderdetails.setOrderID(rs.getInt(2));
                orderdetails.setProductID(rs.getInt(3));
                orderdetails.setQuantity(rs.getInt(4));
                orderdetails.setTotalPrice(rs.getDouble(5));
                
                listSearch.add(orderdetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
}
