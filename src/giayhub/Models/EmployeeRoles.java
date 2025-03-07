/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class EmployeeRoles {
    private int employeeRoleID;
    private int employeeID;
    private int roleID;

    public EmployeeRoles() {
    }

    public EmployeeRoles(int employeeRoleID, int employeeID, int roleID) {
        this.employeeRoleID = employeeRoleID;
        this.employeeID = employeeID;
        this.roleID = roleID;
    }

    public int getEmployeeRoleID() {
        return employeeRoleID;
    }

    public void setEmployeeRoleID(int employeeRoleID) {
        this.employeeRoleID = employeeRoleID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "EmployeeRoles{" + "employeeRoleID=" + employeeRoleID + ", employeeID=" + employeeID + ", roleID=" + roleID + '}';
    }

}
