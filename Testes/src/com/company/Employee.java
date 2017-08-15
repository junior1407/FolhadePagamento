package com.company;

import java.util.Calendar;

/**
 * Created by alunoic on 28/07/17.
 */
public abstract class Employee <T extends Employee> {


    public static final int PAYMENT_FIRST_DAY_MONTH = 1;
    public static final int PAYMENT_SEVENTH_DAY_MONTH = 2;
    public static final int PAYMENT_LAST_DAY_MONTH = 3;
    public static final int PAYMENT_EVERY_MONDAY = 4;
    public static final int PAYMENT_EVERY_FRIDAY = 5;
    public static final int PAYMENT_EVERY_OTHER_MONDAY = 6;
    private int id;
    private int payment_day;
    private String name;
    private String address;
    private SindicateWorker sindicateCard;
    private String PaymentMethod;


    public Employee() {
    }

    public abstract Employee getCopy();
    public Employee(String name, String address, String paymentMethod, int id) {
        this.name = name;
        this.address = address;
        PaymentMethod = paymentMethod;
        sindicateCard = null;
        this.id = id;
    }

    public Employee(String name, String address, SindicateWorker sindicateCard, String paymentMethod, int id) {

        this.name = name;
        this.address = address;
        this.sindicateCard = sindicateCard;
        PaymentMethod = paymentMethod;
        this.id = id;
    }

    public int getPayment_day() {
        return payment_day;
    }


    //private MetodoPagamento metodo;

    public void setPayment_day(int payment_day) {
        this.payment_day = payment_day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SindicateWorker getSindicateCard() {
        return sindicateCard;
    }

    public void setSindicateCard(SindicateWorker sindicateCard) {
        this.sindicateCard = sindicateCard;
    }

    public String getPaymentMethod() {

        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }
    public boolean isPaidToday(Employee e, Calendar c)
    {


        int day = c.get(Calendar.DAY_OF_MONTH);


        int last_workday= Main.getLastWorkDay(c);

        if ((e.getPayment_day() == 1) && (day == 1)) {
            return true;
        }
        else if ((e.getPayment_day() == 2) && (day == 7)) {
            return true;
        }
        else if ((e.getPayment_day() == 3) && (day == last_workday)) {
            return true;
        }
        else if ((e.getPayment_day() == 4) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
            return true;

        } else if ((e.getPayment_day() == 5) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)) {
            return true;
        } else if ((e.getPayment_day() == 6) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
            return true; }

            else {
            return false;
        }}

    public int getFraction(Employee e, Calendar c) {
        int day = c.get(Calendar.DAY_OF_MONTH);
        int fraction = -1;

        int last_workday = Main.getLastWorkDay(c);


        if ((e.getPayment_day() == 1) && (day == 1)) {
            fraction = 1;
        } else if ((e.getPayment_day() == 2) && (day == 7)) {
            fraction = 1;
        } else if ((e.getPayment_day() == 3) && (day == last_workday)) {
            fraction = 1;
        } else if ((e.getPayment_day() == 4) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
            fraction = 4;

        } else if ((e.getPayment_day() == 5) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)) {
            fraction = 4;
        } else if ((e.getPayment_day() == 6) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
            fraction = 2;
        }
        return fraction;
    }
    public Calendar getRequiredDate(Employee e, Calendar c) {


        int day = c.get(Calendar.DAY_OF_MONTH);
        int last_workday = Main.getLastWorkDay(c);
        Calendar required_date = (Calendar) c.clone();

        if ((e.getPayment_day() == 1) && (day == 1)) {
            required_date.add(Calendar.MONTH, -1);
            required_date.set(Calendar.DAY_OF_MONTH, 1);
        } else if ((e.getPayment_day() == 2) && (day == 7)) {
            required_date.add(Calendar.MONTH, -1);
            required_date.set(Calendar.DAY_OF_MONTH, 7);
        } else if ((e.getPayment_day() == 3) && (day == last_workday)) {
            required_date.add(Calendar.MONTH, -1);
            required_date.set(Calendar.DAY_OF_MONTH, Main.getLastWorkDay(required_date));
        } else if ((e.getPayment_day() == 4) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
            required_date = Main.getLastXDay(c, Calendar.MONDAY);

        } else if ((e.getPayment_day() == 5) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)) {
            required_date = Main.getLastXDay(c, Calendar.FRIDAY);
        } else if ((e.getPayment_day() == 6) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
            Calendar lastMonday = Main.getLastXDay(c, Calendar.MONDAY);
            required_date = Main.getLastXDay(lastMonday, Calendar.MONDAY);
        }
        return required_date;
    }
    public abstract Paycheck getPaycheck(T e, Calendar c);

}
