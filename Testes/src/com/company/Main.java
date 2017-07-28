package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int employee_counter = 0;
        int sindicate_counter = 0;

        ArrayList<Employee> employeesList = new ArrayList<Employee>();


        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerFloat = new Scanner(System.in);
        int op = 0;

        do {
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
                            CheckInOut card = new CheckInOut(day, month, year, hour_init, min_init, hour_final, min_final);
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
                    int pos = -1;

                    //  Employee curr =  employeesList.stream().filter(employee -> employee.getId()==id ).collect(Collectors.toList()).get(0);
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
                            Sales sale = new Sales(day, month, year, value);
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
                            ServiceTaxes service = new ServiceTaxes(day, month, year, value);
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

                    ArrayList<Employee> teste = (ArrayList<Employee> ) employeesList.clone();
                    Collections.copy(teste,employeesList);
                    //O sistema deve achar todos os empregados que devem ser pagos no dia indicado
                    // , deve calcular o valor do salário e deve providenciar o pagamento de acordo com o método escolhido pelo empregado.
                    break;
                }
                case 8: {
                    break;
                }
                case 9: {
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
}

