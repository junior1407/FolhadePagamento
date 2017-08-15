package com.company;

import com.sun.corba.se.impl.naming.cosnaming.NamingUtils;

import java.util.*;

public class Main {

    public static String stringMessage= "Type a valid String!";
    public static String intMessage = "Type a valid Integer!";
    public static String floatMessage= "Type a valid float number!";
    public static void main(String[] args) {

        int employee_counter = 0;
        int sindicate_counter = 0;

        ArrayList<Employee> employeesList = new ArrayList<Employee>();
        Stack<Backup> undo = new Stack<>();
        Stack<Backup> redo = new Stack<>();

        Agenda agenda = new Agenda();

       // Scanner scannerInt = new Scanner(System.in);
    //   Scanner scannerString = new Scanner(System.in);
    //   Scanner scannerFloat = new Scanner(System.in);
        Processor proc = new Processor();
        int op = 0;

        do {
            System.out.println("01 - Insert Employee\n02 - Delete an Employee \n"
                    + "03 - Insert Check in and Check out\n04 - Insert a Sales Result\n"
                    + "05 - Insert a Sindicate Tax\n06 - Change an Employee's data\n"
                    + "07 - Generate Today's Paycheck\n08 - Undo/Redo\n09 - Payment Agenda\n"
                    + "10 - Create new Payment Agendas\n00 - Sair");
            System.out.println("Select the option you desire ");
               op = proc.getInteger(intMessage);

            switch (op) {
                case 1: {
                    undo.push(new Backup(employeesList,sindicate_counter,employee_counter));
                    System.out.println("Type your name");
                    String name =proc.getString(stringMessage,true);

                    System.out.println("Type your address");
                    String address =proc.getString(stringMessage,true);
                    System.out.println("Type your payment method");
                    String paymentMethod = proc.getString(stringMessage,true);
                    System.out.println("Type 1 - Monthly Worker\nType 2 - Hourly Worker\nType 3 - Commissioned Worker");
                    int type = proc.getInteger(intMessage);
                    System.out.println("Type 1 - If you participate in the Sindicate\nType 0 - If you DO NOT participate in the sindicate");
                    int sindicate = proc.getInteger(intMessage);
                    SindicateWorker sindicate_card = null;
                    int id_sindicate = -1;
                    int id_worker = employee_counter;
                    if (sindicate == 1) {
                        id_sindicate = sindicate_counter;
                        sindicate_counter++;
                        System.out.println("Type your contribbution to the sindicate");
                        float fixed_tax = proc.getFloat(floatMessage);
                        sindicate_card = new SindicateWorker(id_sindicate, fixed_tax);
                    }
                    if (type == 1) // Monthly
                    {
                        System.out.println("Type your sallary");
                        float sallary = proc.getFloat(floatMessage);
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
                        float sallary = proc.getFloat(floatMessage);

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
                        float sallary = proc.getFloat(floatMessage);
                        System.out.println("Type your commission percentage");
                        float commission_percentage = proc.getFloat(floatMessage);
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
                    undo.push(new Backup(employeesList,sindicate_counter,employee_counter));
                    System.out.println("Type the employee ID");
                    int id = proc.getInteger(intMessage);
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
                    undo.push(new Backup(employeesList,sindicate_counter,employee_counter));
                    System.out.println("Type the employee ID");
                    int id = proc.getInteger(intMessage);
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
                            int day = proc.getInteger(intMessage);
                            System.out.println("Type the month");
                            int month = proc.getInteger(intMessage);
                            System.out.println("Type the year");
                            int year = proc.getInteger(intMessage);
                            System.out.println("Type the hour");
                            int hour_init = proc.getInteger(intMessage);
                            System.out.println("Type the min");
                            int min_init = proc.getInteger(intMessage);
                            System.out.println("Type the hour_end");
                            int hour_final = proc.getInteger(intMessage);
                            System.out.println("Type the mind_end");
                            int min_final = proc.getInteger(intMessage);
                            CheckInOut card = new CheckInOut(day, month - 1, year, hour_init, hour_final,min_init, min_final);
                            curr.AddCard(card);
                            System.out.printf("%d:%d   %d:%d",card.getEntrada().get(Calendar.HOUR_OF_DAY), card.getEntrada().get(Calendar.MINUTE),card.getSaida().get(Calendar.HOUR_OF_DAY), card.getSaida().get(Calendar.MINUTE));
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
                    undo.push(new Backup(employeesList,sindicate_counter,employee_counter));
                    System.out.println("Type the employee ID");
                    int id = proc.getInteger(intMessage);
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
                            int day = proc.getInteger(intMessage);
                            System.out.println("Type the month");
                            int month = proc.getInteger(intMessage);
                            System.out.println("Type the year");
                            int year = proc.getInteger(intMessage);
                            System.out.println("Type the value");
                            float value = proc.getFloat(floatMessage);
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
                    undo.push(new Backup(employeesList,sindicate_counter,employee_counter));
                    System.out.println("Type the employee ID");
                    int id = proc.getInteger(intMessage);
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
                            int day = proc.getInteger(intMessage);
                            System.out.println("Type the month");
                            int month = proc.getInteger(intMessage);
                            System.out.println("Type the year");
                            int year = proc.getInteger(intMessage);
                            System.out.println("Type the value");
                            float value = proc.getFloat(floatMessage);
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
                    undo.push(new Backup(employeesList,sindicate_counter,employee_counter));
                    //Alterar detalhes de um empregado Nome, Endereco, Tipo, Metodo de Pagamento, Sindicato?, Sindicato_id, Sindicato_Valor

                    System.out.println("Type the employee ID");
                    int id = proc.getInteger(intMessage);
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
                        int change = proc.getInteger(intMessage);
                        switch (change) {
                            case 1: {
                                System.out.println("Type your new name");
                                String name = proc.getString(stringMessage,true);
                                curr.setName(name);
                                break;
                            }
                            case 2: {
                                System.out.println("Type the new Address");
                                String address = proc.getString(stringMessage,true);
                                curr.setAddress(address);
                                break;
                            }
                            case 3: {
                                System.out.println("What do you want to be?");
                                System.out.println("Type 1 - Monthly Worker\nType 2 - Hourly Worker\nType 3 - Commissioned Worker");
                                int type = proc.getInteger(intMessage);


                                if (type == 1) // Monthly
                                {

                                    if (!(curr instanceof MonthlyWorker)) {
                                        System.out.println("Type your sallary");
                                        float sallary = proc.getFloat(floatMessage);
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
                                        float sallary = proc.getFloat(floatMessage);

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
                                        float sallary = proc.getFloat(floatMessage);
                                        System.out.println("Type your commission percentage");
                                        float commission_percentage = proc.getFloat(floatMessage);
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
                                String pay = proc.getString(stringMessage,true);
                                curr.setPaymentMethod(pay);
                                break;
                            }
                            case 5: {
                                if (curr.getSindicateCard() == null) {
                                    System.out.println("Do you want to be a member of the sindicate?  1 - Yes, 0 - No");
                                    int yes = proc.getInteger(intMessage);
                                    if (yes == 1) {
                                        System.out.println("Type your contributtion");

                                        int id_sindicate = sindicate_counter;
                                        sindicate_counter++;
                                        System.out.println("Type your contribbution to the sindicate");
                                        float fixed_tax = proc.getFloat(floatMessage);
                                        SindicateWorker sindicate_card = new SindicateWorker(id_sindicate, fixed_tax);
                                        curr.setSindicateCard(sindicate_card);
                                        System.out.println("You have succesfuly joined the sindicate!");
                                    }
                                } else {
                                    System.out.println("Do you want to leave he sindicate?  1 - Yes, 0 - No");
                                    int yes = proc.getInteger(intMessage);
                                    if (yes == 1) {
                                        curr.setSindicateCard(null);
                                        System.out.println("You have succesfuly quit the sindicate!");
                                    }
                                }
                                break;
                            }
                            case 6: {
                                if (curr.getSindicateCard()==null) {
                                    System.out.println("You're not a sindicate member!");
                                }
                                else
                                {
                                    System.out.println("What would you like your new ID to be?");
                                    int new_id = proc.getInteger(intMessage);
                                    int found=0;
                                    for(Employee e: employeesList)
                                    {
                                        if (!(e.getSindicateCard() == null))
                                        {
                                            if (e.getSindicateCard().getId()==new_id)
                                            {
                                                found=1;
                                            }
                                        }
                                    }
                                    if (found==1)
                                    {
                                        System.out.println("This ID is already taken!");
                                    }
                                    else
                                    {
                                        curr.getSindicateCard().setId(new_id);
                                        System.out.println("Done!");
                                    }
                                }

                                break;
                            }
                            case 7: {
                                if (curr.getSindicateCard()==null) {
                                    System.out.println("You're not a sindicate member!");
                                }
                                else
                                {
                                    System.out.println("How much would you like to contribute?");
                                    int new_id = proc.getInteger(intMessage);
                                    int found=0;
                                    for(Employee e: employeesList)
                                    {
                                        if (!(e.getSindicateCard() == null))
                                        {
                                            if (e.getSindicateCard().getId()==new_id)
                                            {
                                                found=1;
                                            }
                                        }
                                    }
                                    if (found==1)
                                    {
                                        System.out.println("This ID is already taken!");
                                    }
                                    else
                                    {
                                        curr.getSindicateCard().setId(new_id);
                                        System.out.println("Done!");
                                    }
                                }

                                break;
                            }
                        }


                    } else {
                        System.out.println("The employee was not found!");
                    }
                    break;
                }
                case 7: {
                    undo.push(new Backup(employeesList,sindicate_counter,employee_counter));
                    System.out.println("Type the day");
                    int day = proc.getInteger(intMessage);
                    System.out.println("Type the month");
                    int month = proc.getInteger(intMessage);
                    System.out.println("Type the year");
                    int year = proc.getInteger(intMessage);
                    Calendar c = Calendar.getInstance();
                    c.set(year, month - 1, day);

                    Map<Integer, String> Agenda;
                    String s1=getDateString(c);
                    int last_workday = getLastWorkDay(c);
                    String s2=getDateString(c);
                    int last_day_month = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    String s3=getDateString(c);
                    int curr_weekday = c.get(Calendar.DAY_OF_WEEK);
                    String date = getDateString(c);
                    for (Employee e : employeesList) {
                        Paycheck p = e.getPaycheck(e, c);
                        if (p!=null)
                        {
                            agenda.addToAgenda(getDateString(c), e.getPaycheck(e,c));
                        }

                    }

                    ArrayList<Paycheck> list = agenda.getPayments().get(getDateString(c));
                    if (list != null) {
                        System.out.println("The following Employees will be paid");

                        for (Paycheck p : list) {
                            System.out.printf("Name: %s | Salary: %f", p.getEmployee().getName(), p.getMoney());
                        }
                    }

                    System.out.println();
                    break;

                }
                case 8: {

                    System.out.println("Do you want to UNDO(1) or REDO(2)?");
                    int choose = proc.getInteger(intMessage);
                    if (choose==1)
                    {
                        if (!undo.isEmpty())
                        {
                            Backup curr = new Backup(employeesList,sindicate_counter,employee_counter);
                            redo.add(curr);
                            Backup prev = undo.pop();
                            employeesList =  prev.getEmployees_list();
                            employee_counter = prev.getEmployeee_counter();
                            sindicate_counter = prev.getEmployeee_counter();
                            System.out.println("Done");
                        }
                        else
                        {
                            System.out.println("There aren't any options left");
                        }
                    }

                    if (choose==2)
                    {
                        if (!redo.isEmpty())
                        {
                            Backup now = new Backup(employeesList,sindicate_counter,employee_counter);
                            undo.add(now);
                            Backup next = redo.pop();
                            employeesList=next.getEmployees_list();
                            sindicate_counter= next.getSindicate_counter();
                            employee_counter=next.getEmployeee_counter();
                            System.out.println("Done");
                        }
                        else
                        {
                            System.out.println("There aren't any options left");
                        }
                    }
                    break;
                }
                case 9: {
                    undo.push(new Backup(employeesList,sindicate_counter,employee_counter));

                    System.out.println("What payment form do you want?");
                    System.out.printf("1 - First day of the monty\n2 - Seventh day of the month\n3- Last WorkDay of the Month\n4 - Every Monday\n5- Every Friday\n6- Every other Monday\n");
                    int newPaymentAgenda = proc.getInteger(intMessage);
                    System.out.println("Type the employee ID");
                    int id = proc.getInteger(intMessage);
                    int pos = -1;
                    for (int i = 0; i < employeesList.size(); i++) {
                        if (employeesList.get(i).getId() == id) {
                            pos = i;
                            break;
                        }
                    }
                    if (pos == -1) {
                        System.out.println("Employee not found");
                    }
                    else
                    {
                        employeesList.get(pos).setPayment_day(newPaymentAgenda);
                        System.out.println("Done!");
                    }
                    break;
                }
            }
        } while (op != 0);

    //*    scannerFloat.close();
    //    scannerInt.close();
   //     scannerString.close();

    }

    public static String getDateString(Calendar c) {
        return c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);
    }

    public static int getLastWorkDay(Calendar c) {
        Calendar d= (Calendar) c.clone();
        int last_day_month = d.getActualMaximum(Calendar.DATE);
        d.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), last_day_month);
        while (d.get(Calendar.DAY_OF_WEEK) > Calendar.FRIDAY || d.get(Calendar.DAY_OF_WEEK) <= Calendar.SUNDAY) {
            d.roll(Calendar.DATE, false);
        }
        return d.get(Calendar.DAY_OF_MONTH);
    }

    public static Calendar getLastXDay(Calendar c, int day_week) {
     Calendar d= (Calendar) c.clone();
        d.roll(Calendar.DAY_OF_YEAR, false);
        while (d.get(Calendar.DAY_OF_WEEK) != day_week) {
            d.roll(Calendar.DAY_OF_YEAR, false);
        }
        return d;

    }

    public static void PrintCalendar(Calendar c) {
        System.out.printf("%d/%d/%d\n", c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    }


}

