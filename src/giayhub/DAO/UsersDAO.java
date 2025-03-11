/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

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
                        rs.getInt(6)));
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
}
