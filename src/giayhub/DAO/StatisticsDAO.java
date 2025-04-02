/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.Statics;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author phong
 */
public class StatisticsDAO {

    // Doanh thu theo thang
    public List<Statics> getThongKe(int month, int year) {
        try {
            String sql = "SELECT \n"
                    + "    YEAR(o.OrderDate) AS Nam, \n"
                    + "    MONTH(o.OrderDate) AS Thang, \n"
                    + "    SUM(od.TotalPrice) AS doanhThu, \n"
                    + "    COUNT(od.Quantity) AS tongSoDaBan, \n"
                    + "    COUNT(o.OrderID) AS tongSoDonHang\n"
                    + "FROM Orders o\n"
                    + "INNER JOIN OrderDetails od ON o.OrderID = od.OrderID\n"
                    + "WHERE o.[Status] = N'Đã thanh toán'\n"
                    + "AND MONTH(o.OrderDate) = '" + month + "'\n"
                    + "AND YEAR(o.OrderDate) = '" + year + "'\n"
                    + "GROUP BY YEAR(o.OrderDate), MONTH(o.OrderDate)\n"
                    + "ORDER BY Nam DESC, Thang DESC";
            ResultSet rs = DBConnection.query(sql);

            List<Statics> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Statics(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getInt(5)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
