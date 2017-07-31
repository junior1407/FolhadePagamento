package com.company;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alunoic on 28/07/17.
 */
public class SindicateWorker {

    private int id;
    private float fixed_tax;
    private ArrayList<ServiceTaxes> taxes;

    public SindicateWorker(int id, float fixed_tax) {

        this.id = id;
        this.fixed_tax = fixed_tax;
        taxes = new ArrayList<ServiceTaxes>();
    }

    public ArrayList<ServiceTaxes> getTaxesPeriodTime(Calendar start, Calendar end) {


        ArrayList<ServiceTaxes> selectedTaxes = new ArrayList<ServiceTaxes>();
        for (ServiceTaxes curr : selectedTaxes) {

            if (curr.happennedBetween(start, end)) {
                selectedTaxes.add(curr);
            }
        }

        return selectedTaxes;
        //return (ArrayList<CheckInOut>) cards.stream().filter(card -> card.happennedBetween(start, end)).collect(Collectors.toList());

    }
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

    public void AddService(ServiceTaxes curr) {
        taxes.add(curr);
    }
}
