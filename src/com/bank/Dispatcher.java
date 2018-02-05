package com.bank;

import java.util.ArrayDeque;


public final class Dispatcher {

    private ArrayDeque<Cashier> cashiers = new ArrayDeque<>();
    private ArrayDeque<Supervisor> supervisors = new ArrayDeque<>();
    private ArrayDeque<Director> directors = new ArrayDeque<>();


    private Agent provideAgent(){
        Agent agent = null;

        if (!this.cashiers.isEmpty())
            agent = this.cashiers.removeFirst();

        else if (!this.supervisors.isEmpty())
            agent = this.supervisors.removeFirst();

        else if (!this.directors.isEmpty())
            agent = this.directors.removeFirst();

        return agent;
    }


    public AgentSupplier attend(){
        Agent agent = provideAgent();
        boolean wasAnAgentRetrieved = agent != null;

        while (!wasAnAgentRetrieved) {
            System.out.print("");
            agent = provideAgent();
            wasAnAgentRetrieved = agent != null;
        }

        return new AgentSupplier(agent);
    }


    public void addAgent(Agent agent){

        if (agent instanceof Cashier)
            this.cashiers.addLast((Cashier) agent);

        else if (agent instanceof Supervisor)
            this.supervisors.addLast((Supervisor) agent);

        else if (agent instanceof Director)
            this.directors.addLast((Director) agent);
    }
}
