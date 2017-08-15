package com.company;

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.stream.Collectors;

/**
 * Created by alunoic on 28/07/17.
 */
public class HourlyWorker extends Employee<HourlyWorker> {


    private float hour_sallary;

    private ArrayList<CheckInOut> cards;

    public HourlyWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, int id, float hour_sallary, ArrayList<CheckInOut> cards, int payment_day) {
        super(name, address, sindicateCard, paymentMethod, id);
        this.hour_sallary = hour_sallary;
        this.cards = cards;
        setPayment_day(getPayment_day());
    }

    public HourlyWorker getCopy()
    {
        ArrayList<CheckInOut> copy = new ArrayList<>();
        for(CheckInOut c: cards)
        {
            copy.add(c.getCopy());
        }

try {
    return new HourlyWorker(getName(), getAddress(), getSindicateCard().getCopy(), getPaymentMethod(), getId(), getHour_sallary(), copy, getPayment_day());
}
catch(NullPointerException e)
{
    return new HourlyWorker(getName(), getAddress(), null, getPaymentMethod(), getId(), getHour_sallary(), copy, getPayment_day());
}
        }

    @Override
    public Paycheck getPaycheck(HourlyWorker e, Calendar c) {

        if (!isPaidToday(e,c)) {
            return null;
        }
        int fraction = getFraction(e,c);
        Calendar required_date = e.getRequiredDate(e,c);
        Calendar yesterday = (Calendar) c.clone();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);

        float sallary= getSumCardsPeriodTime(required_date,yesterday);
        if (getSindicateCard()!=null)
        {
            sallary-=getSindicateCard().getSumTaxesPeriodTime(required_date,yesterday);
            sallary-= getSindicateCard().getFixed_tax()/fraction;
        }


        return new Paycheck(e, sallary);
    }


    public HourlyWorker(String name, String address, String paymentMethod, float hour_sallary, int id) {
        super(name, address, paymentMethod, id);
        this.hour_sallary = hour_sallary;
        cards = new ArrayList<CheckInOut>();
        setPayment_day(Employee.PAYMENT_EVERY_FRIDAY);
    }

    public HourlyWorker(String name, String address, SindicateWorker sindicateCard, String paymentMethod, float hour_sallary, int id) {
        super(name, address, sindicateCard, paymentMethod, id);
        this.hour_sallary = hour_sallary;
        setPayment_day(Employee.PAYMENT_EVERY_FRIDAY);
        cards = new ArrayList<CheckInOut>();
    }


    public float getSumCardsPeriodTime(Calendar start, Calendar end)
    {

        ArrayList<CheckInOut> checks = getCardsPeriodTime(start, end);
        float sum = 0;
        for (CheckInOut check : checks) {
            if (check.getWorkedHours() >= 8) {
               sum += 8 * getHour_sallary();
                sum += ((check.getWorkedHours() - 8) * getHour_sallary()) * 1.5;
            } else {
                sum += check.getWorkedHours() * getHour_sallary();
            }
        }
    return sum;
    }



    public ArrayList<CheckInOut> getCardsPeriodTime(Calendar start, Calendar end) {


        ArrayList<CheckInOut> selectedCards = new ArrayList<CheckInOut>();
        for (CheckInOut curr : cards) {

            if (curr.happennedBetween(start, end)) {
                selectedCards.add(curr);
            }
        }

        return selectedCards;
        //return (ArrayList<CheckInOut>) cards.stream().filter(card -> card.happennedBetween(start, end)).collect(Collectors.toList());

    }


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

    public void AddCard(CheckInOut card) {
        cards.add(card);
    }

}

