/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package giayhub.Dashboard.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.lowagie.text.Font;
import giayhub.DAO.DashboardDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import giayhub.Models.Dashboard;
import giayhub.Models.OrderHistory;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;

/**
 *
 * @author phong
 */
public class DashboardManagement extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtmDonHoanThanh;
    private DefaultTableModel dtmLichSuDonHang;
    private DashboardDAO service = new DashboardDAO();

    int i = -1;

    int x = -1;
    int y = -1;

    public DashboardManagement() {
        initComponents();
        dtm = (DefaultTableModel) tbDonHangChoXuLy.getModel();
        dtmLichSuDonHang = (DefaultTableModel) tbLichSuDonHang.getModel();
        dtmDonHoanThanh = (DefaultTableModel) tbDonHangDaHoanThanh.getModel();

        showDataTable(service.getAll());
        showDataTableLSDH(service.getAllLichSuDonHang());
        showDataTableDonHoanThanh(service.getAllDonHoanThanh());

        init();
    }

    public void init() {
        jPanel5.setLayout(new GridLayout(1, 4, 20, 20)); // 1 hang, 4 cot, khoang cach

        lblTongSoSanPham.setText(service.getTongSoSanPham() + "");
        lblTongSoDonHang.setText(service.getTongSoDonHang() + "");
        lblDonDangXuLy.setText(service.getDonDangXuLy() + "");
        lblDonHoanThanh.setText(service.getDonHangDaBan() + "");

        txtTimKiem.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm...");

        // Mau cam
        TableCellRenderer orangeColumnRender = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                c.setForeground(new Color(255, 140, 0));
                c.setFont(c.getFont().deriveFont(Font.BOLD));

                return c;
            }
        };

        // Theo trang thai
        TableCellRenderer statusColumnRender = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value.equals("Đã thanh toán")) {
                    c.setForeground(new Color(40, 167, 69));
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                }

                if (value.equals("Đã hủy")) {
                    c.setForeground(new Color(255, 0, 0));
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                }

                if (value.equals("Chờ thanh toán")) {
                    c.setForeground(new Color(255, 140, 0));
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                }

                return c;
            }
        };

        // Mau do
        TableCellRenderer redColumnRender = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                c.setForeground(new Color(255, 0, 0));
                c.setFont(c.getFont().deriveFont(Font.BOLD));

                return c;
            }
        };
        
        tbDonHangChoXuLy.getColumnModel().getColumn(1).setCellRenderer(redColumnRender);
        tbDonHangChoXuLy.getColumnModel().getColumn(2).setCellRenderer(orangeColumnRender);
        tbDonHangChoXuLy.getColumnModel().getColumn(4).setCellRenderer(orangeColumnRender);
        tbLichSuDonHang.getColumnModel().getColumn(1).setCellRenderer(orangeColumnRender);
        tbLichSuDonHang.getColumnModel().getColumn(4).setCellRenderer(statusColumnRender);
        tbDonHangDaHoanThanh.getColumnModel().getColumn(1).setCellRenderer(orangeColumnRender);
        tbDonHangDaHoanThanh.getColumnModel().getColumn(4).setCellRenderer(statusColumnRender);
        
        txtTimKiem.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("\\giayhub\\Images\\bx-search.svg", 0.55f));
    }

    public void showDataTable(List<Dashboard> lists) {
        dtm.setRowCount(0);
        i = 1;
        for (Dashboard dashboard : lists) {
            dtm.addRow(new Object[]{
                i++,
                dashboard.getMaDonHang(),
                dashboard.getMaKhachHang(),
                dashboard.getNgayKhoiTao(),
                dashboard.getTrangThai()
            });
        }
        i = -1;
    }

    public void showDataTableLSDH(List<OrderHistory> lists) {
        dtmLichSuDonHang.setRowCount(0);
        x = 1;
        for (OrderHistory orderHistory : lists) {
            dtmLichSuDonHang.addRow(new Object[]{
                x++,
                orderHistory.getOrderID(),
                orderHistory.getFullName(),
                orderHistory.getOrderDate(),
                orderHistory.getStatus(),
                orderHistory.getProductName(),
                orderHistory.getQuantity(),
                orderHistory.getTotalPrice()
            });
        }
        x = -1;
    }

    public void showDataTableDonHoanThanh(List<Dashboard> lists) {
        dtmDonHoanThanh.setRowCount(0);
        y = 1;
        for (Dashboard dashboard : lists) {
            dtmDonHoanThanh.addRow(new Object[]{
                y++,
                dashboard.getMaDonHang(),
                dashboard.getMaKhachHang(),
                dashboard.getNgayKhoiTao(),
                dashboard.getTrangThai()
            });
        }
        y = -1;
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
        allBang = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDonHangChoXuLy = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbLichSuDonHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDonHangDaHoanThanh = new javax.swing.JTable();
        cbTimKiem = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        tongSoSanPham = new giayhub.Dashboard.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTongSoSanPham = new javax.swing.JLabel();
        tongSoDonHang = new giayhub.Dashboard.swing.RoundPanel();
        lblTongSoDonHang = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        donHangChoXuLy = new giayhub.Dashboard.swing.RoundPanel();
        lblDonDangXuLy = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        donHangDaHoanThanh = new giayhub.Dashboard.swing.RoundPanel();
        lblDonHoanThanh = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1101, 651));
        setRequestFocusEnabled(false);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tbDonHangChoXuLy.setFont(new java.awt.Font("Inter 24pt", 0, 12)); // NOI18N
        tbDonHangChoXuLy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã đơn hàng", "Mã khách hàng", "Ngày khởi tạo", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tbDonHangChoXuLy);

        allBang.addTab("Đơn hàng đang xử lý", jScrollPane1);

        tbLichSuDonHang.setFont(new java.awt.Font("Inter 24pt", 0, 12)); // NOI18N
        tbLichSuDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID Đơn Hàng", "Tên KH", "Ngày Tạo", "Trạng Thái", "Tên SP", "Số Lượng", "Tổng Tiền"
            }
        ));
        jScrollPane2.setViewportView(tbLichSuDonHang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        allBang.addTab("Lịch sử đơn hàng", jPanel1);

        tbDonHangDaHoanThanh.setFont(new java.awt.Font("Inter 24pt", 0, 12)); // NOI18N
        tbDonHangDaHoanThanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã đơn hàng", "Mã khách hàng", "Ngày khởi tạo", "Trạng thái"
            }
        ));
        jScrollPane3.setViewportView(tbDonHangDaHoanThanh);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        allBang.addTab("Đơn hàng đã hoàn thành", jPanel2);

        cbTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã đơn hàng", "Mã khách hàng" }));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        tongSoSanPham.setBackground(new java.awt.Color(66, 165, 245));
        tongSoSanPham.setToolTipText("");
        tongSoSanPham.setPreferredSize(new java.awt.Dimension(250, 222));

        jLabel2.setFont(new java.awt.Font("Inter 24pt", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tổng số sản phẩm");

        lblTongSoSanPham.setFont(new java.awt.Font("Inter 24pt", 1, 48)); // NOI18N
        lblTongSoSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblTongSoSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongSoSanPham.setText("00");

        javax.swing.GroupLayout tongSoSanPhamLayout = new javax.swing.GroupLayout(tongSoSanPham);
        tongSoSanPham.setLayout(tongSoSanPhamLayout);
        tongSoSanPhamLayout.setHorizontalGroup(
            tongSoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTongSoSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        tongSoSanPhamLayout.setVerticalGroup(
            tongSoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tongSoSanPhamLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addComponent(lblTongSoSanPham)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel5.add(tongSoSanPham);

        tongSoDonHang.setBackground(new java.awt.Color(255, 140, 0));

        lblTongSoDonHang.setFont(new java.awt.Font("Inter 24pt", 1, 48)); // NOI18N
        lblTongSoDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblTongSoDonHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongSoDonHang.setText("00");

        jLabel5.setFont(new java.awt.Font("Inter 24pt", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tổng số đơn hàng");

        javax.swing.GroupLayout tongSoDonHangLayout = new javax.swing.GroupLayout(tongSoDonHang);
        tongSoDonHang.setLayout(tongSoDonHangLayout);
        tongSoDonHangLayout.setHorizontalGroup(
            tongSoDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTongSoDonHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        tongSoDonHangLayout.setVerticalGroup(
            tongSoDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tongSoDonHangLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(44, 44, 44)
                .addComponent(lblTongSoDonHang)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel5.add(tongSoDonHang);

        donHangChoXuLy.setBackground(new java.awt.Color(255, 167, 38));
        donHangChoXuLy.setToolTipText("");

        lblDonDangXuLy.setFont(new java.awt.Font("Inter 24pt", 1, 48)); // NOI18N
        lblDonDangXuLy.setForeground(new java.awt.Color(255, 255, 255));
        lblDonDangXuLy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonDangXuLy.setText("00");

        jLabel7.setFont(new java.awt.Font("Inter 24pt", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Đơn đang xử lý");

        javax.swing.GroupLayout donHangChoXuLyLayout = new javax.swing.GroupLayout(donHangChoXuLy);
        donHangChoXuLy.setLayout(donHangChoXuLyLayout);
        donHangChoXuLyLayout.setHorizontalGroup(
            donHangChoXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDonDangXuLy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        donHangChoXuLyLayout.setVerticalGroup(
            donHangChoXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(donHangChoXuLyLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addGap(42, 42, 42)
                .addComponent(lblDonDangXuLy)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel5.add(donHangChoXuLy);

        donHangDaHoanThanh.setBackground(new java.awt.Color(46, 125, 50));
        donHangDaHoanThanh.setToolTipText("");

        lblDonHoanThanh.setFont(new java.awt.Font("Inter 24pt", 1, 48)); // NOI18N
        lblDonHoanThanh.setForeground(new java.awt.Color(255, 255, 255));
        lblDonHoanThanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonHoanThanh.setText("00");

        jLabel9.setFont(new java.awt.Font("Inter 24pt", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Đơn hoàn thành");

        javax.swing.GroupLayout donHangDaHoanThanhLayout = new javax.swing.GroupLayout(donHangDaHoanThanh);
        donHangDaHoanThanh.setLayout(donHangDaHoanThanhLayout);
        donHangDaHoanThanhLayout.setHorizontalGroup(
            donHangDaHoanThanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDonHoanThanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        donHangDaHoanThanhLayout.setVerticalGroup(
            donHangDaHoanThanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(donHangDaHoanThanhLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addGap(41, 41, 41)
                .addComponent(lblDonHoanThanh)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel5.add(donHangDaHoanThanh);

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Inter 24pt", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/giayhub/Images/refresh.png"))); // NOI18N
        jButton1.setText("Refresh");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(cbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allBang))
                .addGap(54, 54, 54))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(cbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(allBang, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // Tim kiem cua Form 0
        if (allBang.getSelectedIndex() == 0 && cbTimKiem.getSelectedIndex() == 0) {
            showDataTable(service.timKiemMaDonHangDangXuLy(txtTimKiem.getText()));
        }
        if (allBang.getSelectedIndex() == 0 && cbTimKiem.getSelectedIndex() == 1) {
            showDataTable(service.timKiemMaKhacHangDangXuLy(txtTimKiem.getText()));
        }
        // Tim kiem cua Form 1
        if (allBang.getSelectedIndex() == 1 && cbTimKiem.getSelectedIndex() == 0) {
            showDataTableLSDH(service.timKiemMaDonHangLichSu(txtTimKiem.getText()));
        }
        if (allBang.getSelectedIndex() == 1 && cbTimKiem.getSelectedIndex() == 1) {
            showDataTableLSDH(service.timKiemMaKhachHangLichSu(txtTimKiem.getText()));
        }
        // Tim kiem cua Form 2
        if (allBang.getSelectedIndex() == 2 && cbTimKiem.getSelectedIndex() == 0) {
            showDataTableDonHoanThanh(service.timKiemMaDonHangDaHoanThanh(txtTimKiem.getText()));
        }
        if (allBang.getSelectedIndex() == 2 && cbTimKiem.getSelectedIndex() == 1) {
            showDataTableDonHoanThanh(service.timKiemMaKhacHangDaHoanThanh(txtTimKiem.getText()));
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showDataTable(service.getAll());
        showDataTableLSDH(service.getAllLichSuDonHang());
        showDataTableDonHoanThanh(service.getAllDonHoanThanh());

        lblTongSoSanPham.setText(service.getTongSoSanPham() + "");
        lblTongSoDonHang.setText(service.getTongSoDonHang() + "");
        lblDonDangXuLy.setText(service.getDonDangXuLy() + "");
        lblDonHoanThanh.setText(service.getDonHangDaBan() + "");

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Dữ liệu đã được cập nhật lúc: " + now);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(new Color(255, 165, 0)); // Màu cam sáng
    }//GEN-LAST:event_jButton1MouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane allBang;
    private javax.swing.JComboBox<String> cbTimKiem;
    private giayhub.Dashboard.swing.RoundPanel donHangChoXuLy;
    private giayhub.Dashboard.swing.RoundPanel donHangDaHoanThanh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDonDangXuLy;
    private javax.swing.JLabel lblDonHoanThanh;
    private javax.swing.JLabel lblTongSoDonHang;
    private javax.swing.JLabel lblTongSoSanPham;
    private giayhub.Dashboard.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tbDonHangChoXuLy;
    private javax.swing.JTable tbDonHangDaHoanThanh;
    private javax.swing.JTable tbLichSuDonHang;
    private giayhub.Dashboard.swing.RoundPanel tongSoDonHang;
    private giayhub.Dashboard.swing.RoundPanel tongSoSanPham;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
