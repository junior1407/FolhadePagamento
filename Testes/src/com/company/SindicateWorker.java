package com.company;

import java.util.ArrayList;

/**
 * Created by alunoic on 28/07/17.
 */
public class SindicateWorker {

    private int id;
    private float fixed_tax;
    private ArrayList<ServiceTaxes> taxes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getFixed_tax() {
        return fixed_tax;
    }

    public void setFixed_tax(float fixed_tax) {
        this.fixed_tax = fixed_tax;
    }

    public ArrayList<ServiceTaxes> getTaxes() {
        return taxes;
    }

    public void setTaxes(ArrayList<ServiceTaxes> taxes) {
        this.taxes = taxes;
    }

    public SindicateWorker(int id, float fixed_tax) {

        this.id = id;
        this.fixed_tax = fixed_tax;
        taxes= new ArrayList<ServiceTaxes>();
    }

    public void AddService(ServiceTaxes curr)
    {
        taxes.add(curr);
    }
}
