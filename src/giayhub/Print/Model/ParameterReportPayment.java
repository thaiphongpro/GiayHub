/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Print.Model;

import java.util.List;

/**
 *
 * @author phong
 */
public class ParameterReportPayment {
    int maHoaDon;
    String khachHang;
    String thuNgan;
    String ngayTao;
    double tongTien;
    double tienKhachDua;
    String phuongThucThanhToan;
    String tienThua;
    List<FieldReportPayment> fields;

    public ParameterReportPayment() {
    }

    public ParameterReportPayment(int maHoaDon, String khachHang, String thuNgan, String ngayTao, double tongTien, double tienKhachDua, String phuongThucThanhToan, String tienThua, List<FieldReportPayment> fields) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.thuNgan = thuNgan;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.tienThua = tienThua;
        this.fields = fields;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getThuNgan() {
        return thuNgan;
    }

    public void setThuNgan(String thuNgan) {
        this.thuNgan = thuNgan;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getTienThua() {
        return tienThua;
    }

    public void setTienThua(String tienThua) {
        this.tienThua = tienThua;
    }

    public List<FieldReportPayment> getFields() {
        return fields;
    }

    public void setFields(List<FieldReportPayment> fields) {
        this.fields = fields;
    }

}
