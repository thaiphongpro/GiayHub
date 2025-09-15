/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package giayhub.Views;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import giayhub.DAO.CustomersDAO;
import giayhub.Dashboard.form.SellerManagement;
import giayhub.Models.Customers;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author phong
 */
public class DanhSachKhachHang extends javax.swing.JFrame {

    private CustomersDAO service = new CustomersDAO();
    private DefaultTableModel dtm = new DefaultTableModel();
    private List<Customers> lists = new ArrayList<>();
    private boolean isDarkMode;
    private SellerManagement invoiceForm; // dung de truyen du lieu tu DanhSachKhachHang -> InvoiceManagement

    int i = -1;

    public DanhSachKhachHang(SellerManagement invoiceForm) {
        initComponents();
        this.setLocationRelativeTo(null);
        txtMaKH.setEditable(true);

        dtm = (DefaultTableModel) tbKhachHang.getModel();
        showDataTable(service.getAllCustomers());

        this.invoiceForm = invoiceForm;
    }

    public void showDataTable(List<Customers> lists) {
        dtm.setRowCount(0);
        for (Customers customers : lists) {
            dtm.addRow(new Object[]{
                customers.getCustomerID(),
                customers.getFullName(),
                customers.getEmail(),
                customers.getPhoneNumber(),
                customers.getAddress()
            });
        }
        i = -1;
    }

    public void detailTable() {
        i = tbKhachHang.getSelectedRow();
        txtMaKH.setText(dtm.getValueAt(i, 0) + "");
        txtHoTen.setText(dtm.getValueAt(i, 1) + "");
        txtEmail.setText(dtm.getValueAt(i, 2) + "");
        txtSDT.setText(dtm.getValueAt(i, 3) + "");
        txtDiaChi.setText(dtm.getValueAt(i, 4) + "");
    }

    public void detailTable1(int index) {
        Customers customers = service.getAllCustomers().get(index);
        txtMaKH.setText(customers.getCustomerID() + "");
        txtHoTen.setText(customers.getFullName());
        txtEmail.setText(customers.getEmail());
        txtSDT.setText(customers.getPhoneNumber());
        txtDiaChi.setText(customers.getAddress());
    }

    public Customers getFormData() {
        try {
            return new Customers(
                    Integer.parseInt(txtMaKH.getText()),
                    txtHoTen.getText(),
                    txtEmail.getText(),
                    txtSDT.getText(),
                    txtDiaChi.getText(),
                    2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearData() {
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
    }

    public boolean validateForm() {
        if (txtMaKH.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ mã KH");
            return false;
        }
        if (txtHoTen.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ Họ Tên");
            return false;
        }
        if (txtEmail.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ Email");
            return false;
        }
        if (txtSDT.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ Số ĐT");
            return false;
        }
        if (txtDiaChi.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ Địa Chỉ");
            return false;
        }

        String maKH = txtMaKH.getText().trim();
        if (!maKH.matches("\\d+")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mã Khách Hàng phải là số");
            return false;
        }

        for (Customers customers : service.getAllCustomers()) {
            if (customers.getCustomerID() == Integer.parseInt(maKH)) {
                JOptionPane.showMessageDialog(this, "Không được nhập trùng Mã KH");
                return false;
            }
        }
        String email = txtEmail.getText().trim();
        for (Customers customers : service.getAllCustomers()) {
            if (customers.getEmail().equalsIgnoreCase(email)) {
                JOptionPane.showMessageDialog(this, "Không được nhập trùng Email");
                return false;
            }
        }
        String sdt = txtSDT.getText().trim();
        for (Customers customers : service.getAllCustomers()) {
            if (customers.getPhoneNumber().equalsIgnoreCase(sdt)) {
                JOptionPane.showMessageDialog(this, "Không được nhập trùng Số ĐT");
                return false;
            }
        }
        if (!txtHoTen.getText().matches("^[a-zA-Z\\\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa số và ký tự đặc biệt");
            return false;
        }
        if (!txtDiaChi.getText().matches("^[a-zA-Z0-9\\s,.-]+$")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa ký tự đặc biệt");
            return false;
        }
        if (!txtDiaChi.getText().matches("^[a-zA-Z0-9\\s,.-]+$")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa ký tự đặc biệt");
            return false;
        }
        return true;
    }

    public boolean validateForm1() {
        if (txtMaKH.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ mã KH");
            return false;
        }
        if (txtHoTen.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ Họ Tên");
            return false;
        }
        if (txtEmail.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ Email");
            return false;
        }
        if (txtSDT.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ Số ĐT");
            return false;
        }
        if (txtDiaChi.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ Địa Chỉ");
            return false;
        }
        String maKH = txtMaKH.getText().trim();
        if (!maKH.matches("\\d+")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mã Khách Hàng phải là số");
            return false;
        }

        if (!txtSDT.getText().trim().matches("^(0|\\+84)(3[2-9]|5[2689]|7[0-9]|8[1-9]|9[0-9])[0-9]{7}$")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Số điện thoại phải đúng định dạng (VD: 0323456789 hoặc +84323456789)");
            return false;
        }
        if (!txtEmail.getText().trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng nhập đúng định dạng Email");
            return false;
        }
        if (!txtHoTen.getText().matches("^[a-zA-Z\\\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa số và ký tự đặc biệt");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cbTimKiem = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        btnChon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Inter 24pt", 1, 24)); // NOI18N
        jLabel1.setText("Danh Sách Khách Hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Họ Tên:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã Khách Hàng:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Số ĐT:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Địa Chỉ:");

        btnThem.setBackground(new java.awt.Color(252, 153, 0));
        btnThem.setFont(new java.awt.Font("Inter 24pt", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/giayhub/Images/addUser.png"))); // NOI18N
        btnThem.setText("Thêm người dùng");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(252, 153, 0));
        btnSua.setFont(new java.awt.Font("Inter 24pt", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/giayhub/Images/edit.png"))); // NOI18N
        btnSua.setText("Sửa thông tin");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtHoTen))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiaChi))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thiết lập thông tin khách hàng", jPanel1);

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ Tên", "Email", "Số ĐT", "Địa Chỉ"
            }
        ));
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbKhachHang);

        jTabbedPane5.addTab("Thông tin khách hàng", jScrollPane2);

        jLabel7.setText("Tìm kiếm:");

        cbTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên Khách Hàng", "Số Điện Thoại" }));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnChon.setBackground(new java.awt.Color(252, 153, 0));
        btnChon.setFont(new java.awt.Font("Inter 24pt", 1, 14)); // NOI18N
        btnChon.setForeground(new java.awt.Color(255, 255, 255));
        btnChon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/giayhub/Images/down.png"))); // NOI18N
        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(284, 284, 284))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addComponent(jTabbedPane1)
                    .addComponent(jTabbedPane5))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        i = tbKhachHang.getSelectedRow();
        detailTable();
    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (validateForm()) {
            try {
                service.themKhachHang(getFormData());
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
            } finally {
                clearData();
                showDataTable(service.getAllCustomers());
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (validateForm1()) {
            try {
                service.suaKhachHang(getFormData());
                JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi sửa dữ liệu: " + e.getMessage());
            } finally {
                clearData();
                showDataTable(service.getAllCustomers());
            }
            i = -1;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        if (cbTimKiem.getSelectedIndex() == 0) {
            showDataTable(service.searchTen(txtTimKiem.getText()));
        }
        if (cbTimKiem.getSelectedIndex() == 1) {
            showDataTable(service.searchSDT(txtTimKiem.getText()));
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        detailTable1(0);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int confirmed = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            this.dispose(); // Đóng cửa sổ thay vì thoát chương trình
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        i = tbKhachHang.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu trong bảng");
            return;
        }
        String maKH = tbKhachHang.getValueAt(i, 0) + "";
        String sdt = tbKhachHang.getValueAt(i, 3) + "";
        String tenKH = tbKhachHang.getValueAt(i, 1) + "";

        invoiceForm.setKhachHang(maKH, sdt, tenKH);

        this.dispose();
    }//GEN-LAST:event_btnChonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatArcOrangeIJTheme()); // FlatLaf sáng

            UIManager.put("Button.arc", 20);
            UIManager.put("TextComponent.arc", 25);

            // 🌟 Màu nền tổng thể của JTable
            UIManager.put("Table.background", new Color(255, 235, 204)); // Màu cam nhạt
            UIManager.put("Table.foreground", Color.BLACK); // Màu chữ

            // 🌟 Màu nền khi chọn dòng
            UIManager.put("Table.selectionBackground", new Color(255, 153, 51)); // Màu cam đậm
            UIManager.put("Table.selectionForeground", Color.WHITE); // Chữ trắng khi chọn

            // 🌟 Màu tiêu đề cột (Header)
            UIManager.put("TableHeader.background", new Color(255, 140, 0)); // Màu cam đậm
            UIManager.put("TableHeader.foreground", Color.WHITE); // Màu chữ trắng
            UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 14));

            // 🌟 Độ cao của dòng
            UIManager.put("Table.rowHeight", 30);

            // 🌟 Hiện đường kẻ ngang
            UIManager.put("Table.showHorizontalLines", true);
            UIManager.put("Table.showVerticalLines", true);
            UIManager.put("Table.gridColor", new Color(255, 200, 100)); // Đường kẻ màu cam nhạt
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Chạy giao diện chính
//        SwingUtilities.invokeLater(() -> {
//            new DanhSachKhachHang().setVisible(true);
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
