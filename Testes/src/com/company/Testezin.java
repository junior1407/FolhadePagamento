package com.company;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alunoic on 28/07/17.
 */
public class Testezin {

    public static void main(String[] args) {


        Calendar c = Calendar.getInstance();
        c.set(2017, Calendar.MARCH, 1, 15, 59);
        c.add(Calendar.DAY_OF_MONTH, -1);
        //   Calendar first_day_lastmonth= (Calendar) c.clone();
        //  first_day_lastmonth.add(Calendar.MONTH,-1);
        //   first_day_lastmonth.set(Calendar.DAY_OF_MONTH,1);
        Main.PrintCalendar(c);
        //      Main.PrintCalendar(first_day_lastmonth);

        // Calendar d = Calendar.getInstance();
        //d.set(1900,Calendar.JANUARY,10,16,30);
        //CheckInOut f = new CheckInOut(c,d);
        //f.getWorkedHours();
        //JOptionPane.showMessageDialog(null,""+  f.getWorkedHours());

        //  int last_day_month = c.getActualMaximum(Calendar.DATE);
        //System.out.println("Ultimo dia do mes e: " +last_day_month);
        // c.set(2017,c.get(Calendar.MONTH),last_day_month);

        //  System.out.println(Main.getLastWorkDay(c));
        // System.out.println("O mes teve "+last_day_month+" e o ultimo dia foi "+name_last_day);


        c.roll(Calendar.DAY_OF_YEAR, false);
        //  c.add(Calendar.Da, -1);
        //c.add(Calendar.DAY_OF_YEAR);
        Main.PrintCalendar(c);

/*
        HourlyWorker h = new HourlyWorker("v", "v", "v", 1, 1);
        CheckInOut c1 = new CheckInOut(14, 06, 4, 8, 6, 7, 8);
        CheckInOut c2 = new CheckInOut(15, 06, 4, 8, 6, 7, 8);
        CheckInOut c3 = new CheckInOut(16, 06, 4, 8, 6, 7, 8);
        CheckInOut c4 = new CheckInOut(17, 06, 4, 8, 6, 7, 8);
        System.out.println(c1.getEntrada().get(Calendar.MONTH));

        h.AddCard(c1);
        h.AddCard(c2);
        h.AddCard(c3);
        h.AddCard(c4);
        Calendar c = Calendar.getInstance();
        c.set(4, Calendar.JULY, 14);
        Calendar d = Calendar.getInstance();
        d.set(4, Calendar.JULY, 16);

        ArrayList<CheckInOut> a = h.getCardsPeriodTime(c, d);
        for (CheckInOut curr : a) {
            Main.PrintCalendar(curr.getEntrada());
        }*/
    }


        /*

        ArrayList<Employee> lista1 = new ArrayList<Employee>();
        lista1.add(new MonthlyWorker ("a","a","a",0,0) );



        ArrayList<Employee>lista2= new ArrayList<Employee>(lista1);


        lista1.get(0).setName("J");
        System.out.println(lista1.get(0).getName());
        System.out.println(lista2.get(0).getName());
  */

}



