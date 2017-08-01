package com.company;

import java.util.ArrayList;

public class Backup {
    private ArrayList<Employee> employees_list;
    private int sindicate_counter;
    private int employeee_counter;

    public ArrayList<Employee> getEmployees_list() {
        return employees_list;
    }

    public void setEmployees_list(ArrayList<Employee> employees_list) {
        this.employees_list = employees_list;
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> new_one= new ArrayList<>();
        for(Employee e: employees_list)
        {
            new_one.add( e.getCopy());
        }


        return new_one;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees_list = employees;
    }

    public int getSindicate_counter() {
        return sindicate_counter;
    }

    public void setSindicate_counter(int sindicate_counter) {
        this.sindicate_counter = sindicate_counter;
    }

    public int getEmployeee_counter() {
        return employeee_counter;
    }

    public void setEmployeee_counter(int employeee_counter) {
        this.employeee_counter = employeee_counter;
    }


    public Backup(ArrayList<Employee> employees, int sindicate_counter, int employeee_counter) {
        employees_list=new ArrayList<>();
        for(Employee e: employees)
        {
          employees_list.add( e.getCopy());
    }

        this.sindicate_counter = sindicate_counter;
        this.employeee_counter = employeee_counter;
    }
}
