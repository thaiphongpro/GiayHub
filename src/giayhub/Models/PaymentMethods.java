/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Models;

/**
 *
 * @author phong
 */
public class PaymentMethods {
    private int paymentMethodID;
    private String methodName;

    public PaymentMethods() {
    }

    public PaymentMethods(int paymentMethodID, String methodName) {
        this.paymentMethodID = paymentMethodID;
        this.methodName = methodName;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "PaymentMethods{" + "paymentMethodID=" + paymentMethodID + ", methodName=" + methodName + '}';
    }

}
