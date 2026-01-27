package com.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.models.Ticket;
import com.service.TicketService;
import com.service.WorkflowService;

import org.springframework.http.MediaType;

@RestController
public class Controller {
    private TicketService ticketService = new TicketService();
    private WorkflowService workflowService = new WorkflowService();
    @GetMapping(path = "/test")
    public String test() {
        return "Dapr Test";
    }

    @PostMapping(path = "/ticket", consumes = MediaType.ALL_VALUE)
    public String createTicket(@RequestBody Ticket ticket) {
        // DaprClient daprClient = new DaprClientBuilder().build();
        // daprClient.saveState(STATE_STORE_NAME, KEY, ticket).block();
        // return "Ticket Created -> " + ticket.toString();
        return ticketService.createTicket(ticket);
    }

    @DeleteMapping(path = "/ticket")
    public String deleteTicket() {
        // DaprClient daprClient = new DaprClientBuilder().build();
        // daprClient.deleteState(STATE_STORE_NAME, KEY).block();
        // return "Ticket Deleted!";
        return ticketService.deleteTicket();
    }

    @GetMapping(path = "/ticket")
    public String getTicket() {
        // DaprClient daprClient = new DaprClientBuilder().build();
        // Mono<State<Ticket>> ticket = daprClient.getState(STATE_STORE_NAME, KEY, Ticket.class); 
        // return "Ticket -> " + ticket.block().getValue();
        return ticketService.getTicket();
    }

    @PostMapping(path = "/initiateWorkflow") 
    public String initiateWorkflow() {
        try {
            workflowService.initiateWorkflow();
        } catch (Exception e) {
            return "Exception -> " + e.toString();
        }
        return "Workflow Done and Loop Closed";
    }
}
