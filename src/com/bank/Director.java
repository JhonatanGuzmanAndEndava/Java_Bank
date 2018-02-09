package com.bank;

public class Director extends Agent {

    public Director(int id){ super(id); }

    @Override
    public void serve() {
        System.out.printf(" Attended by director %n", this.employeeID);
    }
}
