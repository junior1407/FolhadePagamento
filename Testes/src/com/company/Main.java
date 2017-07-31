package com.company;

import com.sun.corba.se.impl.naming.cosnaming.NamingUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        int employee_counter = 0;
        int sindicate_counter = 0;

        ArrayList<Employee> employeesList = new ArrayList<Employee>();
        Agenda agenda = new Agenda();

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerFloat = new Scanner(System.in);
        int op = 0;

        do {
            System.out.println("01 - Insert Employee\n02 - Delete an Employee \n"
                    + "03 - Insert Check in and Check out\n04 - Insert a Sales Result\n"
                    + "05 - Insert a Sindicate Tax\n06 - Change an Employee's data\n"
                    + "07 - Generate Today's Paycheck\n08 - Undo/Redo\n09 - Payment Agenda\n"
                    + "10 - Create new Payment Agendas\n00 - Sair");
            System.out.println("Select the option you desire ");
            op = scannerInt.nextInt();
            switch (op) {
                case 1: {
                    System.out.println("Type your name");
                    String name = scannerString.nextLine();
                    System.out.println("Type your address");
                    String address = scannerString.nextLine();
                    System.out.println("Type your payment method");
                    String paymentMethod = scannerString.nextLine();
                    System.out.println("Type 1 - Monthly Worker\nType 2 - Hourly Worker\nType 3 - Commissioned Worker");
                    int type = scannerInt.nextInt();
                    System.out.println("Type 1 - If you participate in the Sindicate\nType 0 - If you DO NOT participate in the sindicate");
                    int sindicate = scannerInt.nextInt();
                    SindicateWorker sindicate_card = null;
                    int id_sindicate = -1;
                    int id_worker = employee_counter;
                    if (sindicate == 1) {
                        id_sindicate = sindicate_counter;
                        sindicate_counter++;
                        System.out.println("Type your contribbution to the sindicate");
                        float fixed_tax = scannerFloat.nextFloat();
                        sindicate_card = new SindicateWorker(id_sindicate, fixed_tax);
                    }
                    if (type == 1) // Monthly
                    {
                        System.out.println("Type your sallary");
                        float sallary = scannerFloat.nextFloat();
                        MonthlyWorker curr;
                        if (sindicate_card == null) {
                            curr = new MonthlyWorker(name, address, paymentMethod, sallary, id_worker);
                        } else {
                            curr = new MonthlyWorker(name, address, sindicate_card, paymentMethod, sallary, id_worker);
                        }
                        employeesList.add(curr);

                    }
                    if (type == 2) // Hourly
                    {
                        HourlyWorker curr;
                        System.out.println("Type your sallary/hour");
                        float sallary = scannerFloat.nextFloat();

                        if (sindicate_card == null) {
                            curr = new HourlyWorker(name, address, paymentMethod, sallary, id_worker);
                        } else {
                            curr = new HourlyWorker(name, address, sindicate_card, paymentMethod, sallary, id_worker);
                        }
                        employeesList.add(curr);


                    }
                    if (type == 3) // Commissioned
                    {
                        System.out.println("Type your sallary ");
                        float sallary = scannerFloat.nextFloat();
                        System.out.println("Type your commission percentage");
                        float commission_percentage = scannerFloat.nextFloat();
                        CommissionedWorker curr;
                        if (sindicate_card == null) {
                            curr = new CommissionedWorker(name, address, paymentMethod, sallary, commission_percentage, id_worker);

                        } else {
                            curr = new CommissionedWorker(name, address, sindicate_card, paymentMethod, sallary, commission_percentage, id_worker);
                        }
                        employeesList.add(curr);
                    }

                    employee_counter++;
                    System.out.println("Congratulations! You were registed with the id " + id_worker);
                    if (sindicate == 1) {
                        System.out.println("Your Sindicate ID is " + id_sindicate);
                    }
                    break;
                }
                case 2: {
                    System.out.println("Type the employee ID");
                    int id = scannerInt.nextInt();
                    int pos = -1;
                    for (int i = 0; i < employeesList.size(); i++) {
                        if (employeesList.get(i).getId() == id) {
                            pos = i;
                            break;
                        }
                    }

                    if (pos == -1) {
                        System.out.println("The employee was not found!");
                    } else {
                        employeesList.remove(pos);
                        System.out.println("The Employee was successfuly removed");
                    }
                    break;
                }

                case 3: {
                    System.out.println("Type the employee ID");
                    int id = scannerInt.nextInt();
                    int pos = -1;
                    for (int i = 0; i < employeesList.size(); i++) {
                        if (employeesList.get(i).getId() == id) {
                            pos = i;
                            break;
                        }
                    }

                    if (pos != -1) {
                        if (employeesList.get(pos) instanceof HourlyWorker) {

                            HourlyWorker curr = (HourlyWorker) employeesList.get(pos);
                            System.out.println("Type the day");
                            int day = scannerInt.nextInt();
                            System.out.println("Type the day");
                            int month = scannerInt.nextInt();
                            System.out.println("Type the day");
                            int year = scannerInt.nextInt();
                            System.out.println("Type the day");
                            int hour_init = scannerInt.nextInt();
                            System.out.println("Type the day");
                            int min_init = scannerInt.nextInt();
                            System.out.println("Type the day");
                            int hour_final = scannerInt.nextInt();
                            System.out.println("Type the day");
                            int min_final = scannerInt.nextInt();
                            CheckInOut card = new CheckInOut(day, month - 1, year, hour_init, min_init, hour_final, min_final);
                            curr.AddCard(card);
                            System.out.println("Check In/Out Registered Successfuly!!!");
                        } else {
                            System.out.println("The employee is not a Hourly Worker!");
                        }
                    } else {
                        System.out.println("The employee was not found!");
                    }

                    break;
                }
                case 4: {
                    System.out.println("Type the employee ID");
                    int id = scannerInt.nextInt();
                    //   employeesList.stream().filter(employee -> employee.getId()==id ).collect(Collectors.toList()).t.nextInt();
                    int pos = -1;

                    //  Employee curr =  get(0);
                    for (int i = 0; i < employeesList.size(); i++) {
                        if (employeesList.get(i).getId() == id) {
                            pos = i;
                            break;
                        }
                    }

                    if (pos != -1) {
                        if (employeesList.get(pos) instanceof CommissionedWorker) {
                            CommissionedWorker curr = (CommissionedWorker) employeesList.get(pos);
                            System.out.println("Type the day");
                            int day = scannerInt.nextInt();
                            System.out.println("Type the month");
                            int month = scannerInt.nextInt();
                            System.out.println("Type the year");
                            int year = scannerInt.nextInt();
                            System.out.println("Type the value");
                            float value = scannerInt.nextFloat();
                            Sales sale = new Sales(day, month - 1, year, value);
                            curr.AddSale(sale);
                            System.out.println("Sale added successfuly!");
                        } else {
                            System.out.println("The employee is not a Commissioned Worker!");
                        }
                    } else {
                        System.out.println("The employee was not found!");
                    }


                    break;
                }
                case 5: {
                    System.out.println("Type the employee ID");
                    int id = scannerInt.nextInt();
                    int pos = -1;

                    //  Employee curr =  employeesList.stream().filter(employee -> employee.getId()==id ).collect(Collectors.toList()).get(0);
                    for (int i = 0; i < employeesList.size(); i++) {
                        if (employeesList.get(i).getId() == id) {
                            pos = i;
                            break;
                        }
                    }
                    if (pos != -1) {
                        if (employeesList.get(pos).getSindicateCard() != null) {
                            Employee curr = employeesList.get(pos);
                            System.out.println("Type the day");
                            int day = scannerInt.nextInt();
                            System.out.println("Type the month");
                            int month = scannerInt.nextInt();
                            System.out.println("Type the year");
                            int year = scannerInt.nextInt();
                            System.out.println("Type the value");
                            float value = scannerInt.nextFloat();
                            ServiceTaxes service = new ServiceTaxes(day, month - 1, year, value);
                            curr.getSindicateCard().AddService(service);
                            System.out.println("Service Tax added successfuly!");
                        } else {
                            System.out.println("The employee is not a Member of the Sindicate!");
                        }
                    } else {
                        System.out.println("The employee was not found!");
                    }
                    break;
                }
                case 6: {
                    //Alterar detalhes de um empregado Nome, Endereco, Tipo, Metodo de Pagamento, Sindicato?, Sindicato_id, Sindicato_Valor

                    System.out.println("Type the employee ID");
                    int id = scannerInt.nextInt();
                    int pos = -1;

                    //  Employee curr =  employeesList.stream().filter(employee -> employee.getId()==id ).collect(Collectors.toList()).get(0);
                    for (int i = 0; i < employeesList.size(); i++) {
                        if (employeesList.get(i).getId() == id) {
                            pos = i;
                            break;
                        }
                    }
                    if (pos != -1) {
                        Employee curr = employeesList.get(pos);
                        System.out.println("What would you like to change about this Employee?");
                        System.out.println("1-Name\n2-Address\n3-Type of Employee\n4-Paymenth Method\n5-Change his membership in the sindicate\n"
                                + "6-Change his sindicate_id\n7-Change his sindicate_contribution");
                        int change = scannerInt.nextInt();
                        switch (change) {
                            case 1: {
                                System.out.println("Type your new name");
                                String name = scannerString.nextLine();
                                curr.setName(name);
                                break;
                            }
                            case 2: {
                                System.out.println("Type the new Address");
                                String address = scannerString.nextLine();
                                curr.setAddress(address);
                                break;
                            }
                            case 3: {
                                System.out.println("What do you want to be?");
                                System.out.println("Type 1 - Monthly Worker\nType 2 - Hourly Worker\nType 3 - Commissioned Worker");
                                int type = scannerInt.nextInt();


                                if (type == 1) // Monthly
                                {

                                    if (!(curr instanceof MonthlyWorker)) {
                                        System.out.println("Type your sallary");
                                        float sallary = scannerFloat.nextFloat();
                                        MonthlyWorker novo;
                                        if (curr.getSindicateCard() == null) {
                                            novo = new MonthlyWorker(curr.getName(), curr.getAddress(), curr.getPaymentMethod(), sallary, curr.getId());
                                        } else {
                                            novo = new MonthlyWorker(curr.getName(), curr.getAddress(), curr.getSindicateCard(), curr.getPaymentMethod(), sallary, curr.getId());
                                        }
                                        employeesList.add(novo);
                                        employeesList.remove(curr);
                                    } else {
                                        System.out.println("You alredy are a Monthly Worker");
                                    }
                                }
                                if (type == 2) // Hourly
                                {
                                    if (!(curr instanceof HourlyWorker)) {
                                        HourlyWorker novo;
                                        System.out.println("Type your sallary/hour");
                                        float sallary = scannerFloat.nextFloat();

                                        if (curr.getSindicateCard() == null) {
                                            novo = new HourlyWorker(curr.getName(), curr.getAddress(), curr.getPaymentMethod(), sallary, curr.getId());
                                        } else {
                                            novo = new HourlyWorker(curr.getName(), curr.getAddress(), curr.getSindicateCard(), curr.getPaymentMethod(), sallary, curr.getId());
                                        }
                                        employeesList.add(novo);
                                        employeesList.remove(curr);
                                    } else {
                                        System.out.println("You alredy are a Hourly Worker");
                                    }


                                }
                                if (type == 3) // Commissioned
                                {
                                    if (!(curr instanceof CommissionedWorker)) {
                                        System.out.println("Type your sallary ");
                                        float sallary = scannerFloat.nextFloat();
                                        System.out.println("Type your commission percentage");
                                        float commission_percentage = scannerFloat.nextFloat();
                                        CommissionedWorker novo;
                                        if (curr.getSindicateCard() == null) {
                                            novo = new CommissionedWorker(curr.getName(), curr.getAddress(), curr.getPaymentMethod(), sallary, commission_percentage, curr.getId());

                                        } else {
                                            novo = new CommissionedWorker(curr.getName(), curr.getAddress(), curr.getSindicateCard(), curr.getPaymentMethod(), sallary, commission_percentage, curr.getId());
                                        }
                                        employeesList.add(novo);
                                        employeesList.remove(curr);
                                    } else {
                                        System.out.println("You alredy are a Commissioned Worker");
                                    }
                                }


                                break;
                            }
                            case 4: {
                                System.out.println("Type the new Payment Method");
                                String pay = scannerString.nextLine();
                                curr.setPaymentMethod(pay);
                                break;
                            }
                            case 5: {
                                if (curr.getSindicateCard() == null) {
                                    System.out.println("Do you want to be a member of the sindicate?  1 - Yes, 0 - No");
                                    int yes = scannerInt.nextInt();
                                    if (yes == 1) {
                                        System.out.println("Type your contributtion");

                                        int id_sindicate = sindicate_counter;
                                        sindicate_counter++;
                                        System.out.println("Type your contribbution to the sindicate");
                                        float fixed_tax = scannerFloat.nextFloat();
                                        SindicateWorker sindicate_card = new SindicateWorker(id_sindicate, fixed_tax);
                                        curr.setSindicateCard(sindicate_card);
                                        System.out.println("You have succesfuly joined the sindicate!");
                                    }
                                } else {
                                    System.out.println("Do you want to leave he sindicate?  1 - Yes, 0 - No");
                                    int yes = scannerInt.nextInt();
                                    if (yes == 1) {
                                        curr.setSindicateCard(null);
                                        System.out.println("You have succesfuly quit the sindicate!");
                                    }
                                }
                                break;
                            }
                            case 6: {//change sindicate id
                                break;
                            }
                            case 7: {//change sindicate contribution
                                break;
                            }
                        }


                    } else {
                        System.out.println("The employee was not found!");
                    }
                    break;
                }
                case 7: {

                    System.out.println("Type the day");
                    int day = scannerInt.nextInt();
                    System.out.println("Type the month");
                    int month = scannerInt.nextInt();
                    System.out.println("Type the year");
                    int year = scannerInt.nextInt();
                    Calendar c = Calendar.getInstance();
                    c.set(year, month - 1, day);
                    Map<Integer, String> Agenda;
                    int last_workday = getLastWorkDay(c);
                    int last_day_month = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    int curr_weekday = c.get(Calendar.DAY_OF_WEEK);
                    String date = getDateString(c);
                    for (Employee e : employeesList) {


                        if ((e.getPayment_day() == 1) && (day == 1)) {

                            Calendar first_day_lastmonth = (Calendar) c.clone();
                            first_day_lastmonth.add(Calendar.MONTH, -1);
                            first_day_lastmonth.set(Calendar.DAY_OF_MONTH, 1);
                            Calendar yesterday = (Calendar) c.clone();
                            yesterday.add(Calendar.DAY_OF_MONTH, -1);


                            float sindicateTax=0;
                            if (e.getSindicateCard()!= null)
                            {
                                sindicateTax+= e.getSindicateCard().getFixed_tax();
                                ArrayList<ServiceTaxes> taxes_list= e.getSindicateCard().getTaxesPeriodTime(first_day_lastmonth,yesterday);
                                for(ServiceTaxes s: taxes_list)
                                {
                                    sindicateTax+=s.getValue();
                                }
                            }

                            if (e instanceof MonthlyWorker) {
                                Paycheck p = new Paycheck(e, ((MonthlyWorker) e).getSallary() - sindicateTax);
                                agenda.addToAgenda(date, p);
                            }
                            if (e instanceof HourlyWorker) {
                                ArrayList<CheckInOut> checks = ((HourlyWorker) e).getCardsPeriodTime(first_day_lastmonth, yesterday);
                                float salary_soFar = 0;
                                for (CheckInOut check : checks) {
                                    if (check.getWorkedHours() >= 8) {
                                        salary_soFar += 8 * ((HourlyWorker) e).getHour_sallary();
                                        salary_soFar += ((check.getWorkedHours() - 8) * ((HourlyWorker) e).getHour_sallary()) * 1.5;
                                    } else {
                                        salary_soFar += check.getWorkedHours() * ((HourlyWorker) e).getHour_sallary();
                                    }
                                }
                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);

                            }
                            if (e instanceof CommissionedWorker) {
                                CommissionedWorker curr = (CommissionedWorker) e;
                                float salary_soFar = 0;
                                salary_soFar += curr.getFixed_sallary();


                                ArrayList<Sales> sales = curr.getSalesPeriodTime(first_day_lastmonth, yesterday);
                                for (Sales s : sales) {
                                    salary_soFar += s.getValue() * curr.getCommission_percentage();
                                }

                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);
                            }


                        } else if ((e.getPayment_day() == 2) && (day == 7)) {


                            Calendar seventh_day_lastmonth = (Calendar) c.clone();
                            seventh_day_lastmonth.add(Calendar.MONTH, -1);
                            seventh_day_lastmonth.set(Calendar.DAY_OF_MONTH, 7);
                            Calendar yesterday = (Calendar) c.clone();
                            yesterday.add(Calendar.DAY_OF_MONTH, -1);

                            float sindicateTax=0;
                            if (e.getSindicateCard()!= null)
                            {
                                sindicateTax+= e.getSindicateCard().getFixed_tax();
                                ArrayList<ServiceTaxes> taxes_list= e.getSindicateCard().getTaxesPeriodTime(seventh_day_lastmonth,yesterday);
                                for(ServiceTaxes s: taxes_list)
                                {
                                    sindicateTax+=s.getValue();
                                }
                            }


                            if (e instanceof MonthlyWorker) {
                                Paycheck p = new Paycheck(e, ((MonthlyWorker) e).getSallary()-sindicateTax);
                                agenda.addToAgenda(date, p);
                            }
                            if (e instanceof HourlyWorker) {


                                ArrayList<CheckInOut> checks = ((HourlyWorker) e).getCardsPeriodTime(seventh_day_lastmonth, yesterday);
                                float salary_soFar = 0;
                                for (CheckInOut check : checks) {
                                    if (check.getWorkedHours() >= 8) {
                                        salary_soFar += 8 * ((HourlyWorker) e).getHour_sallary();
                                        salary_soFar += ((check.getWorkedHours() - 8) * ((HourlyWorker) e).getHour_sallary()) * 1.5;
                                    } else {
                                        salary_soFar += check.getWorkedHours() * ((HourlyWorker) e).getHour_sallary();
                                    }
                                }
                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);

                            }
                            if (e instanceof CommissionedWorker) {
                                CommissionedWorker curr = (CommissionedWorker) e;
                                float salary_soFar = 0;
                                salary_soFar += curr.getFixed_sallary();

                                ArrayList<Sales> sales = curr.getSalesPeriodTime(seventh_day_lastmonth, yesterday);
                                for (Sales s : sales) {
                                    salary_soFar += s.getValue() * curr.getCommission_percentage();
                                }

                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);
                            }


                        } else if ((e.getPayment_day() == 3) && (day == last_workday)) {


                            Calendar before_last_day_lastmonth = (Calendar) c.clone();
                            before_last_day_lastmonth.add(Calendar.MONTH, -1);
                            before_last_day_lastmonth.set(Calendar.DAY_OF_MONTH, getLastWorkDay(before_last_day_lastmonth));
                            Calendar yesterday = (Calendar) c.clone();
                            yesterday.add(Calendar.DAY_OF_MONTH, -1);
                            float sindicateTax=0;
                            if (e.getSindicateCard()!= null)
                            {
                                sindicateTax+= e.getSindicateCard().getFixed_tax();
                                ArrayList<ServiceTaxes> taxes_list= e.getSindicateCard().getTaxesPeriodTime(before_last_day_lastmonth,yesterday);
                                for(ServiceTaxes s: taxes_list)
                                {
                                    sindicateTax+=s.getValue();
                                }
                            }


                            if (e instanceof MonthlyWorker) {
                                Paycheck p = new Paycheck(e, ((MonthlyWorker) e).getSallary()-sindicateTax);
                                agenda.addToAgenda(date, p);
                            }
                            if (e instanceof HourlyWorker) {


                                ArrayList<CheckInOut> checks = ((HourlyWorker) e).getCardsPeriodTime(before_last_day_lastmonth, yesterday);
                                float salary_soFar = 0;
                                for (CheckInOut check : checks) {
                                    if (check.getWorkedHours() >= 8) {
                                        salary_soFar += 8 * ((HourlyWorker) e).getHour_sallary();
                                        salary_soFar += ((check.getWorkedHours() - 8) * ((HourlyWorker) e).getHour_sallary()) * 1.5;
                                    } else {
                                        salary_soFar += check.getWorkedHours() * ((HourlyWorker) e).getHour_sallary();
                                    }
                                }
                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);

                            }
                            if (e instanceof CommissionedWorker) {
                                CommissionedWorker curr = (CommissionedWorker) e;
                                float salary_soFar = 0;
                                salary_soFar += curr.getFixed_sallary();
                                ArrayList<Sales> sales = curr.getSalesPeriodTime(before_last_day_lastmonth, yesterday);
                                for (Sales s : sales) {
                                    salary_soFar += s.getValue() * curr.getCommission_percentage();
                                }

                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);
                            }


                        } else if ((e.getPayment_day() == 4) && (curr_weekday == Calendar.MONDAY)) {


                            Calendar last_monday = getLastXDay(c, Calendar.MONDAY);
                            Calendar yesterday = (Calendar) c.clone();
                            yesterday.add(Calendar.DAY_OF_MONTH, -1);



                            float sindicateTax=0;
                            if (e.getSindicateCard()!= null)
                            {
                                sindicateTax+= e.getSindicateCard().getFixed_tax()/4;
                                ArrayList<ServiceTaxes> taxes_list= e.getSindicateCard().getTaxesPeriodTime(last_monday,yesterday);
                                for(ServiceTaxes s: taxes_list)
                                {
                                    sindicateTax+=s.getValue();
                                }
                            }

                            if (e instanceof MonthlyWorker) {
                                Paycheck p = new Paycheck(e,( ((MonthlyWorker) e).getSallary() / 4)-sindicateTax);
                                agenda.addToAgenda(date, p);
                            }
                            if (e instanceof HourlyWorker) {


                                ArrayList<CheckInOut> checks = ((HourlyWorker) e).getCardsPeriodTime(last_monday, yesterday);
                                float salary_soFar = 0;
                                for (CheckInOut check : checks) {
                                    if (check.getWorkedHours() >= 8) {
                                        salary_soFar += 8 * ((HourlyWorker) e).getHour_sallary();
                                        salary_soFar += ((check.getWorkedHours() - 8) * ((HourlyWorker) e).getHour_sallary()) * 1.5;
                                    } else {
                                        salary_soFar += check.getWorkedHours() * ((HourlyWorker) e).getHour_sallary();
                                    }
                                }
                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);

                            }
                            if (e instanceof CommissionedWorker) {
                                CommissionedWorker curr = (CommissionedWorker) e;
                                float salary_soFar = 0;
                                salary_soFar += curr.getFixed_sallary() / 4;
                                ArrayList<Sales> sales = curr.getSalesPeriodTime(last_monday, yesterday);
                                for (Sales s : sales) {
                                    salary_soFar += s.getValue() * curr.getCommission_percentage();
                                }

                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);
                            }


                        } else if ((e.getPayment_day() == 5) && (curr_weekday == Calendar.FRIDAY)) {


                            Calendar last_monday = getLastXDay(c, Calendar.FRIDAY);
                            Calendar yesterday = (Calendar) c.clone();
                            yesterday.add(Calendar.DAY_OF_MONTH, -1);



                            float sindicateTax=0;
                            if (e.getSindicateCard()!= null)
                            {
                                sindicateTax+= e.getSindicateCard().getFixed_tax()/4;
                                ArrayList<ServiceTaxes> taxes_list= e.getSindicateCard().getTaxesPeriodTime(last_monday,yesterday);
                                for(ServiceTaxes s: taxes_list)
                                {
                                    sindicateTax+=s.getValue();
                                }
                            }

                            if (e instanceof MonthlyWorker) {
                                Paycheck p = new Paycheck(e, (((MonthlyWorker) e).getSallary() / 4)-sindicateTax);
                                agenda.addToAgenda(date, p);
                            }
                            if (e instanceof HourlyWorker) {

                                ArrayList<CheckInOut> checks = ((HourlyWorker) e).getCardsPeriodTime(last_monday, yesterday);
                                float salary_soFar = 0;
                                for (CheckInOut check : checks) {
                                    if (check.getWorkedHours() >= 8) {
                                        salary_soFar += 8 * ((HourlyWorker) e).getHour_sallary();
                                        salary_soFar += ((check.getWorkedHours() - 8) * ((HourlyWorker) e).getHour_sallary()) * 1.5;
                                    } else {
                                        salary_soFar += check.getWorkedHours() * ((HourlyWorker) e).getHour_sallary();
                                    }
                                }
                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);

                            }
                            if (e instanceof CommissionedWorker) {
                                CommissionedWorker curr = (CommissionedWorker) e;
                                float salary_soFar = 0;
                                salary_soFar += curr.getFixed_sallary() / 4;


                                ArrayList<Sales> sales = curr.getSalesPeriodTime(last_monday, yesterday);
                                for (Sales s : sales) {
                                    salary_soFar += s.getValue() * curr.getCommission_percentage();
                                }

                                Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                agenda.addToAgenda(getDateString(c), p);
                            }


                        } else if ((e.getPayment_day() == 6) && (curr_weekday == Calendar.MONDAY)) {

                            Calendar lastMonday = getLastXDay(c, Calendar.MONDAY);
                            String previous_monday = getDateString(lastMonday);
                            Calendar last__last_monday = getLastXDay(lastMonday, Calendar.MONDAY);
                            Calendar yesterday = (Calendar) c.clone();
                            yesterday.add(Calendar.DAY_OF_MONTH, -1);

                            float sindicateTax=0;
                            if (e.getSindicateCard()!= null)
                            {
                                sindicateTax+= e.getSindicateCard().getFixed_tax()/2;
                                ArrayList<ServiceTaxes> taxes_list= e.getSindicateCard().getTaxesPeriodTime(last__last_monday,yesterday);
                                for(ServiceTaxes s: taxes_list)
                                {
                                    sindicateTax+=s.getValue();
                                }
                            }


                            if (!agenda.wasPaid(date, e)) {
                                if (e instanceof MonthlyWorker) {
                                    Paycheck p = new Paycheck(e, (((MonthlyWorker) e).getSallary() / 2)-sindicateTax);
                                    agenda.addToAgenda(date, p);
                                }
                                if (e instanceof HourlyWorker) {


                                    ArrayList<CheckInOut> checks = ((HourlyWorker) e).getCardsPeriodTime(last__last_monday, yesterday);
                                    float salary_soFar = 0;
                                    for (CheckInOut check : checks) {
                                        if (check.getWorkedHours() >= 8) {
                                            salary_soFar += 8 * ((HourlyWorker) e).getHour_sallary();
                                            salary_soFar += ((check.getWorkedHours() - 8) * ((HourlyWorker) e).getHour_sallary()) * 1.5;
                                        } else {
                                            salary_soFar += check.getWorkedHours() * ((HourlyWorker) e).getHour_sallary();
                                        }
                                    }
                                    Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                    agenda.addToAgenda(getDateString(c), p);

                                }
                                if (e instanceof CommissionedWorker) {
                                    CommissionedWorker curr = (CommissionedWorker) e;
                                    float salary_soFar = 0;
                                    salary_soFar += curr.getFixed_sallary() / 2;




                                    ArrayList<Sales> sales = curr.getSalesPeriodTime(last__last_monday, yesterday);
                                    for (Sales s : sales) {
                                        salary_soFar += s.getValue() * curr.getCommission_percentage();
                                    }

                                    Paycheck p = new Paycheck(e, salary_soFar-sindicateTax);
                                    agenda.addToAgenda(getDateString(c), p);
                                }
                            }
                        }
                    }


                    System.out.println("The following Employees will be paid");
                    ArrayList<Paycheck> list = agenda.getPayments().get(getDateString(c));
                    for (Paycheck p : list) {
                        System.out.printf("Name: %s | Salary: %f", p.getEmployee().getName(), p.getMoney());
                    }
                    System.out.println();
                    break;

                }
                case 8: {
                    break;
                }
                case 9: {
                    //Change someone's payment Agenda
                    break;
                }
                case 10: {
                    break;
                }

            }


        } while (op != 0);

        scannerFloat.close();
        scannerInt.close();
        scannerString.close();

    }

    public static String getDateString(Calendar c) {
        return c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);
    }

    public static int getLastWorkDay(Calendar c) {
        int last_day_month = c.getActualMaximum(Calendar.DATE);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), last_day_month);
        while (c.get(Calendar.DAY_OF_WEEK) > Calendar.FRIDAY || c.get(Calendar.DAY_OF_WEEK) <= Calendar.SUNDAY) {
            c.roll(Calendar.DATE, false);
        }
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static Calendar getLastXDay(Calendar c, int day_week) {
        c.roll(Calendar.DAY_OF_YEAR, false);
        while (c.get(Calendar.DAY_OF_WEEK) != day_week) {
            c.roll(Calendar.DAY_OF_YEAR, false);
        }
        return c;

    }

    public static void PrintCalendar(Calendar c) {
        System.out.printf("%d/%d/%d\n", c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    }


}

