package com.customer;


import java.util.Random;

public class Customer {

    private int customerId;
    private String reason;
    private final String[] REASONS = {"deposit", "withdrawal", "issue"};


    public Customer(int id){
        this.customerId = id;
        Random randomInt = new Random();
        int numberOfReasons = REASONS.length;
        this.reason = this.REASONS[randomInt.nextInt(numberOfReasons)];
    }


    public int getCustomerId() {
        return customerId;
    }


    public String getReason() {
        return reason;
    }
}
