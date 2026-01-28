package com.service;


import com.models.Ticket;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.State;
import reactor.core.publisher.Mono;

public class TicketService {
    private static final String STATE_STORE_NAME = "kvstore";
    private static final String KEY = "ticket";

    public String createTicket(Ticket ticket) {
        DaprClient daprClient = new DaprClientBuilder().build();
        daprClient.saveState(STATE_STORE_NAME, KEY, ticket).block();
        return "Ticket Created -> " + ticket.toString();
    }

    public String deleteTicket() {
        DaprClient daprClient = new DaprClientBuilder().build();
        daprClient.deleteState(STATE_STORE_NAME, KEY).block();
        return "Ticket Deleted!";
    }

    public String getTicket() {
        DaprClient daprClient = new DaprClientBuilder().build();
        Mono<State<Ticket>> ticket = daprClient.getState(STATE_STORE_NAME, KEY, Ticket.class); 
        return "Ticket -> " + ticket.block().getValue();
    }
    
}
