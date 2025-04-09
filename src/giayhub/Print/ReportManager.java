/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Print;

import giayhub.Print.Model.ParameterReportPayment;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author phong
 */
public class ReportManager {

    private static ReportManager instance;

    private JasperReport reportPay;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {

    }

    public void compileReport() throws JRException {
        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("/giayhub/Print/reportInvoices.jrxml"));
    }

    public void printReportPayment(ParameterReportPayment data) throws JRException {      
        Map para = new HashMap();
        para.put("maHoaDon", data.getMaHoaDon());
        para.put("khachHang", data.getKhachHang());
        para.put("thuNgan", data.getThuNgan());
        para.put("ngayTao", data.getNgayTao());
        para.put("tienKhachDua", data.getTienKhachDua());
        para.put("tongTien", data.getTongTien());
        para.put("phuongThucThanhToan", data.getPhuongThucThanhToan());
        para.put("tienThua", data.getTienThua());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);

        view(print);
    }

    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}
