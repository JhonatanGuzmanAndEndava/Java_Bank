package com.bank;

public class Supervisor extends Agent {

    public Supervisor(int id){ super(id); }

    @Override
    public void serve() {
        System.out.printf(" Attended by Supervisor %n", this.employeeID);
    }
}
