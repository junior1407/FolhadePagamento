package com.company;

import java.util.Calendar;

/**
 * Created by alunoic on 28/07/17.
 */
public abstract class Employee {


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
}
