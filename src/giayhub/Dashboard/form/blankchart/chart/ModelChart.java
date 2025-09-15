/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package giayhub.Dashboard.form.blankchart.chart;

/**
 *
 * @author phong
 */
public class ModelChart {

    String label;
    double values[];

    public double getMaxValues() {
        double max = 0;
        for (double value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public ModelChart() {
    }

    public ModelChart(String label, double[] values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

}
