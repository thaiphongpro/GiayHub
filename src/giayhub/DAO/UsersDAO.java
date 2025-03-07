/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.Users;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author FPT Scam
 */
public class UsersDAO {

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
                        rs.getString(6),
                        rs.getString(7)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Check login
    public List<Users> checkLogin(String userName, String password) {
        try {
            String sql = """
                         SELECT COUNT(*) 
                         FROM Users 
                         WHERE UserName = ?
                         AND [Password] = ?
                         """;
            ResultSet rs = DBConnection.query(sql, userName, password);

            List<Users> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void test(){
        System.out.println("Hello World");
        // Duy has been joined
    }
}
