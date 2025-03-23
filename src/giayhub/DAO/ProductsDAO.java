/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.DAO;

import giayhub.Models.ImportProductInformation;
import giayhub.Models.ImportProducts;
import giayhub.Models.Products;
import giayhub.Models.Suppliers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phong
 */
public class ProductsDAO {

    public List<Suppliers> getAllNCC() {
        try {
            String sql = """
                         SELECT * FROM Suppliers
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<Suppliers> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Suppliers(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void them(Suppliers ncc) {
        String sql = """
                     INSERT INTO Suppliers(SupplierID, SupplierName, ContactName, PhoneNumber, Address) VALUES(?,?,?,?,?)
                     """;
        DBConnection.update(sql, ncc.getSupplierID(), ncc.getSupplierName(), ncc.getContactName(), ncc.getPhoneNumber(), ncc.getAddress());
    }

    public void sua(Suppliers ncc, int id) {
        String sql = """
                     UPDATE Suppliers SET SupplierName = ?, ContactName = ?, PhoneNumber = ?, Address = ?  WHERE SupplierID = ?
                     """;
        DBConnection.update(sql, ncc.getSupplierName(), ncc.getContactName(), ncc.getPhoneNumber(), ncc.getAddress(), ncc.getSupplierID());
    }

    public void xoa(Suppliers ncc, int id) {
        String sql = """
                    DELETE FROM Suppliers WHERE SupplierID = ?
                    """;
        DBConnection.update(sql, ncc.getSupplierID());
    }

    public static List<Suppliers> searchTenNCC(String tenNCC) {
        List<Suppliers> searchNCC = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Suppliers WHERE SupplierName LIKE'%" + tenNCC + "%'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Suppliers ncc = new Suppliers();
                ncc.setSupplierID(rs.getInt(1));
                ncc.setSupplierName(rs.getString(2));
                ncc.setContactName(rs.getString(3));
                ncc.setPhoneNumber(rs.getString(4));
                ncc.setAddress(rs.getString(5));

                searchNCC.add(ncc);
            }
        } catch (Exception e) {
            System.out.println("Issue to search: " + e.getMessage());
        }
        return searchNCC;
    }

    public List<Products> getAllProduct() {
        try {
            String sql = """
                         SELECT * FROM Products
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<Products> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Products(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
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

    public void addProduct(Products products) {
        try {
            String sql = """
                         INSERT INTO Products (ProductID, ProductName, Description, Price, StockQuantity, Size, Color) 
                         VALUES
                         (?, ?, ?, ?, ?, ?, ?)
                          """;
            DBConnection.update(sql,
                    products.getProductID(),
                    products.getProductName(),
                    products.getDescription(),
                    products.getPrice(),
                    products.getStockQuantity(),
                    products.getSize(),
                    products.getColor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editProduct(Products products) {
        try {
            String sql = """
                         UPDATE Products
                         SET ProductName = ?, [Description] = ?, Price = ?, StockQuantity = ?, [Size] = ?, Color = ?
                         WHERE ProductID = ?
                         """;
            DBConnection.update(sql,
                    products.getProductName(),
                    products.getDescription(),
                    products.getPrice(),
                    products.getStockQuantity(),
                    products.getSize(),
                    products.getColor(),
                    products.getProductID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(Products products) {
        try {
            String sql = """
                         DELETE FROM Products
                         WHERE ProductID = ?
                         """;
            DBConnection.update(sql, products.getProductID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ImportProductInformation> getAllImportProductInformation() {
        try {
            String sql = """
                         SELECT ip.ImportID ,p.ProductName, ip.Quantity,ip.ImportPrice,ip.ImportDate, s.SupplierName, s.PhoneNumber, s.Address
                         FROM Suppliers s
                         INNER JOIN ImportProducts ip
                             ON s.SupplierID = ip.SupplierID
                         INNER JOIN Products p
                             ON ip.ProductID = p.ProductID
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<ImportProductInformation> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new ImportProductInformation(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tim kiem theo ma nhap hang
    public List<ImportProductInformation> timMaNhapHang(String MaNH) {
        List<ImportProductInformation> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT ip.ImportID ,p.ProductName, ip.Quantity,ip.ImportPrice,ip.ImportDate, s.SupplierName, s.PhoneNumber, s.Address\n"
                    + "FROM Suppliers s\n"
                    + "INNER JOIN ImportProducts ip\n"
                    + "    ON s.SupplierID = ip.SupplierID\n"
                    + "INNER JOIN Products p\n"
                    + "    ON ip.ProductID = p.ProductID\n"
                    + "WHERE ip.ImportID LIKE '%" + MaNH + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ImportProductInformation ipi = new ImportProductInformation();
                ipi.setImportID(rs.getInt(1));
                ipi.setProductName(rs.getString(2));
                ipi.setQuantity(rs.getInt(3));
                ipi.setImportPrice(rs.getDouble(4));
                ipi.setImportDate(rs.getString(5));
                ipi.setSupplierName(rs.getString(6));
                ipi.setSupplierPhoneNumber(rs.getString(7));
                ipi.setSupplierAddress(rs.getString(8));

                listSearch.add(ipi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    // Tim kiem theo ten san pham nha cung cap
    public List<ImportProductInformation> timTenSanPhamNCC(String tenSPNCC) {
        List<ImportProductInformation> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT ip.ImportID ,p.ProductName, ip.Quantity,ip.ImportPrice,ip.ImportDate, s.SupplierName, s.PhoneNumber, s.Address\n"
                    + "FROM Suppliers s\n"
                    + "INNER JOIN ImportProducts ip\n"
                    + "    ON s.SupplierID = ip.SupplierID\n"
                    + "INNER JOIN Products p\n"
                    + "    ON ip.ProductID = p.ProductID\n"
                    + "WHERE p.ProductName LIKE '%" + tenSPNCC + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ImportProductInformation ipi = new ImportProductInformation();
                ipi.setImportID(rs.getInt(1));
                ipi.setProductName(rs.getString(2));
                ipi.setQuantity(rs.getInt(3));
                ipi.setImportPrice(rs.getDouble(4));
                ipi.setImportDate(rs.getString(5));
                ipi.setSupplierName(rs.getString(6));
                ipi.setSupplierPhoneNumber(rs.getString(7));
                ipi.setSupplierAddress(rs.getString(8));

                listSearch.add(ipi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    // Tim kiem theo nha cung cap
    public List<ImportProductInformation> timNhaCungCap(String nhaCungCap) {
        List<ImportProductInformation> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT ip.ImportID ,p.ProductName, ip.Quantity,ip.ImportPrice,ip.ImportDate, s.SupplierName, s.PhoneNumber, s.Address\n"
                    + "FROM Suppliers s\n"
                    + "INNER JOIN ImportProducts ip\n"
                    + "    ON s.SupplierID = ip.SupplierID\n"
                    + "INNER JOIN Products p\n"
                    + "    ON ip.ProductID = p.ProductID\n"
                    + "WHERE s.SupplierName LIKE '%" + nhaCungCap + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ImportProductInformation ipi = new ImportProductInformation();
                ipi.setImportID(rs.getInt(1));
                ipi.setProductName(rs.getString(2));
                ipi.setQuantity(rs.getInt(3));
                ipi.setImportPrice(rs.getDouble(4));
                ipi.setImportDate(rs.getString(5));
                ipi.setSupplierName(rs.getString(6));
                ipi.setSupplierPhoneNumber(rs.getString(7));
                ipi.setSupplierAddress(rs.getString(8));

                listSearch.add(ipi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    // Tim kiem theo so dien thoai nha cung cap
    public List<ImportProductInformation> timSDTNhaCungCap(String sdtNCC) {
        List<ImportProductInformation> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT ip.ImportID ,p.ProductName, ip.Quantity,ip.ImportPrice,ip.ImportDate, s.SupplierName, s.PhoneNumber, s.Address\n"
                    + "FROM Suppliers s\n"
                    + "INNER JOIN ImportProducts ip\n"
                    + "    ON s.SupplierID = ip.SupplierID\n"
                    + "INNER JOIN Products p\n"
                    + "    ON ip.ProductID = p.ProductID\n"
                    + "WHERE s.PhoneNumber LIKE '%" + sdtNCC + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ImportProductInformation ipi = new ImportProductInformation();
                ipi.setImportID(rs.getInt(1));
                ipi.setProductName(rs.getString(2));
                ipi.setQuantity(rs.getInt(3));
                ipi.setImportPrice(rs.getDouble(4));
                ipi.setImportDate(rs.getString(5));
                ipi.setSupplierName(rs.getString(6));
                ipi.setSupplierPhoneNumber(rs.getString(7));
                ipi.setSupplierAddress(rs.getString(8));

                listSearch.add(ipi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    /**
     * Thiet lap thong tin nguon nhap
     */
    public List<Suppliers> getAllNCC1() {
        try {
            String sql = """
                         SELECT SupplierID, SupplierName, ContactName FROM Suppliers
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<Suppliers> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Suppliers(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Products> getAllSP() {
        try {
            String sql = """
                         SELECT ProductID, ProductName, Price FROM Products
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<Products> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new Products(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ImportProducts> getAllNhapSP() {
        try {
            String sql = """
                         SELECT * FROM ImportProducts
                         """;
            ResultSet rs = DBConnection.query(sql);

            List<ImportProducts> lists = new ArrayList<>();

            while (rs.next()) {
                lists.add(new ImportProducts(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Them nhap san pham
    public void themNhapSP(ImportProducts ip) {
        try {
            String sql = """
                         INSERT INTO ImportProducts (ImportID, ProductID, SupplierID, ImportDate, Quantity, ImportPrice)
                         VALUES
                         (?, ?, ?, ?, ?, ?)
                         """;
            DBConnection.update(sql,
                    ip.getImportID(),
                    ip.getProductID(),
                    ip.getSupplierID(),
                    ip.getImportDate(),
                    ip.getQuantity(),
                    ip.getImportPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sua nhap san pham
    public void suaNhapSP(ImportProducts ip) {
        try {
            String sql = """
                         UPDATE ImportProducts
                         SET ProductID = ?, SupplierID = ?, Quantity = ?, ImportPrice = ?
                         WHERE ImportID = ?
                         """;
            DBConnection.update(sql,
                    ip.getProductID(),
                    ip.getSupplierID(),
                    ip.getQuantity(),
                    ip.getImportPrice(),
                    ip.getImportID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Xoa nhap san pham
    public void xoaNhapSP(ImportProducts ip){
        try {
            String sql = """
                         DELETE FROM ImportProducts
                         WHERE ImportID = ?
                         """;
            DBConnection.update(sql, ip.getImportID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Tim kiem san pham
    public List<Products> timKiemMaSP(String maSP){
        List<Products> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Products WHERE ProductID = '"+maSP+"'";
            ResultSet rs = stm.executeQuery(sql);
            
            while (rs.next()) {                
                Products products = new Products();
                products.setProductID(rs.getInt(1));
                products.setProductName(rs.getString(2));
                products.setDescription(rs.getString(3));
                products.setPrice(rs.getDouble(4));
                products.setStockQuantity(rs.getInt(5));
                products.setSize(rs.getString(6));
                products.setColor(rs.getString(7));
                
                listSearch.add(products);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
    
    // Tim kiem ten san pham
    public List<Products> timKiemTenSP(String tenSP){
        List<Products> listSearch = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Products WHERE ProductName = '"+tenSP+"'";
            ResultSet rs = stm.executeQuery(sql);
            
            while (rs.next()) {                
                Products products = new Products();
                products.setProductID(rs.getInt(1));
                products.setProductName(rs.getString(2));
                products.setDescription(rs.getString(3));
                products.setPrice(rs.getDouble(4));
                products.setStockQuantity(rs.getInt(5));
                products.setSize(rs.getString(6));
                products.setColor(rs.getString(7));
                
                listSearch.add(products);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
}
