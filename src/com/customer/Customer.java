package com.customer;


public class Customer {

    private int customerId;


    public Customer(int id){
        this.customerId = id;
    }


    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
