package com.bank;

public class Cashier extends Agent {

    public Cashier(int id){
        super(id);
    }

    @Override
    public void serve() {
        System.out.printf(" Attended by cashier %n", this.employeeID);
    }
}
