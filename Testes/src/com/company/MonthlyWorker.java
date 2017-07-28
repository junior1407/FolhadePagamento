package com.company;

/**
 * Created by alunoic on 28/07/17.
 */
public class MonthlyWorker extends Employee {

    private float sallary;

    public float getSallary() {
        return sallary;
    }

    public void setSallary(float sallary) {
        this.sallary = sallary;
    }

    public MonthlyWorker(String name, String address, String paymentMethod, float sallary, int id) {
        super(name, address, paymentMethod,id);
        this.sallary = sallary;
    }

    public MonthlyWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, float sallary, int id) {
        super(name, address, sindicateCard, paymentMethod,id );
        this.sallary = sallary;
    }
}
