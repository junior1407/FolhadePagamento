package com.company;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alunoic on 28/07/17.
 */
public class    MonthlyWorker extends Employee<MonthlyWorker>  {

    private float sallary;

    public MonthlyWorker(String name, String address, String paymentMethod, float sallary, int id) {
        super(name, address, paymentMethod, id);
        this.sallary = sallary;
        setPayment_day(Employee.PAYMENT_LAST_DAY_MONTH);
    }

    public MonthlyWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, float sallary, int id) {
        super(name, address, sindicateCard, paymentMethod, id);
        this.sallary = sallary;
        setPayment_day(Employee.PAYMENT_LAST_DAY_MONTH);
    }

    public MonthlyWorker getCopy()
    {
        return new MonthlyWorker(getName(),getAddress(),getSindicateCard().getCopy(),getPaymentMethod(),getId(),getSallary(),getPayment_day());
    }

    @Override
    public Paycheck getPaycheck(MonthlyWorker e, Calendar c) {


        if (!isPaidToday(e,c)) {
        return null;
        }
        int fraction = getFraction(e,c);
        Calendar required_date = e.getRequiredDate(e,c);
        Calendar yesterday = (Calendar) c.clone();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);

        float sallary=getSallary()/fraction;
        if (getSindicateCard()!=null)
        {
            sallary-=getSindicateCard().getSumTaxesPeriodTime(required_date,yesterday);
            sallary-= getSindicateCard().getFixed_tax()/fraction;
        }


       return new Paycheck(e, sallary);

    }

    public MonthlyWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, int id, float sallary, int payment_day) {
        super(name, address, sindicateCard, paymentMethod, id);
        this.sallary = sallary;
        setPayment_day(payment_day);
    }

    public float getSallary() {
        return sallary;
    }

    public void setSallary(float sallary) {
        this.sallary = sallary;
    }
}




