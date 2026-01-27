package com.activities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.models.Notification;

import io.dapr.workflows.WorkflowActivity;
import io.dapr.workflows.WorkflowActivityContext;

public class NotifyActivity implements WorkflowActivity{
    private static Logger logger = LoggerFactory.getLogger(NotifyActivity.class);
    
    @Override
    public Object run(WorkflowActivityContext ctx) {
        Notification notification = ctx.getInput(Notification.class);
        logger.info(notification.getMessage());

        return "";
    }
}
