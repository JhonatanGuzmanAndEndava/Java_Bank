package com.bank;

import com.customer.Customer;

import java.util.ArrayDeque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Dispatcher {

    private ArrayDeque<Cashier> cashiers = new ArrayDeque<>();
    private ArrayDeque<Supervisor> supervisors = new ArrayDeque<>();
    private ArrayDeque<Director> directors = new ArrayDeque<>();
    private ExecutorService threadsPool;

    /**
     * @param threadsNumber number of threads to run in the application
     */
    public Dispatcher(int threadsNumber){
        int numberOfCashiers = 5;
        int numberOfSupervisors = 3;
        int numberOfDirectors = 2;

        for (int i = 0; i < numberOfCashiers; i++)
            this.addAgent(new Cashier(i+1));

        for (int j = 0; j < numberOfSupervisors; j++)
            this.addAgent(new Supervisor(j+1));

        for (int k = 0; k < numberOfDirectors; k++)
            this.addAgent(new Director(k+1));

        this.threadsPool = Executors.newFixedThreadPool(threadsNumber);
    }

    /**
     * suhtdowns the threadsPool
     */
    public void shutdownExecution(){
        this.threadsPool.shutdown();
    }

    /**
     * Gets an Agent from the three diferent object pools (cashiers, supervisors, directors).
     * @return any object of Cashier, Supervisor or Director type.
     */
    private synchronized Agent provideAgent(){
        Agent agent = null;

        if (!this.cashiers.isEmpty())
            agent = this.cashiers.removeFirst();

        else if (!this.supervisors.isEmpty())
            agent = this.supervisors.removeFirst();

        else if (!this.directors.isEmpty())
            agent = this.directors.removeFirst();

        return agent;
    }


    /**
     * Address a thread an Agent type object to the customer given as argument.
     * @param customer
     */
    public void attend(Customer customer){
        Agent agent = provideAgent();
        boolean wasAnAgentRetrieved = agent != null;

        while (!wasAnAgentRetrieved) {
            agent = provideAgent();
            wasAnAgentRetrieved = agent != null;
        }

        AgentSupplier agentSupplier = new AgentSupplier(agent);
        agentSupplier.setClient(customer);
        CompletableFuture.supplyAsync(agentSupplier, threadsPool).
                thenAccept(agentFromFuture -> this.addAgent(agentFromFuture));
    }


    /**
     * Adds the agent given as argument to the corresponding object pool
     * @param agent
     */
    private void addAgent(Agent agent){

        if (agent instanceof Cashier)
            this.cashiers.addLast((Cashier) agent);

        else if (agent instanceof Supervisor)
            this.supervisors.addLast((Supervisor) agent);

        else if (agent instanceof Director)
            this.directors.addLast((Director) agent);
    }
}
