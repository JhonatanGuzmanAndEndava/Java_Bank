package com.bank;

import com.customer.Customer;


public class Main {


    public static void main(String[] args) {
        int numberOfClients = 15;
        int numberOfThreads = 10;
        Dispatcher objectsPool = new Dispatcher(numberOfThreads);

        for (int i = 0; i < numberOfClients; i++) {
            int currentId = i+1;
            Customer customer = new Customer(currentId);
            objectsPool.attend(customer);
        }

        objectsPool.shutdownExecution();
    }
}
