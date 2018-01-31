package com.bank;

import java.util.Random;
import java.util.function.Supplier;


public class AgentSupplier implements Supplier<Agent> {

    private Agent agent;
    private int clientId;
    private long secondsToSleep;


    public AgentSupplier(Agent agent){
        this.agent = agent;
        this.setSecondsToSleep();
    }


    @Override
    public Agent get() {
        System.out.println("started to serve client " + clientId);
        try {
            Thread.sleep(this.secondsToSleep * 1000);
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }

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


    public int getClientId() {
        return clientId;
    }


    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
