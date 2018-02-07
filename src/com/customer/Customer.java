package com.customer;


import java.util.Random;

public class Customer {

    private int customerId;
    private String reason;
    private String[] reasons = {"Deposit", "withdrawal", "issue"};


    public Customer(int id){
        this.customerId = id;
        Random randomInt = new Random();
        int numberOfReasons = reasons.length;
        this.reason = this.reasons[randomInt.nextInt(numberOfReasons)];
    }


    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public String getReason() {
        return reason;
    }


    public void setReason(String reason) {
        this.reason = reason;
    }
}
