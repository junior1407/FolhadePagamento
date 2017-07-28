package com.company;

import java.util.ArrayList;

/**
 * Created by alunoic on 28/07/17.
 */
public class HourlyWorker extends Employee {


    private float hour_sallary;

    private ArrayList<CheckInOut> cards;

    public ArrayList<CheckInOut> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CheckInOut> cards) {
        this.cards = cards;
    }



    public float getHour_sallary() {
        return hour_sallary;
    }

    public void setHour_sallary(float hour_sallary) {
        this.hour_sallary = hour_sallary;
    }


    public HourlyWorker(String name, String address, String paymentMethod, float hour_sallary,int id) {
        super(name, address, paymentMethod,id);
        this.hour_sallary = hour_sallary;
        cards=new ArrayList<CheckInOut>();

    }

    public HourlyWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, float hour_sallary, int id) {
        super(name, address, sindicateCard, paymentMethod,id);
        this.hour_sallary = hour_sallary;
        cards=new ArrayList<CheckInOut>();
    }
    public void AddCard(CheckInOut card)
    {
        cards.add(card);
    }

}

