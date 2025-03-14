/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.Customers;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phong
 */
public class CustomersDAO {
    
    public List<Customers> getAllCustomers(){
        try {
            String sql = """
                         SELECT * FROM Customers
                         """;
            ResultSet rs = DBConnection.query(sql);
            
            List<Customers> lists = new ArrayList<>();
            
            while (rs.next()) {                
                lists.add(new Customers(
                        rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Thêm khách hàng
    public void themKhachHang(Customers customers){
        try {
            String sql = """
                         INSERT INTO Customers (CustomerID, FullName, Email, PhoneNumber, Address, RoleID) 
                         VALUES
                         (?, ?, ?, ?, ?, ?) 
                         """;
            DBConnection.update(sql, 
                    customers.getCustomerID(), 
                    customers.getFullName(), 
                    customers.getEmail(),
                    customers.getPhoneNumber(),
                    customers.getAddress(),
                    customers.getRoleID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Sửa tt khách hàng
    public void suaKhachHang(Customers customers){
        try {
            String sql = """
                         UPDATE Customers
                         SET FullName = ?, Email = ?, PhoneNumber = ?, Address = ?
                         WHERE CustomerID = ?
                         """;
            DBConnection.update(sql, 
                    customers.getFullName(),
                    customers.getEmail(),
                    customers.getPhoneNumber(),
                    customers.getAddress(),
                    customers.getCustomerID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Xóa khách hàng
    public void xoaKhachHang(Customers customers){
        try {
            String sql = """
                         DELETE FROM Customers
                         WHERE CustomerID = ?
                         """;
            DBConnection.update(sql, customers.getCustomerID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Tìm theo số ĐT
    public static List<Customers> searchSDT(String SDT){
        List<Customers> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM customers WHERE PhoneNumber LIKE'%"+SDT+"%'";
            ResultSet rs = stm.executeQuery(sql);
            
            while (rs.next()) {                
                Customers cs = new Customers();
                cs.setCustomerID(rs.getInt(1));
                cs.setFullName(rs.getString(2));
                cs.setEmail(rs.getString(3));
                cs.setPhoneNumber(rs.getString(4));
                cs.setAddress(rs.getString(5));
                
                listSearch.add(cs);
            }
        } catch (Exception e) {
            System.out.println("Issue to search: "+e.getMessage());
        }
        return listSearch;
    }
    
    // Tìm theo tên
    public static List<Customers> searchTen(String HoTen){
        List<Customers> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM customers WHERE FullName LIKE'%"+HoTen+"%'";
            ResultSet rs = stm.executeQuery(sql);
            
            while (rs.next()) {                
                Customers cs = new Customers();
                cs.setCustomerID(rs.getInt(1));
                cs.setFullName(rs.getString(2));
                cs.setEmail(rs.getString(3));
                cs.setPhoneNumber(rs.getString(4));
                cs.setAddress(rs.getString(5));
                
                listSearch.add(cs);
            }
        } catch (Exception e) {
            System.out.println("Issue to search: "+e.getMessage());
        }
        return listSearch;
    }
}
