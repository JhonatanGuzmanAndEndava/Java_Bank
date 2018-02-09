package com.customer;

import java.util.Random;

public class Customer {

    private int customerId;
    private Reasons reason;
    private double balance;
    private double moneyForTransaction;

    public Customer(int id){
        this.customerId = id;

        switch(new Random().nextInt(3)) {
            case 0:
                reason = Reasons.DEPOSIT;
                break;
            case 1:
                reason = Reasons.WITHDRAWAL;
                break;
            case 2:
                reason = Reasons.ISSUE;
                break;
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public Reasons getReason() {
        return reason;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMoneyForTransaction() {
        return moneyForTransaction;
    }

    public void setMoneyForTransaction(double moneyForTransaction) {
        this.moneyForTransaction = moneyForTransaction;
    }
}
