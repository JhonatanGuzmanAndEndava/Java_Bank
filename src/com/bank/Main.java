package com.bank;

import com.customer.Customer;
import java.util.ArrayDeque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;


public class Main {


    public static void main(String[] args) {
        ArrayDeque<Customer> clients = new ArrayDeque<>();
        int numberOfCashiers = 5;
        int numberOfSupervisors = 3;
        int numberOfDirectors = 2;
        int numberOfClients = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfClients);
        Dispatcher objectsPool = new Dispatcher();

        for (int i = 0; i < numberOfCashiers; i++)
            objectsPool.addAgent(new Cashier(i));

        for (int j = 0; j < numberOfSupervisors; j++)
            objectsPool.addAgent(new Supervisor(j));

        for (int k = 0; k < numberOfDirectors; k++)
            objectsPool.addAgent(new Director(k));

        for (int cl = 0; cl < numberOfClients; cl++) {
            clients.addLast(new Customer());
            Supplier<Agent> supplier = objectsPool.attend();
            AgentSupplier agentSupplier = (AgentSupplier) supplier;
            agentSupplier.setClientId(cl);
            CompletableFuture.supplyAsync(supplier, threadPool).
                    thenAccept(agent -> {
                        System.out.println("it took " + agentSupplier.getSecondsToSleep() + " seconds to attend " +
                        "client " + agentSupplier.getClientId());
                        objectsPool.addAgent(agent);
                    });
        }

        threadPool.shutdown();
    }
}
