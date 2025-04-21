/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.Dashboard;
import giayhub.Models.OrderHistory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phong
 */
public class DashboardDAO {

    public List<Dashboard> getAll() {
        try {
            String sql = """
                         SELECT 
                         o.OrderID as maDonHang, 
                         c.CustomerID as maKhachHang, 
                         o.OrderDate as ngayKhoiTao, 
                         o.[Status] as trangThai
                         FROM Orders o
                         INNER JOIN Customers c
                             ON o.CustomerID = c.CustomerID
                         WHERE o.[Status] = N'Chờ thanh toán'
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<Dashboard> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Dashboard(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Don hang da hoan thanh
    public List<Dashboard> getAllDonHoanThanh() {
        try {
            String sql = """
                         SELECT 
                         o.OrderID as maDonHang, 
                         c.CustomerID as maKhachHang, 
                         o.OrderDate as ngayKhoiTao, 
                         o.[Status] as trangThai
                         FROM Orders o
                         INNER JOIN Customers c
                             ON o.CustomerID = c.CustomerID
                         WHERE o.[Status] = N'Đã thanh toán'
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<Dashboard> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Dashboard(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTongSoDonHang() {
        int tongSoDonHang = 0;
        try {
            String sql = """
                         SELECT 
                         COUNT(*)
                         FROM Orders o
                         INNER JOIN Customers c
                             ON o.CustomerID = c.CustomerID
                         """;
            ResultSet rs = DBConnection.query(sql);

            while (rs.next()) {
                tongSoDonHang = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongSoDonHang;
    }

    public int getDonHangDaBan() {
        int donHangDaBan = 0;
        try {
            String sql = """
                         SELECT 
                         COUNT(*)
                         FROM Orders o
                         INNER JOIN Customers c
                         ON o.CustomerID = c.CustomerID
                         WHERE o.[Status] = N'Đã thanh toán'
                         """;
            ResultSet rs = DBConnection.query(sql);

            while (rs.next()) {
                donHangDaBan = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donHangDaBan;
    }

    public int getDonDangXuLy() {
        int donDangXuLy = 0;
        try {
            String sql = """
                         SELECT 
                         COUNT(*)
                         FROM Orders o
                         INNER JOIN Customers c
                             ON o.CustomerID = c.CustomerID
                         WHERE o.[Status] = N'Chờ thanh toán'
                         """;
            ResultSet rs = DBConnection.query(sql);

            while (rs.next()) {
                donDangXuLy = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donDangXuLy;
    }

    // Tong so don hang
    public int getTongSoSanPham() {
        int tongSoSanPham = 0;
        try {
            String sql = """
                         SELECT COUNT(*)
                         FROM Products
                         """;
            ResultSet rs = DBConnection.query(sql);

            while (rs.next()) {
                tongSoSanPham = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongSoSanPham;
    }

    // Lich su don hang
    public List<OrderHistory> getAllLichSuDonHang() {
        try {
            String sql = """
                         SELECT o.OrderID, c.FullName, o.OrderDate, o.Status, p.ProductName, od.Quantity, od.TotalPrice
                         FROM Orders o
                         INNER JOIN Customers c
                             ON o.CustomerID = c.CustomerID
                         INNER JOIN OrderDetails od
                             ON o.OrderID = od.OrderID
                         INNER JOIN Products p
                             ON od.ProductID = p.ProductID
                         ORDER BY o.OrderDate DESC
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<OrderHistory> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new OrderHistory(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Tim kiem theo ten ma don hang, ma khach hang
     */
    
    // Ma Don hang dang xu ly
    public List<Dashboard> timKiemMaDonHangDangXuLy(String maDH) {
        List<Dashboard> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT \n"
                    + "                         o.OrderID as maDonHang, \n"
                    + "                         c.CustomerID as maKhachHang, \n"
                    + "                         o.OrderDate as ngayKhoiTao, \n"
                    + "                         o.[Status] as trangThai\n"
                    + "                         FROM Orders o\n"
                    + "                         INNER JOIN Customers c\n"
                    + "                         ON o.CustomerID = c.CustomerID\n"
                    + "                         WHERE o.[Status] = N'Chờ thanh toán'\n"
                    + "                         AND o.OrderID LIKE '%" + maDH + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Dashboard dashboard = new Dashboard();
                dashboard.setMaDonHang(rs.getInt(1));
                dashboard.setMaKhachHang(rs.getInt(2));
                dashboard.setNgayKhoiTao(rs.getString(3));
                dashboard.setTrangThai(rs.getString(4));

                listSearch.add(dashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    // Ma khach hang dang xu ly
    public List<Dashboard> timKiemMaKhacHangDangXuLy(String maKH) {
        List<Dashboard> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT \n"
                    + "                         o.OrderID as maDonHang, \n"
                    + "                         c.CustomerID as maKhachHang, \n"
                    + "                         o.OrderDate as ngayKhoiTao, \n"
                    + "                         o.[Status] as trangThai\n"
                    + "                         FROM Orders o\n"
                    + "                         INNER JOIN Customers c\n"
                    + "                         ON o.CustomerID = c.CustomerID\n"
                    + "                         WHERE c.CustomerID LIKE '%" + maKH + "%'\n"
                    + "                         AND o.[Status] = N'Chờ thanh toán'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Dashboard dashboard = new Dashboard();
                dashboard.setMaDonHang(rs.getInt(1));
                dashboard.setMaKhachHang(rs.getInt(2));
                dashboard.setNgayKhoiTao(rs.getString(3));
                dashboard.setTrangThai(rs.getString(4));

                listSearch.add(dashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    // Ma don hang Lich su don hang
    public List<OrderHistory> timKiemMaDonHangLichSu(String maDH) {
        List<OrderHistory> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT o.OrderID, c.FullName, o.OrderDate, o.Status, p.ProductName, od.Quantity, od.TotalPrice\n"
                    + "FROM Orders o\n"
                    + "INNER JOIN Customers c\n"
                    + "ON o.CustomerID = c.CustomerID\n"
                    + "INNER JOIN OrderDetails od\n"
                    + "ON o.OrderID = od.OrderID\n"
                    + "INNER JOIN Products p\n"
                    + "ON od.ProductID = p.ProductID\n"
                    + "WHERE o.OrderID LIKE '%" + maDH + "%'\n"
                    + "ORDER BY o.OrderDate DESC";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                OrderHistory dashboard = new OrderHistory();
                dashboard.setOrderID(rs.getInt(1));
                dashboard.setFullName(rs.getString(2));
                dashboard.setOrderDate(rs.getString(3));
                dashboard.setStatus(rs.getString(4));
                dashboard.setProductName(rs.getString(5));
                dashboard.setQuantity(rs.getInt(6));
                dashboard.setTotalPrice(rs.getDouble(7));

                listSearch.add(dashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    // Ma khach hang Lich su don hang
    public List<OrderHistory> timKiemMaKhachHangLichSu(String maKH) {
        List<OrderHistory> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT o.OrderID, c.FullName, o.OrderDate, o.Status, p.ProductName, od.Quantity, od.TotalPrice\n"
                    + "FROM Orders o\n"
                    + "INNER JOIN Customers c\n"
                    + "ON o.CustomerID = c.CustomerID\n"
                    + "INNER JOIN OrderDetails od\n"
                    + "ON o.OrderID = od.OrderID\n"
                    + "INNER JOIN Products p\n"
                    + "ON od.ProductID = p.ProductID\n"
                    + "WHERE c.CustomerID LIKE '%"+maKH+"%'\n"
                    + "ORDER BY o.OrderDate DESC";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                OrderHistory dashboard = new OrderHistory();
                dashboard.setOrderID(rs.getInt(1));
                dashboard.setFullName(rs.getString(2));
                dashboard.setOrderDate(rs.getString(3));
                dashboard.setStatus(rs.getString(4));
                dashboard.setProductName(rs.getString(5));
                dashboard.setQuantity(rs.getInt(6));
                dashboard.setTotalPrice(rs.getDouble(7));

                listSearch.add(dashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
    
    // Ma Don hang da hoan thanh
    public List<Dashboard> timKiemMaDonHangDaHoanThanh(String maDH) {
        List<Dashboard> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT \n"
                    + "                         o.OrderID as maDonHang, \n"
                    + "                         c.CustomerID as maKhachHang, \n"
                    + "                         o.OrderDate as ngayKhoiTao, \n"
                    + "                         o.[Status] as trangThai\n"
                    + "                         FROM Orders o\n"
                    + "                         INNER JOIN Customers c\n"
                    + "                         ON o.CustomerID = c.CustomerID\n"
                    + "                         WHERE o.[Status] = N'Đã thanh toán'\n"
                    + "                         AND o.OrderID LIKE '%" + maDH + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Dashboard dashboard = new Dashboard();
                dashboard.setMaDonHang(rs.getInt(1));
                dashboard.setMaKhachHang(rs.getInt(2));
                dashboard.setNgayKhoiTao(rs.getString(3));
                dashboard.setTrangThai(rs.getString(4));

                listSearch.add(dashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    // Ma khach hang dang xu ly
    public List<Dashboard> timKiemMaKhacHangDaHoanThanh(String maKH) {
        List<Dashboard> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT \n"
                    + "                         o.OrderID as maDonHang, \n"
                    + "                         c.CustomerID as maKhachHang, \n"
                    + "                         o.OrderDate as ngayKhoiTao, \n"
                    + "                         o.[Status] as trangThai\n"
                    + "                         FROM Orders o\n"
                    + "                         INNER JOIN Customers c\n"
                    + "                         ON o.CustomerID = c.CustomerID\n"
                    + "                         WHERE o.[Status] = N'Đã thanh toán'\n"
                    + "                         AND c.CustomerID LIKE '%" + maKH + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Dashboard dashboard = new Dashboard();
                dashboard.setMaDonHang(rs.getInt(1));
                dashboard.setMaKhachHang(rs.getInt(2));
                dashboard.setNgayKhoiTao(rs.getString(3));
                dashboard.setTrangThai(rs.getString(4));

                listSearch.add(dashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
}
