package com.company;

import java.util.ArrayList;

/**
 *
 * Created by alunoic on 28/07/17.
 */
public class CommissionedWorker extends Employee {

    private float fixed_sallary;
    private float commission_percentage;
    private ArrayList<Sales> sales;

    public CommissionedWorker(String name, String address, String paymentMethod, float fixed_sallary, float commission_percentage, int id) {
        super(name, address, paymentMethod, id);
        this.fixed_sallary = fixed_sallary;
        this.commission_percentage = commission_percentage;
        this.sales=new ArrayList<Sales>();
    }

    public CommissionedWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, float fixed_sallary, float commission_percentage, int id) {
        super(name, address, sindicateCard, paymentMethod, id);
        this.fixed_sallary = fixed_sallary;
        this.commission_percentage = commission_percentage;
        this.sales=new ArrayList<Sales>();
    }

    public float getFixed_sallary() {
        return fixed_sallary;
    }

    public void setFixed_sallary(float fixed_sallary) {
        this.fixed_sallary = fixed_sallary;
    }

    public float getCommission_percentage() {
        return commission_percentage;
    }

    public void setCommission_percentage(float commission_percentage) {
        this.commission_percentage = commission_percentage;
    }

    public ArrayList<Sales> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sales> sales) {
        this.sales = sales;
    }

    public void AddSale(Sales sale)
    {
        sales.add(sale);
    }
}
