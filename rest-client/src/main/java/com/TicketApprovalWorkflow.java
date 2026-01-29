package com;

import org.slf4j.Logger;

import com.activities.ApproveTicketActivity;
import com.activities.CreateTicketActivity;
import com.activities.NotifyActivity;
import com.models.Notification;
import com.models.Ticket;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;

public class TicketApprovalWorkflow implements Workflow{
    
    @Override
    public WorkflowStub create() {
        return ctx -> {
            Logger logger = ctx.getLogger();
            logger.info("Starting Workflow: " + ctx.getName());
            logger.info("Current Orchestration Time: " + ctx.getCurrentInstant());

            Ticket ticket = ctx.getInput(Ticket.class);
            logger.info("Received Ticket: " + ticket.toString());

            // Create Ticket
            ctx.callActivity(CreateTicketActivity.class.getName(), ticket).await();

            // Notify
            Notification notification = new Notification();
            notification.setMessage("Ticket has been created -> " + ticket.getTitle());
            ctx.callActivity(NotifyActivity.class.getName(), notification).await();

            // Approve Ticket
            ctx.callActivity(ApproveTicketActivity.class.getName(), ticket).await();

            // Notify
            notification.setMessage("Ticket has been approved! -> " + ticket.toString());
            ctx.callActivity(NotifyActivity.class.getName(), notification).await();

            // Close Ticket

            // Notify

            // End Process
            ctx.complete("Ticket Approval Complete");
        };
    }
}
