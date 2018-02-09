package com.bank;

import com.customer.Customer;

import java.util.Random;


public class Main {


    public static void main(String[] args) {
        int numberOfClients = 15;
        int numberOfThreads = 10;
        Dispatcher objectsPool = new Dispatcher(numberOfThreads);

        for (int i = 0; i < numberOfClients; i++) {
            int currentId = i+1;
            Customer customer = new Customer(currentId);
            customer.setBalance(1.0 + (double) new Random().nextInt(10001));
            customer.setMoneyForTransaction(1.0 + (double) new Random().nextInt(1001));
            objectsPool.attend(customer);
        }

        objectsPool.shutdownExecution();
    }
}
