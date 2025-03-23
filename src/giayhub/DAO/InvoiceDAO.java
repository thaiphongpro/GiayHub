/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.Invoices;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author phong
 */
public class InvoiceDAO {
    
    public List<Invoices> getAll(){
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
    
    public List<Invoices> searchMaHD(String maHD){
        List<Invoices> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Invoices WHERE InvoiceID LIKE '%"+maHD+"%'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {                
                Invoices invoices = new Invoices();
                invoices.setInvoiceID(rs.getInt(1));
                invoices.setOrderID(rs.getInt(2));
                invoices.setIssueDate(rs.getString(3));
                invoices.setTotalMoney(rs.getDouble(4));
                invoices.setPaymentMethod(rs.getString(5));
                invoices.setPaymentStatus(rs.getString(6));
                
                listSearch.add(invoices);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
    
    public List<Invoices> searchMaDH(String maDH){
        List<Invoices> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Invoices WHERE OrderID LIKE '%"+maDH+"%'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {                
                Invoices invoices = new Invoices();
                invoices.setInvoiceID(rs.getInt(1));
                invoices.setOrderID(rs.getInt(2));
                invoices.setIssueDate(rs.getString(3));
                invoices.setTotalMoney(rs.getDouble(4));
                invoices.setPaymentMethod(rs.getString(5));
                invoices.setPaymentStatus(rs.getString(6));
                
                listSearch.add(invoices);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
}
