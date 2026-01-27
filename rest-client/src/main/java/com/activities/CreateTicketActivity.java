package com.activities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.models.Ticket;
import com.service.TicketService;

import io.dapr.workflows.WorkflowActivity;
import io.dapr.workflows.WorkflowActivityContext;

public class CreateTicketActivity implements WorkflowActivity{
    private static Logger logger = LoggerFactory.getLogger(CreateTicketActivity.class);
    private TicketService ticketService = new TicketService();

    @Override
    public Object run(WorkflowActivityContext ctx) {
        Ticket ticket = ctx.getInput(Ticket.class);
        logger.info("Creating ticket -> " + ticket.getTitle());

        // Simulate slow processing
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
        }

        ticketService.createTicket(ticket);
        return "Ticket " + ticket.getTitle() + " created!";
    }
}
