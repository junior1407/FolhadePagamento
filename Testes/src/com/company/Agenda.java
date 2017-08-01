package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Agenda {

    private HashMap<String, ArrayList<Paycheck>> payments;

    public Agenda(HashMap<String, ArrayList<Paycheck>> payments) {
        this.payments = payments;
    }

    public Agenda getCopy()
    {
        HashMap<String,ArrayList<Paycheck>> copy = new HashMap<>();
        for(Map.Entry<String, ArrayList<Paycheck>> entry: payments.entrySet())
        {
            ArrayList<Paycheck> curr_array = entry.getValue();
            ArrayList<Paycheck> copy_curr_array = new ArrayList<>();
            for(Paycheck s: curr_array)
            {
                copy_curr_array.add(s.getCopy());
            }
            copy.put(entry.getKey(),copy_curr_array);
        }
    return new Agenda(copy);
    }


    public Agenda() {

        payments = new HashMap<String, ArrayList<Paycheck>>();
    }

    public HashMap<String, ArrayList<Paycheck>> getPayments() {
        return payments;
    }

    public void setPayments(HashMap<String, ArrayList<Paycheck>> payments) {
        this.payments = payments;
    }

    public void addToAgenda(String date, Paycheck p) {
        if (payments.containsKey(date)) {
            payments.get(date).add(p);
        } else {
            payments.put(date, new ArrayList<Paycheck>());
            payments.get(date).add(p);
        }
    }

    public boolean wasPaid(String date, Employee e) {
        if (payments.containsKey(date)) {
            ArrayList<Paycheck> list = payments.get(date);

            for (Paycheck p : list) {
                if (p.getEmployee().getId() == e.getId()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

}
