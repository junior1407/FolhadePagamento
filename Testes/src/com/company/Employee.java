package com.company;

import java.util.ArrayList;
import java.util.SortedSet;

/**
 * Created by alunoic on 28/07/17.
 */
public abstract class Employee {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String address;
    private SindicateWorker sindicateCard;
    private String PaymentMethod;

    //private MetodoPagamento metodo;


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

    public Employee() {
    }

    public Employee(String name, String address, String paymentMethod, int id) {
        this.name = name;
        this.address = address;
        PaymentMethod = paymentMethod;
        sindicateCard=null;
        this.id=id;
    }

    public Employee(String name, String address, SindicateWorker sindicateCard, String paymentMethod, int id) {

        this.name = name;
        this.address = address;
        this.sindicateCard = sindicateCard;
        PaymentMethod = paymentMethod;
        this.id=id;
    }

    public String getPaymentMethod() {

        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }
}
