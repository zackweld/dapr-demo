package com.service;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import com.TicketApprovalWorkflow;
import com.activities.ApproveTicketActivity;
import com.activities.CreateTicketActivity;
import com.activities.NotifyActivity;
import com.models.Ticket;

import io.dapr.workflows.client.DaprWorkflowClient;
import io.dapr.workflows.client.WorkflowInstanceStatus;
import io.dapr.workflows.runtime.WorkflowRuntime;
import io.dapr.workflows.runtime.WorkflowRuntimeBuilder;

public class WorkflowService {
    
    public void initiateWorkflow() throws Exception{

        WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(TicketApprovalWorkflow.class);
        builder.registerActivity(CreateTicketActivity.class);
        builder.registerActivity(NotifyActivity.class);
        builder.registerActivity(ApproveTicketActivity.class);

        WorkflowRuntime runtime = builder.build();

        System.out.println("Start workflow runtime");
        runtime.start(false);

        DaprWorkflowClient workflowClient = new DaprWorkflowClient();
        try (workflowClient) {
            executeWorkflow(workflowClient);
        }
    }

    private static void executeWorkflow(DaprWorkflowClient workflowClient) {
        System.out.println("==========Begin Ticket Workflow==========");
        Ticket ticket = new Ticket();
        ticket.setTitle("Workflow Ticket");
        ticket.setOwner("Workflow Admin");
        ticket.setApproved(false);

        System.out.println("Creating Ticket -> " + ticket.toString());

        String instanceId = workflowClient.scheduleNewWorkflow(TicketApprovalWorkflow.class, ticket);
        System.out.printf("Scheduled new workflow instance of TicketApprovalWorkflow with instance ID: %s%n",
        instanceId);

        try {
            workflowClient.waitForInstanceStart(instanceId, Duration.ofSeconds(10), false);
            System.out.printf("Workflow instance %s started%n", instanceId);
        } catch (TimeoutException e) {
            System.out.printf("Workflow instance %s did not start within 10 seconds%n", instanceId);
            return;
        }

        try {
            WorkflowInstanceStatus workflowStatus = workflowClient.waitForInstanceCompletion(instanceId,
                Duration.ofSeconds(30),
                true);
            if (workflowStatus != null) {
                System.out.printf("Workflow instance completed, out is: %s%n",
                    workflowStatus.getSerializedOutput());
                } else {
                System.out.printf("Workflow instance %s not found%n", instanceId);
            }
        } catch (TimeoutException e) {
            System.out.printf("Workflow instance %s did not complete within 30 seconds%n", instanceId);
        }

        System.out.println("TERMINATING WORKFLOW");
        workflowClient.terminateWorkflow(instanceId, instanceId);
        workflowClient.purgeInstance(instanceId);
    }
}
