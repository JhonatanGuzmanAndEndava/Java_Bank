package com.bank;

import java.util.Random;

public abstract class Agent {

    protected int employeeID;


    public int getEmployeeID() {
        return employeeID;
    }


    public abstract void serve();
}
