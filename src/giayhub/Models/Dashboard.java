/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class Dashboard {

    private int maDonHang;
    private int maKhachHang;
    private String ngayKhoiTao;
    private String trangThai;
    private int tongSoDonHang;
    private int donHangDaBan;
    private int donDangXuLy;
    private int donHoanThanh;

    public Dashboard() {
    }

    public Dashboard(int maDonHang, int maKhachHang, String ngayKhoiTao, String trangThai, int tongSoDonHang, int donHangDaBan, int donDangXuLy, int donHoanThanh) {
        this.maDonHang = maDonHang;
        this.maKhachHang = maKhachHang;
        this.ngayKhoiTao = ngayKhoiTao;
        this.trangThai = trangThai;
        this.tongSoDonHang = tongSoDonHang;
        this.donHangDaBan = donHangDaBan;
        this.donDangXuLy = donDangXuLy;
        this.donHoanThanh = donHoanThanh;
    }

    public Dashboard(int maDonHang, int maKhachHang, String ngayKhoiTao, String trangThai) {
        this.maDonHang = maDonHang;
        this.maKhachHang = maKhachHang;
        this.ngayKhoiTao = ngayKhoiTao;
        this.trangThai = trangThai;
    }

    // Constructor chỉ có Tổng số đơn hàng
    public Dashboard(int tongSoDonHang) {
        this.tongSoDonHang = tongSoDonHang;
    }

    // Constructor chỉ có Đơn hàng đã bán
    public Dashboard(int donHangDaBan, boolean isSoldOrders) {
        this.donHangDaBan = donHangDaBan;
    }

    // Constructor chỉ có Đơn hàng đang xử lý
    public Dashboard(boolean isProcessing, int donDangXuLy) {
        this.donDangXuLy = donDangXuLy;
    }

    // Constructor chỉ có Đơn hàng hoàn thành
    public Dashboard(String status, int donHoanThanh) {
        this.donHoanThanh = donHoanThanh;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getNgayKhoiTao() {
        return ngayKhoiTao;
    }

    public void setNgayKhoiTao(String ngayKhoiTao) {
        this.ngayKhoiTao = ngayKhoiTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getTongSoDonHang() {
        return tongSoDonHang;
    }

    public void setTongSoDonHang(int tongSoDonHang) {
        this.tongSoDonHang = tongSoDonHang;
    }

    public int getDonHangDaBan() {
        return donHangDaBan;
    }

    public void setDonHangDaBan(int donHangDaBan) {
        this.donHangDaBan = donHangDaBan;
    }

    public int getDonDangXuLy() {
        return donDangXuLy;
    }

    public void setDonDangXuLy(int donDangXuLy) {
        this.donDangXuLy = donDangXuLy;
    }

    public int getDonHoanThanh() {
        return donHoanThanh;
    }

    public void setDonHoanThanh(int donHoanThanh) {
        this.donHoanThanh = donHoanThanh;
    }

    @Override
    public String toString() {
        return "Dashboard{" + "maDonHang=" + maDonHang + ", maKhachHang=" + maKhachHang + ", ngayKhoiTao=" + ngayKhoiTao + ", trangThai=" + trangThai + ", tongSoDonHang=" + tongSoDonHang + ", donHangDaBan=" + donHangDaBan + ", donDangXuLy=" + donDangXuLy + ", donHoanThanh=" + donHoanThanh + '}';
    }

}
