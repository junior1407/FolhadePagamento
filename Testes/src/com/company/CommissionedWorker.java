package com.company;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alunoic on 28/07/17.
 */
public class CommissionedWorker extends Employee<CommissionedWorker> {

    private float fixed_sallary;
    private float commission_percentage;
    private ArrayList<Sales> sales;

    public CommissionedWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, int id, float fixed_sallary, float commission_percentage, ArrayList<Sales> sales, int payment_day) {
        super(name, address, sindicateCard, paymentMethod, id);
        this.fixed_sallary = fixed_sallary;
        this.commission_percentage = commission_percentage;
        this.sales = sales;
        setPayment_day(payment_day);
    }

    public CommissionedWorker getCopy()
    {
        ArrayList<Sales> copy = new ArrayList<>();
        for(Sales s: sales)
        {
            copy.add(s.getCopy());
        }
        return new CommissionedWorker(getName(),getAddress(),getSindicateCard().getCopy(),
                getPaymentMethod(),getId(),getFixed_sallary(),getCommission_percentage(),copy,getPayment_day());

    }

    @Override
    public Paycheck getPaycheck(CommissionedWorker e, Calendar c) {
        if (!isPaidToday(e,c)) {
            return null;
        }
        int fraction = getFraction(e,c);
        Calendar required_date = e.getRequiredDate(e,c);
        Calendar yesterday = (Calendar) c.clone();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);

        float sallary= getSumSalesPeriodTime(required_date,yesterday);
        if (getSindicateCard()!=null)
        {
            sallary-=getSindicateCard().getSumTaxesPeriodTime(required_date,yesterday);
            sallary-= getSindicateCard().getFixed_tax()/fraction;
        }


        return new Paycheck(e, sallary);
    }


    public CommissionedWorker(String name, String address, String paymentMethod, float fixed_sallary, float commission_percentage, int id) {
        super(name, address, paymentMethod, id);
        this.fixed_sallary = fixed_sallary;
        this.commission_percentage = commission_percentage;
        this.sales = new ArrayList<Sales>();
        setPayment_day(Employee.PAYMENT_EVERY_OTHER_MONDAY);
    }


    public CommissionedWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, float fixed_sallary, float commission_percentage, int id) {
        super(name, address, sindicateCard, paymentMethod, id);
        this.fixed_sallary = fixed_sallary;
        this.commission_percentage = commission_percentage;
        this.sales = new ArrayList<Sales>();
        setPayment_day(Employee.PAYMENT_EVERY_OTHER_MONDAY);
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

    public void AddSale(Sales sale) {
        sales.add(sale);
    }


    public ArrayList<Sales> getSalesPeriodTime(Calendar start, Calendar end) {


        ArrayList<Sales> selectedSales = new ArrayList<Sales>();
        for (Sales curr : sales) {

            if (curr.happennedBetween(start, end)) {
                selectedSales.add(curr);
            }
        }

        return selectedSales;
        //return (ArrayList<CheckInOut>) cards.stream().filter(card -> card.happennedBetween(start, end)).collect(Collectors.toList());

    }
    public float getSumSalesPeriodTime(Calendar start, Calendar end)
    {
        float sum=0;
        ArrayList<Sales> sales = getSalesPeriodTime(start,end);
        for (Sales s : sales) {
            sum += s.getValue() * getCommission_percentage();
        }
        return sum;
    }


}
