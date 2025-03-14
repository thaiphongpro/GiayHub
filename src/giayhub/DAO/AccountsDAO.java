/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.Customers;
import giayhub.Models.Users;
import giayhub.Views.LoginForm;
import giayhub.Views.DashboardForm;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author FPT Scam
 */
public class AccountsDAO {

    public List<Users> getAllUsers() {
        try {
            String sql = """
                         SELECT * FROM Users
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<Users> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Users(
                        rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Check login
    public boolean checkLogin(String userName, String password) {
        int row = 0;
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT COUNT(*) as rowNumber, UserName, Password FROM Users WHERE UserName = '" + userName + "' AND [Password] = '" + password + "' GROUP BY UserName, Password";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                row = 1;
                String usn = rs.getString(2);
                String pass = rs.getString(3);

                LoginForm.USERS = new Users(usn, pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (row > 0);
    }
    
    // Check username tồn tài trên hệ thống
    public boolean checkUsers(String username){
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
            PreparedStatement ps  = conn.prepareStatement(sql);
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                return rs.getInt(1) > 0; // Nếu count > 0 thì username đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
        
    // Đăng ký tài khoản Users
//    public int dangKyUsers(Users users){
//        int userID = -1;
//        try {
//            Connection conn = DBConnection.getConnection();
//            String sql = "INSERT INTO Users (Username, Password, Email, PhoneNumber, RoleID) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            
//            ps.setString(1, users.getUserName());
//            ps.setString(2, users.getPassword());
//            ps.setString(3, users.getEmail());
//            ps.setString(4, users.getPhoneNumber());
//            ps.setString(5, users.getRoleID()+"");
//            
//            int row = ps.executeUpdate();
//            if (row > 0) {
//                ResultSet rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    userID = rs.getInt(1);
//                }
//            }
//            ps.close();
//            conn.close();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return userID;
//    }
    
    // Đăng ký tài khoản Customers
//    public void dangKyCustomers(Customers customers){
//        try {
//            Connection conn = DBConnection.getConnection();
//            String sql = """
//                         INSERT INTO Customers (FullName, Email, PhoneNumber, Address,UserID) 
//                         VALUES 
//                         (?, ?, ?, ?, ?, ?)
//                         """;
//            PreparedStatement ps = conn.prepareStatement(sql);
//            
//            ps.setString(1, customers.getFullName());
//            ps.setString(2, customers.getEmail());
//            ps.setString(3, customers.getPhoneNumber());
//            ps.setString(4, customers.getAddress());
//            ps.setInt(5, userID);
//            
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public void dangKyUsers(Users users){
        try {
            String sql = """
                         INSERT INTO Users (Username, Password, Email, PhoneNumber, RoleID) 
                         VALUES 
                         (?, ?, ?, ?, ?)
                         """;
            DBConnection.update(sql, users.getUserName(), users.getPassword(), users.getEmail(), users.getPhoneNumber(), users.getRoleID());
        } catch (Exception e) {
        }
    }
}
