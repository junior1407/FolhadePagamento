package com.company;

public class Paycheck {

    private Employee employee;
    private float money;

    public Paycheck getCopy()
    {
        return new Paycheck(employee.getCopy(), money);
    }

    public Paycheck(Employee employee, float money) {

        this.employee = employee;
        this.money = money;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
