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

        try {
            System.out.println(attendingClientMsg());
            Thread.sleep(this.secondsToSleep * 1000);
            switch(this.client.getReason()) {
                case DEPOSIT:
                    client.setBalance(this.client.getBalance() + this.client.getMoneyForTransaction());
                    client.setMoneyForTransaction(0);
                    break;

                case WITHDRAWAL:
                    client.setBalance(this.client.getBalance() - this.client.getMoneyForTransaction() < 0 ?
                            this.client.getBalance() : this.client.getBalance() - this.client.getMoneyForTransaction());
                    client.setMoneyForTransaction(0);
                    break;

                case ISSUE:
                    //TODO
                    break;
            }
            System.out.println(attendedClientMsg());
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.agent;
    }

    /**
     * Assigns an int number from [10, 15] of seconds to put the thread to sleep.
     */
    private void setSecondsToSleep() {
        Random randomNumber = new Random();
        int seconds = randomNumber.nextInt(6) + 10;
        this.secondsToSleep = new Long(seconds);
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    private String attendingClientMsg() {
        return "The " + this.agent.getClass().getSimpleName() +
                " " + this.agent.getEmployeeID() + " has started to serve client " + this.client.getCustomerId() +
                " for a " + this.client.getReason();
    }

    private String attendedClientMsg() {
        return "It took " + this.secondsToSleep + " seconds to attend " +
                "client " + this.client.getCustomerId();
    }
}
