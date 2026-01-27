# dapr-demo
Diagrid SA Challenge

# Build

- cd rest-client/
- mvn clean install
- cd ..

# Run

- dapr run -f .

# Http Controller 

- Contoller path - http://localhost:{HTTP PORT}/

    - Header -> dapr-app-id = rest-client

- Create Ticket
    - POST
    - Path extension - /ticket

- Retrieve Ticket
    - GET
    - Path extension - /ticket

- Delete Ticket
    - DELETE
    - Path extension - /ticket

# Workflow

- Initiate Workflow (Same controller as above)
    - POST
    - Path extension - /initiateWorkflow

## Workflow Process - Ticket Approval
- Create Ticket
- Notify
- Approve Ticket
- Notify


