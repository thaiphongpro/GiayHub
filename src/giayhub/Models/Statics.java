/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class Statics {
    private String nam;
    private String thang;
    private double tongDoanhThu;
    private int tongSoSPDaBan;
    private int tongSoDonHang;

    public Statics() {
    }

    public Statics(String nam, String thang, double tongDoanhThu, int tongSoSPDaBan, int tongSoDonHang) {
        this.nam = nam;
        this.thang = thang;
        this.tongDoanhThu = tongDoanhThu;
        this.tongSoSPDaBan = tongSoSPDaBan;
        this.tongSoDonHang = tongSoDonHang;
    }
    
    // Doanh thu theo thang
    public Statics(String nam, String thang, double tongDoanhThu) {
        this.nam = nam;
        this.thang = thang;
        this.tongDoanhThu = tongDoanhThu;
    }
    
    // Tong so san pham da ban theo thang
    public Statics(String nam, String thang, int tongSoSPDaBan) {
        this.nam = nam;
        this.thang = thang;
        this.tongSoSPDaBan = tongSoSPDaBan;
    }
    
    // Tong so don hang theo thang
//    public Statics(String nam, String thang, int tongSoDonHang) {
//        this.nam = nam;
//        this.thang = thang;
//        this.tongSoDonHang = tongSoDonHang;
//    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public int getTongSoSPDaBan() {
        return tongSoSPDaBan;
    }

    public void setTongSoSPDaBan(int tongSoSPDaBan) {
        this.tongSoSPDaBan = tongSoSPDaBan;
    }

    public int getTongSoDonHang() {
        return tongSoDonHang;
    }

    public void setTongSoDonHang(int tongSoDonHang) {
        this.tongSoDonHang = tongSoDonHang;
    }

    @Override
    public String toString() {
        return "Statics{" + "nam=" + nam + ", thang=" + thang + ", tongDoanhThu=" + tongDoanhThu + ", tongSoSPDaBan=" + tongSoSPDaBan + ", tongSoDonHang=" + tongSoDonHang + '}';
    }

}
