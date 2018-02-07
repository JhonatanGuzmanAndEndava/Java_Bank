package com.bank;

import com.customer.Customer;

import java.util.Random;
import java.util.function.Supplier;


public class AgentSupplier implements Supplier<Agent> {

    private Agent agent;
    private Customer client;
    private long secondsToSleep;


    public AgentSupplier(Agent agent){
        this.agent = agent;
        this.setSecondsToSleep();
    }


    @Override
    public Agent get() {
        System.out.println("The " + this.agent.getClass().getSimpleName() +
                " " + this.agent.getEmployeeID() + " has started to serve client " + this.client.getCustomerId() +
                " for a " + this.client.getReason());

        try {
            Thread.sleep(this.secondsToSleep * 1000);
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("It took " + this.secondsToSleep + " seconds to attend " +
                "client " + this.client.getCustomerId());

        return this.agent;
    }


    private void setSecondsToSleep() {
        Random randomNumber = new Random();
        int seconds = randomNumber.nextInt(6) + 10;
        this.secondsToSleep = new Long(seconds);
    }


    public long getSecondsToSleep(){
        return this.secondsToSleep;
    }


    public Customer getClient() {
        return this.client;
    }


    public void setClient(Customer client) {
        this.client = client;
    }
}
