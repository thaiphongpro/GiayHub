/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package giayhub.Dashboard.form;

import giayhub.DAO.CartDAO;
import giayhub.DAO.InvoiceDAO;
import giayhub.DAO.ProductsDAO;
import giayhub.Models.CartItems;
import giayhub.Models.Invoices;
import giayhub.Models.Products;
import giayhub.Models.createInvoices;
import giayhub.Views.DanhSachKhachHang;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author phong
 */
public class InvoiceManagement extends javax.swing.JPanel {

    private DefaultTableModel dtmHoaDon;
    private DefaultTableModel dtmSanPham;
    private DefaultTableModel dtmGioHang = new DefaultTableModel();
    private InvoiceDAO service = new InvoiceDAO();
    private ProductsDAO serviceSanPham = new ProductsDAO();
    private CartDAO cartDao = new CartDAO();

    LocalDate ngayHienTai = LocalDate.now();

    int i = -1; // Hoa don

    int x = -1; // San pham
    int y = -1; // Gio hang

    public InvoiceManagement() {
        initComponents();

        dtmHoaDon = (DefaultTableModel) tbHoaDon1.getModel();
        dtmSanPham = (DefaultTableModel) tbSanPham.getModel();
        dtmGioHang = (DefaultTableModel) tbGioHang.getModel();

        showDataTable(service.getAll());
        showDataTableSanPham(serviceSanPham.getAllProduct());
        showDataTableGioHang(cartDao.getLists());

        txtSDTKhachHang.setEditable(false);
        txtMaKH.setEditable(false);

        txtMaHD.setEditable(false);
        txtNgayTao.setEditable(false);
        txtTongTien.setEditable(false);
        txtTienThua.setEditable(false);

    }

    public void setKhachHang(String hoTen, String sdt) {
        txtMaKH.setText(hoTen);
        txtSDTKhachHang.setText(sdt);
    }

    public void tinhTienThua() {

        double tongTienHoaDon = txtTongTien.getText().trim().isEmpty() ? 0 : parseCurrency(txtTongTien.getText().trim());
        double tienDua = txtTienKhachDua.getText().trim().isEmpty() ? 0 : parseCurrency(txtTienKhachDua.getText().trim());
        double tienChuyen = txtTienKhachChuyen.getText().trim().isEmpty() ? 0 : parseCurrency(txtTienKhachChuyen.getText().trim());

        double tienThua = (tienDua + tienChuyen) - tongTienHoaDon;

        if (tienThua >= 0) {
            txtTienThua.setText(formatVND(tienThua));
        } else if (tongTienHoaDon >= tienThua) {
            txtTienThua.setText("Chưa đủ tiền, thiếu: " + (-tienThua));
        } else {
            txtTienThua.setText("");
        }
    }

    public static String formatVND(double soTien) {
        DecimalFormat formatter = new DecimalFormat("#,###");

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(soTien) + " VND";
    }

    public static double parseCurrency(String input) {
        try {
            System.out.println("Input: " + input);

            // Xóa ký hiệu tiền tệ nếu có
            input = input.replace(" VND", "").replace("vnd", "").replace("đ", "").trim();

            // Xử lý dấu phân cách
            if (input.contains(".") && input.contains(",")) {
                input = input.replace(".", ""); // Xóa dấu chấm (ngăn cách nghìn)
                input = input.replace(",", "."); // Chuyển dấu phẩy thành dấu chấm (thập phân)
            } else if (input.contains(".") && !input.contains(",")) {
                // Nếu chỉ có dấu chấm (có thể là dấu thập phân) thì giữ nguyên
                // Không thay thế gì cả
            } else {
                // Nếu chỉ có dấu phẩy (sai định dạng) thì chuyển thành dấu chấm
                input = input.replace(",", ".");
            }

            System.out.println("Output: " + input);
            return Double.parseDouble(input);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Hoa don
    public void showDataTable(List<Invoices> lists) {
        dtmHoaDon.setRowCount(0);
        i = 1;
        for (Invoices invoices : lists) {
            dtmHoaDon.addRow(new Object[]{
                i++,
                invoices.getInvoiceID(),
                invoices.getOrderID(),
                invoices.getIssueDate(),
                invoices.getTotalMoney(),
                invoices.getPaymentMethod(),
                invoices.getPaymentStatus()
            });
        }
        i = -1;
    }

    // San pham
    public void showDataTableSanPham(List<Products> lists) {
        dtmSanPham.setRowCount(0);
        x = 1;
        for (Products products : lists) {
            dtmSanPham.addRow(new Object[]{
                x++,
                products.getProductID(),
                products.getProductName(),
                products.getDescription(),
                products.getPrice(),
                products.getStockQuantity(),
                products.getSize(),
                products.getColor()
            });
        }
        x = -1;
    }

    // Gio hang
    public void showDataTableGioHang(List<CartItems> lists) {
        dtmGioHang.setRowCount(0);
        y = 1;
        for (CartItems cartItems : lists) {
            dtmGioHang.addRow(new Object[]{
                y++,
                cartItems.getProductID(),
                cartItems.getProductName(),
                cartItems.getQuantity(),
                formatVND(cartItems.getPrice()),
                formatVND(cartItems.getTotalPrice())
            });
        }
        y = -1;
    }

    public createInvoices getFormDataOrders() {
        try {
            int orderID = service.layIdOrders() + 1;
            return new createInvoices(
                    orderID,
                    Integer.parseInt(txtMaKH.getText()),
                    String.valueOf(ngayHienTai),
                    "Chờ thanh toán");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public createInvoices getFormDataOrderDetails(int orderID) {
        try {
            int orderDetailsID = service.layIdOrderDetails() + 1;

            for (int y = 0; y < tbGioHang.getRowCount(); y++) {
                return new createInvoices(
                        orderDetailsID,
                        orderID,
                        Integer.parseInt(dtmGioHang.getValueAt(y, 1) + ""), // Ma San Pham
                        Integer.parseInt(dtmGioHang.getValueAt(y, 3) + ""), // So Luong
                        cartDao.getTotal()); // Tong Tien
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public createInvoices getFormDataInvoices(int orderID) {
        try {
            int invoiceID = service.layIdInvoices() + 1;
            return new createInvoices(
                    invoiceID,
                    orderID,
                    String.valueOf(ngayHienTai),
                    cartDao.getTotal(),
                    cbPhuongThucThanhToan.getSelectedItem().toString(),
                    "Chờ thanh toán");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public createInvoices getFormDataInvoices1() {
        i = tbHoaDon1.getSelectedRow();
        try {
            return new createInvoices(
                    Integer.parseInt(dtmHoaDon.getValueAt(i, 1) + ""),
                    Integer.parseInt(dtmHoaDon.getValueAt(i, 2) + ""),
                    dtmHoaDon.getValueAt(i, 3) + "",
                    parseCurrency(dtmHoaDon.getValueAt(i, 4) + ""),
                    dtmHoaDon.getValueAt(i, 5) + "",
                    dtmHoaDon.getValueAt(i, 6) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void detailThongTinHoaDon() {
        y = tbHoaDon1.getSelectedRow();
        txtMaHD.setText(dtmHoaDon.getValueAt(y, 1) + "");
        txtNgayTao.setText(dtmHoaDon.getValueAt(y, 3) + "");
        txtTongTien.setText(dtmHoaDon.getValueAt(y, 4) + "");
        cbPhuongThucThanhToan.setSelectedItem(dtmHoaDon.getValueAt(y, 5).toString());
        lblTongTienHoaDon.setText(dtmHoaDon.getValueAt(y, 4) + " VND");
    }

    public boolean validateFormHoaDon() {
        if (txtSDTKhachHang.getText().isBlank() && txtMaKH.getText().isBlank() && tbGioHang.getRowCount() <= 0 && cbPhuongThucThanhToan.getSelectedItem().equals("-Chọn Phương Thức Thanh Toán-")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng thêm thông tin khách hàng và thêm sản phẩm vào giỏ hàng và chọn phương thức thanh toán");
            return false;
        }
        if (txtSDTKhachHang.getText().isBlank() || txtMaKH.getText().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn khách hàng");
            return false;
        }
        if (tbGioHang.getRowCount() <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng thêm sản phẩm vào giỏ hàng trước khi tạo hóa đơn");
            return false;
        }
        if (cbPhuongThucThanhToan.getSelectedItem().equals("-Chọn Phương Thức Thanh Toán-")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn phương thức thanh toán trước khi tạo hóa đơn");
            return false;
        }
        return true;
    }

    public boolean validateFormHuyHoaDon() {
        i = tbHoaDon1.getSelectedRow();
        String trangThai = dtmHoaDon.getValueAt(i, 6).toString();
        if (trangThai.equalsIgnoreCase("Ðã h?y")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Hóa đơn này đã hủy rồi");
            return false;
        }
        if (trangThai.equalsIgnoreCase("Ðã thanh toán")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không thể hủy hóa đơn đã thanh toán");
            return false;
        }
        return true;
    }

    public boolean validateFormThanhToan() {
        i = tbHoaDon1.getSelectedRow();
        if (i == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn một hóa đơn");
            return false;
        }
        String trangThai = dtmHoaDon.getValueAt(i, 6).toString();
        if (trangThai.equalsIgnoreCase("Ðã h?y")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không thể thanh toán hóa đơn đã hủy");
            return false;
        }
        return true;
    }

    public boolean validateFormTinhTienThua() {
        String tienDua = txtTienKhachDua.getText().trim();
        String tienChuyen = txtTienKhachChuyen.getText().trim();

        // Check so duong
        if (!tienDua.matches("\\d+")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tiền khách đưa bắt buộc phải là số nguyên dương");
            return false;
        }
        if (!tienChuyen.matches("\\d+")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tiền khách chuyển bắt buộc phải là số nguyên dương");
            return false;
        }
        return true;
    }

    public void clearForm() {
        txtSDTKhachHang.setText("");
        txtMaKH.setText("");
        txtMaHD.setText("");
        txtNgayTao.setText("");
        txtTongTien.setText("");
        cbPhuongThucThanhToan.setSelectedIndex(0);
        txtTienKhachDua.setText("");
        txtTienKhachChuyen.setText("");
        txtTienThua.setText("");
        lblTongTienHoaDon.setText("0");
        dtmGioHang.setRowCount(0);
        cartDao.clearCart();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new giayhub.Dashboard.swing.RoundPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHoaDon1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cbTimKiemHoaDon = new javax.swing.JComboBox<>();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        btnTaoHoaDon = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cbTimKiemSanPham = new javax.swing.JComboBox<>();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSDTKhachHang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        btnTimKH = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbPhuongThucThanhToan = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTienKhachChuyen = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lblTongTienHoaDon = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1101, 651));

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Inter 24pt", 1, 18), new java.awt.Color(255, 153, 0))); // NOI18N

        tbHoaDon1.setFont(new java.awt.Font("Inter 24pt", 0, 12)); // NOI18N
        tbHoaDon1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Mã HĐ", "Mã ĐH", "Ngày Tạo", "Tổng Tiền", "PTTT", "Trạng Thái"
            }
        ));
        tbHoaDon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDon1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbHoaDon1);

        jLabel8.setText("Tìm kiếm:");

        cbTimKiemHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Hóa Đơn", "Mã Đơn Hàng" }));

        txtTimKiemHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHoaDonKeyReleased(evt);
            }
        });

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTaoHoaDon)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Inter 24pt", 1, 18), new java.awt.Color(255, 153, 0))); // NOI18N
        jPanel1.setToolTipText("");

        btnThem.setText("Thêm vào giỏ hàng");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Mã SP", "Tên SP", "Mô Tả", "Giá", "Tồn Kho", "Size", "Màu Sắc"
            }
        ));
        jScrollPane3.setViewportView(tbSanPham);

        jLabel9.setText("Tìm kiếm:");

        cbTimKiemSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã SP", "Tên SP" }));

        txtTimKiemSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addContainerGap())
            .addComponent(jScrollPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(jLabel9)
                    .addComponent(cbTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Inter 24pt", 1, 18), new java.awt.Color(255, 153, 0))); // NOI18N
        jPanel3.setToolTipText("");

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "#", "Mã SP", "Tên SP", "Số Lượng", "Giá Tiền", "Tổng Tiền"
            }
        ));
        jScrollPane2.setViewportView(tbGioHang);

        jButton1.setText("Làm mới bảng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh toán"));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Inter 24pt", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("Thông tin khách hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("SDT");

        btnTimKH.setText("Tìm");
        btnTimKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKHMouseClicked(evt);
            }
        });
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Mã Khách Hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDTKhachHang)
                            .addComponent(jLabel2)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKH))
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKH))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Inter 24pt", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 0));
        jLabel4.setText("Thông tin hóa đơn");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã HD:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Ngày tạo:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tổng:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Phương thức thanh toán:");

        cbPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Chọn Phương Thức Thanh Toán-", "Tiền mặt", "Chuyển khoản", "Thẻ tín dụng" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tiền khách đưa:");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tiền khách chuyển:");

        txtTienKhachChuyen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachChuyenKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tiền thừa:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Tổng tiền:");

        lblTongTienHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTienHoaDon.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTienHoaDon.setText("0");

        btnThanhToan.setFont(new java.awt.Font("Inter 24pt", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuyHoaDon.setFont(new java.awt.Font("Inter 24pt", 1, 14)); // NOI18N
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaHD))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayTao))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTien))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienKhachDua))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienKhachChuyen))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienThua))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongTienHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(cbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTienKhachChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblTongTienHoaDon))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        x = tbSanPham.getSelectedRow();

        if (x == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Chưa chọn sản phẩm");
            return;
        }

        int productId = Integer.parseInt(dtmSanPham.getValueAt(x, 1).toString());
        String productName = dtmSanPham.getValueAt(x, 2).toString();
        double price = Double.parseDouble(dtmSanPham.getValueAt(x, 4).toString());
        int stockQuantity = Integer.parseInt(dtmSanPham.getValueAt(x, 5).toString());

        try {
            if (cartDao.checkStock(stockQuantity)) {
                cartDao.addToCart(productId, productName, price);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Đã thêm vào giỏ hàng");
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vượt quá số lượng trong kho");
            }
            showDataTableGioHang(cartDao.getLists());
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi thêm vào giỏ hàng: " + e.getMessage());
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cartDao.clearCart();
        dtmGioHang.setRowCount(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonKeyReleased
        if (cbTimKiemHoaDon.getSelectedIndex() == 0) {
            showDataTable(service.searchMaHD(txtTimKiemHoaDon.getText()));
        }
        if (cbTimKiemHoaDon.getSelectedIndex() == 1) {
            showDataTable(service.searchMaDH(txtTimKiemHoaDon.getText()));
        }
    }//GEN-LAST:event_txtTimKiemHoaDonKeyReleased

    private void txtTimKiemSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamKeyReleased
        if (cbTimKiemSanPham.getSelectedIndex() == 0) {
            showDataTableSanPham(serviceSanPham.timKiemMaSP(txtTimKiemSanPham.getText()));
        }
        if (cbTimKiemSanPham.getSelectedIndex() == 1) {
            showDataTableSanPham(serviceSanPham.timKiemTenSP(txtTimKiemSanPham.getText()));
        }
    }//GEN-LAST:event_txtTimKiemSanPhamKeyReleased

    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        DanhSachKhachHang danhSachKhachHang = new DanhSachKhachHang(this);
        danhSachKhachHang.setVisible(true);
    }//GEN-LAST:event_btnTimKHActionPerformed

    private void btnTimKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKHMouseClicked

    }//GEN-LAST:event_btnTimKHMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        if (validateFormHoaDon()) {
            try {
                createInvoices newOrder = getFormDataOrders();
                service.addOrders(newOrder);

                int orderID = service.layIdOrders();

                createInvoices orderDetail = getFormDataOrderDetails(orderID);
                service.addOrderDetails(orderDetail);

                createInvoices invoices = getFormDataInvoices(orderID);
                service.addInvoices(invoices);

                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Tạo hóa đơn thành công!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                showDataTable(service.getAll());
            }
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tbHoaDon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDon1MouseClicked
        y = tbHoaDon1.getSelectedRow();
        detailThongTinHoaDon();
    }//GEN-LAST:event_tbHoaDon1MouseClicked

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        if (validateFormTinhTienThua()) {
            tinhTienThua();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void txtTienKhachChuyenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachChuyenKeyReleased
        if (validateFormTinhTienThua()) {
            tinhTienThua();
        }
    }//GEN-LAST:event_txtTienKhachChuyenKeyReleased

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        i = tbHoaDon1.getSelectedRow();
        if (i == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn hóa đơn cần hủy");
            return;
        }
        String thongBao = "<html>"
                + "<b>Thông tin hóa đơn:</b><br>"
                + "<b>#</b> " + dtmHoaDon.getValueAt(i, 0) + "<br>"
                + "<b>Mã Hóa Đơn:</b> " + dtmHoaDon.getValueAt(i, 1) + "<br>"
                + "<b>Mã Đơn Hàng:</b> " + dtmHoaDon.getValueAt(i, 2) + "<br>"
                + "<b>Ngày Tạo:</b> " + dtmHoaDon.getValueAt(i, 3) + "<br>"
                + "<b>Tổng Tiền:</b> " + dtmHoaDon.getValueAt(i, 4) + "<br>"
                + "<b>Phương Thức Thanh Toán:</b> " + dtmHoaDon.getValueAt(i, 5) + "<br>"
                + "<b>Trạng Thái:</b> " + dtmHoaDon.getValueAt(i, 6) + "<br>"
                + "</html>";
        int o = JOptionPane.showConfirmDialog(
                null,
                "Xác nhận hủy hóa đơn này?",
                thongBao,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        try {
            if (validateFormHuyHoaDon()) {
                if (o == JOptionPane.YES_OPTION) {
                    service.huyHoaDon(getFormDataInvoices1());
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Đã hủy hóa đơn!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            showDataTable(service.getAll());
            clearForm();
        }
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (validateFormThanhToan()) {
            try {
                service.thanhToanHoaDon(getFormDataInvoices1());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thanh toán thành công!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                showDataTable(service.getAll());
                clearForm();
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JComboBox<String> cbPhuongThucThanhToan;
    private javax.swing.JComboBox<String> cbTimKiemHoaDon;
    private javax.swing.JComboBox<String> cbTimKiemSanPham;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblTongTienHoaDon;
    private giayhub.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHoaDon1;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDTKhachHang;
    private javax.swing.JTextField txtTienKhachChuyen;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiemHoaDon;
    private javax.swing.JTextField txtTimKiemSanPham;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
