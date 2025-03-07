/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class Employees {
    private int employeeID ;
    private String fullName;
    private String email;
    private String phoneNumber;
    private int roleID;
    private String createdDate;

    public Employees() {
    }

    public Employees(int employeeID, String fullName, String email, String phoneNumber, int roleID, String createdDate) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roleID = roleID;
        this.createdDate = createdDate;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Employees{" + "employeeID=" + employeeID + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", roleID=" + roleID + ", createdDate=" + createdDate + '}';
    }

}
