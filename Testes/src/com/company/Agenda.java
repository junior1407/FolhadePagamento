package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Agenda {

    private HashMap<String, ArrayList<Paycheck>> payments;

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
