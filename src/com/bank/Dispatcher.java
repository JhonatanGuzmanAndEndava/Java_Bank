package com.bank;

import java.util.ArrayDeque;


public final class Dispatcher {

    private ArrayDeque<Cashier> cashiers = new ArrayDeque<>();
    private ArrayDeque<Supervisor> supervisors = new ArrayDeque<>();
    private ArrayDeque<Director> directors = new ArrayDeque<>();


    public AgentSupplier attend(){
        AgentSupplier supplier = null;
        Agent agent = null;

        if (!this.cashiers.isEmpty())
            agent = this.cashiers.getFirst();

        else if (!this.supervisors.isEmpty())
            agent = this.supervisors.getFirst();

        else if (!this.directors.isEmpty())
            agent = this.directors.getFirst();

        supplier = (agent != null)? new AgentSupplier(agent) : supplier;

        return supplier;
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
