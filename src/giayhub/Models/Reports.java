/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class Reports {
    private int reportId;
    private String generatedDate;
    private int totalOrders;
    private int completedOrders;
    private int pendingOrders;
    private int processingOrders;
    private float totalSales;
    private String topProduct;
    private String bestEmployee;

    public Reports() {
    }

    public Reports(int reportId, String generatedDate, int totalOrders, int completedOrders, int pendingOrders, int processingOrders, float totalSales, String topProduct, String bestEmployee) {
        this.reportId = reportId;
        this.generatedDate = generatedDate;
        this.totalOrders = totalOrders;
        this.completedOrders = completedOrders;
        this.pendingOrders = pendingOrders;
        this.processingOrders = processingOrders;
        this.totalSales = totalSales;
        this.topProduct = topProduct;
        this.bestEmployee = bestEmployee;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(String generatedDate) {
        this.generatedDate = generatedDate;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders = completedOrders;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public int getProcessingOrders() {
        return processingOrders;
    }

    public void setProcessingOrders(int processingOrders) {
        this.processingOrders = processingOrders;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(float totalSales) {
        this.totalSales = totalSales;
    }

    public String getTopProduct() {
        return topProduct;
    }

    public void setTopProduct(String topProduct) {
        this.topProduct = topProduct;
    }

    public String getBestEmployee() {
        return bestEmployee;
    }

    public void setBestEmployee(String bestEmployee) {
        this.bestEmployee = bestEmployee;
    }

    @Override
    public String toString() {
        return "Reports{" + "reportId=" + reportId + ", generatedDate=" + generatedDate + ", totalOrders=" + totalOrders + ", completedOrders=" + completedOrders + ", pendingOrders=" + pendingOrders + ", processingOrders=" + processingOrders + ", totalSales=" + totalSales + ", topProduct=" + topProduct + ", bestEmployee=" + bestEmployee + '}';
    }
    
}
